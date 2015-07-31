package com.anicloud.sunny.schedule.service;

import com.anicloud.sunny.application.service.strategy.StrategyService;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.schedule.domain.schedule.ScheduleManager;
import com.anicloud.sunny.schedule.domain.strategy.ScheduleStateListener;
import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            strategyInstance.prepareSchedule(scheduleManager, this, strategy.owner.accessToken);
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

        strategyService.saveStrategy(strategy);
    }
}
