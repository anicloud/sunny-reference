package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.assemble.StrategyDtoAssembler;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.utils.NumGenerate;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.domain.model.device.FeatureFunction;
import com.anicloud.sunny.domain.model.device.FunctionArgument;
import com.anicloud.sunny.domain.model.share.FunctionValue;
import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstance;
import com.anicloud.sunny.domain.model.strategy.FeatureTrigger;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.infrastructure.jms.StrategyStateQueueService;
import com.anicloud.sunny.infrastructure.persistence.domain.share.TriggerType;
import com.anicloud.sunny.infrastructure.persistence.service.StrategyPersistenceService;
import com.anicloud.sunny.schedule.domain.adapter.DaoAdapter;
import com.anicloud.sunny.schedule.domain.strategy.*;
import com.anicloud.sunny.schedule.dto.*;
import com.anicloud.sunny.infrastructure.persistence.service.StrategyInstancePersistenceService;
import com.anicloud.sunny.schedule.persistence.dao.StrategyInstanceDao;
import com.anicloud.sunny.schedule.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public StrategyDto saveStrategy(StrategyDto strategyDto) {
        // generate the strategy number
        if (strategyDto.strategyId == null) {
            strategyDto.strategyId = NumGenerate.generate();
        }

        Strategy strategy = StrategyDtoAssembler.toStrategy(strategyDto);
        //strategy = Strategy.save(strategyPersistenceService, strategy);
        // send to the schedule
        scheduleService.scheduleStrategy(strategy);
        return StrategyDtoAssembler.toDto(strategy);
    }

    @Override
    public void saveStrategy(Strategy strategy) {
        if (strategy.strategyInstance != null) {
            StrategyInstanceDao dao = strategyInstancePersistenceService.
                    getByStrategyId(strategy.strategyId);
            if (dao == null) {
                Strategy.save(strategyPersistenceService, strategy);
                strategyInstancePersistenceService.save(DaoAdapter.toStrategyInstanceDao(strategy.strategyInstance));
            } else {
                strategyInstancePersistenceService.update(DaoAdapter.toStrategyInstanceDao(strategy.strategyInstance));
            }
            // send the state to jms
            strategyStateQueueService.updateStrategyState(strategy);
        }
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


 }
