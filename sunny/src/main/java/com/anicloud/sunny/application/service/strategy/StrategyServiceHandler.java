package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.assemble.StrategyDtoAssembler;
import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.utils.NumGenerate;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.infrastructure.jms.StrategyStateQueueService;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.StrategyDao;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import com.anicloud.sunny.infrastructure.persistence.repository.schedule.StrategyInstanceRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.strategy.StrategyRepository;
import com.anicloud.sunny.infrastructure.persistence.service.StrategyPersistenceService;
import com.anicloud.sunny.schedule.domain.adapter.DaoAdapter;
import com.anicloud.sunny.schedule.domain.strategy.*;
import com.anicloud.sunny.infrastructure.persistence.service.StrategyInstancePersistenceService;
import com.anicloud.sunny.schedule.persistence.dao.StrategyInstanceDao;
import com.anicloud.sunny.schedule.service.ScheduleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Iterator;
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
    @Resource
    private StrategyRepository strategyRepository;
    @Resource
    private StrategyInstanceRepository strategyInstanceRepository;
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
    public void saveStrategy(Strategy strategySrc) {
        Strategy strategy = strategySrc.clone();
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
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void operateStrategy(String strategyId, StrategyAction action) {
        Assert.notNull(strategyId);
        Assert.notNull(action);
        Strategy strategy = getStrategyById(strategyId);
        strategy.strategyInstance.action = action;
        strategy.strategyInstance.timeStamp = System.currentTimeMillis();
        scheduleService.scheduleStrategy(strategy);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public StrategyDto modifyStrategy(StrategyDto strategyDto) {
        Strategy strategy = StrategyDtoAssembler.toStrategy(strategyDto);
        strategy = Strategy.modify(strategyPersistenceService, strategy);
        return StrategyDtoAssembler.toDto(strategy);
    }

    @Override
    public void removeStrategy(Long hashUserId, String strategyId) {
        Strategy.remove(strategyPersistenceService, strategyId);
        strategyInstancePersistenceService.remove(strategyId);
    }

    @Override
    public StrategyDto getStrategyDtoById(String strategyId) {
        Strategy strategy = getStrategyById(strategyId);
        return StrategyDtoAssembler.toDto(strategy);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Strategy getStrategyById(String strategyId) {
        Strategy strategy = Strategy.getStrategyById(strategyPersistenceService, strategyId);
        StrategyInstanceDao instanceDao = strategyInstancePersistenceService.getByStrategyId(strategyId);
        strategy.strategyInstance = DaoAdapter.fromStrategyInstanceDao(instanceDao);
        return strategy;
    }

    @Override
    public List<StrategyDto> getStrategyByUser(Long hashUserId) {
        List<Strategy> strategyList = Strategy.getStrategyListByUser(strategyPersistenceService, hashUserId);
        for (Strategy strategy : strategyList) {
            StrategyInstanceDao instanceDao = strategyInstancePersistenceService.getByStrategyId(strategy.strategyId);
            if(instanceDao != null){
                strategy.strategyInstance = DaoAdapter.fromStrategyInstanceDao(instanceDao);
                strategy.startTime = strategy.strategyInstance.startTime;
                strategy.repeatWeek = strategy.strategyInstance.repeatWeek;
                strategy.isRepeat = strategy.strategyInstance.isRepeat;
            }
        }
        return StrategyDtoAssembler.toDtoList(strategyList);
    }

    public List<StrategyDto> getStrategyByUser(Long hashUserId,int page,int number) {
        Specification<StrategyDao> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Join<StrategyDao,UserDao> join = root.join(root.getModel().getSingularAttribute("owner", UserDao.class), JoinType.INNER);
            Predicate predicate = criteriaBuilder.equal(join.get(("hashUserId")).as(Long.class),hashUserId);
            criteriaQuery.where(predicate);
            return criteriaQuery.getRestriction();
        };
        PageRequest pageRequest = new PageRequest(page,number);
        Page<StrategyDao> pageInfo = strategyRepository.findAll(specification,pageRequest);
        Iterator<StrategyDao> iterator = pageInfo.iterator();
        List<Strategy> strategyList = new ArrayList<>();
        while(iterator.hasNext()) {
            Strategy strategy = Strategy.toStrategy(iterator.next());
            StrategyInstanceDao instanceDao = strategyInstancePersistenceService.getByStrategyId(strategy.strategyId);
            strategy.strategyInstance = DaoAdapter.fromStrategyInstanceDao(instanceDao);
            strategyList.add(strategy);
        }
        return StrategyDtoAssembler.toDtoList(strategyList);
    }

    public List<StrategyInstance> getRunningStrategy() {
        List<StrategyInstanceDao> instanceDaos = strategyInstanceRepository.findRunningStrategy();
        List<StrategyInstance> instances = new ArrayList<>();
        for(StrategyInstanceDao instanceDao:instanceDaos) {
            StrategyInstance instance = DaoAdapter.fromStrategyInstanceDao(instanceDao);
            instances.add(instance);
        }
        return instances;
    }

    @Override
    public StrategyDao findByStrategyId(String strategyId) {
        return strategyRepository.findByStrategyNum(strategyId);
    }

    public int getCountByHashUserId(Long hashUserId) {
        return strategyRepository.countByHashUserId(hashUserId);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void runDeviceFeature(UserDto userDto, DeviceFeatureInstanceDto deviceFeatureInstanceDto) {
        // generate the device feature run strategy,
        // identify it by strategy name field
//        Strategy strategy = StrategyDtoAssembler.fromRunDeviceFeatureInstanceDto(userDto, deviceFeatureInstanceDto);
//        scheduleService.scheduleStrategy(strategy);
    }

    private Strategy getSingleStrategy(String strategyId) {
        return Strategy.getStrategyById(strategyPersistenceService, strategyId);
    }
}
