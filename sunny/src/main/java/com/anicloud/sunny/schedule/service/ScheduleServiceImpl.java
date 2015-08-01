package com.anicloud.sunny.schedule.service;

import com.anicloud.sunny.application.service.strategy.StrategyService;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.schedule.domain.schedule.ScheduleManager;
import com.anicloud.sunny.schedule.domain.strategy.ScheduleStateListener;
import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangbin on 7/10/15.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService, ScheduleStateListener {
    private final static Logger log = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Resource
    private ScheduleManager scheduleManager;

    @Resource
    private StrategyService strategyService;

    private Map<String, Strategy> strategyMap;

    public ScheduleServiceImpl() {
        strategyMap = new HashMap<>();
    }

    @Override
    public void scheduleStrategy(Strategy strategy) {
        StrategyInstance strategyInstance = strategy.strategyInstance;
        if (!strategyInstance.isScheduled) {
            strategyInstance.prepareSchedule(scheduleManager, this, strategy.owner.hashUserId);
            strategyMap.put(strategy.strategyId, strategy);
        }
        switch (strategyInstance.action) {
            case START:
                strategyInstance.start();
                break;
            case STOP:
                strategyInstance.stop();
                break;
            case PAUSE:
                strategyInstance.pause();
                break;
            case RESUME:
                strategyInstance.resume();
                break;
            default:
                break;
        }
    }


    @Override
    public void onScheduleStateChanged(Object src, ScheduleState state) {
        StrategyInstance strategyInstance = (StrategyInstance)src;
        Strategy strategy = strategyMap.get(strategyInstance.strategyId);
        log.info("-----------strategy state changed--------------");
        log.info("[name]:" + strategy.strategyName);
        log.info("[state]:");
        switch (state) {
            case RUNNING:
                log.info("running");
                break;
            case DONE:
                log.info("done");
                break;
            case SUSPENDED:
                log.info("suspended");
                break;
            case NONE:
                log.info("none");
                break;
            default:
                log.info("unknow");

        }
        strategyService.saveStrategy(strategy);
    }
}
