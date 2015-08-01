package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.assemble.StrategyDtoAssembler;
import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.utils.NumGenerate;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.infrastructure.jms.StrategyStateQueueService;
import com.anicloud.sunny.infrastructure.persistence.service.StrategyPersistenceService;
import com.anicloud.sunny.schedule.domain.adapter.DaoAdapter;
import com.anicloud.sunny.schedule.domain.strategy.*;
import com.anicloud.sunny.infrastructure.persistence.service.StrategyInstancePersistenceService;
import com.anicloud.sunny.schedule.persistence.dao.StrategyInstanceDao;
import com.anicloud.sunny.schedule.service.ScheduleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-14.
 */
@Service
@Transactional
public class StrategyServiceHandler implements StrategyService {
    private final static Logger LOGGER = LoggerFactory.getLogger(StrategyServiceHandler.class);

    @Resource
    private DeviceFeatureService deviceFeatureService;
    @Resource
    private StrategyPersistenceService strategyPersistenceService;
    @Resource
    private StrategyStateQueueService strategyStateQueueService;
    @Resource
    private StrategyInstancePersistenceService strategyInstancePersistenceService;
    @Resource
    private ScheduleService scheduleService;

    @Override
    public void saveStrategy(StrategyDto strategyDto) {
        // generate the strategy number
        if (StringUtils.isEmpty(strategyDto.strategyId)) {
            strategyDto.strategyId = NumGenerate.generate();
        }

        if (strategyDto.deviceFeatureInstanceList != null) {
            for (DeviceFeatureInstanceDto deviceFeatureInstanceDto : strategyDto.deviceFeatureInstanceList) {
                String deviceFeatureId = deviceFeatureInstanceDto.deviceFeatureDto.featureId;
                deviceFeatureInstanceDto.deviceFeatureDto = deviceFeatureService.getDeviceFeatureById(deviceFeatureId);
            }
        }
        Strategy strategy = StrategyDtoAssembler.toStrategy(strategyDto);
        // saveStrategy(strategy);
        // send to the schedule
        scheduleService.scheduleStrategy(strategy);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveStrategy(Strategy strategy) {
        Assert.notNull(strategy.strategyInstance);
        Strategy strategyOrg = getSingleStrategy(strategy.strategyId);
        StrategyInstanceDao dao = strategyInstancePersistenceService.
                getByStrategyId(strategy.strategyId);
        if (strategyOrg == null && dao == null) {
            Strategy.save(strategyPersistenceService, strategy);
            strategyInstancePersistenceService.save(DaoAdapter.toStrategyInstanceDao(strategy.strategyInstance));
        } else {
            strategyInstancePersistenceService.update(DaoAdapter.toStrategyInstanceDao(strategy.strategyInstance));
        }
        // send the state to jms
        strategyStateQueueService.updateStrategyState(strategy);
    }

    @Override
    public void operateStrategy(String strategyId, StrategyAction action) {
        Strategy strategy = getStrategyById(strategyId);
        strategy.strategyInstance.action = action;
        strategy.strategyInstance.timeStamp = System.currentTimeMillis();
        scheduleService.scheduleStrategy(strategy);
    }

    @Override
    public StrategyDto modifyStrategy(StrategyDto strategyDto) {
        Strategy strategy = StrategyDtoAssembler.toStrategy(strategyDto);
        strategy = Strategy.modify(strategyPersistenceService, strategy);
        return StrategyDtoAssembler.toDto(strategy);
    }

    @Override
    public void removeStrategy(String strategyId) {
        Strategy.remove(strategyPersistenceService, strategyId);
        strategyInstancePersistenceService.remove(strategyId);
    }

    @Override
    public StrategyDto getStrategyDtoById(String strategyId) {
        Strategy strategy = getStrategyById(strategyId);
        return StrategyDtoAssembler.toDto(strategy);
    }

    @Override
    public Strategy getStrategyById(String strategyId) {
        Strategy strategy = Strategy.getStrategyById(strategyPersistenceService, strategyId);
        StrategyInstanceDao instanceDao = strategyInstancePersistenceService.getByStrategyId(strategyId);
        strategy.strategyInstance = DaoAdapter.fromStrategyInstanceDao(instanceDao);
        return strategy;
    }

    @Override
    public List<StrategyDto> getStrategyByUser(String hashUserId) {
        List<Strategy> strategyList = Strategy.getStrategyListByUser(strategyPersistenceService, hashUserId);
        for (Strategy strategy : strategyList) {
            StrategyInstanceDao instanceDao = strategyInstancePersistenceService.getByStrategyId(strategy.strategyId);
            strategy.strategyInstance = DaoAdapter.fromStrategyInstanceDao(instanceDao);
        }
        return StrategyDtoAssembler.toDtoList(strategyList);
    }

    @Override
    public void runDeviceFeature(UserDto userDto, DeviceFeatureInstanceDto deviceFeatureInstanceDto) {
        // generate the device feature run strategy, identify it by strategy name field
        Strategy strategy = StrategyDtoAssembler.fromRunDeviceFeatureInstanceDto(userDto, deviceFeatureInstanceDto);
        scheduleService.scheduleStrategy(strategy);
    }

    private Strategy getSingleStrategy(String strategyId) {
        return Strategy.getStrategyById(strategyPersistenceService, strategyId);
    }
}
