package com.anicloud.sunny.schedule.service;

import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.schedule.domain.adapter.DtoAdapter;
import com.anicloud.sunny.schedule.domain.schedule.ScheduleManager;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import com.anicloud.sunny.schedule.dto.StrategyInstanceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by huangbin on 7/10/15.
 */
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    private ScheduleManager scheduleManager;

    @Override
    public void scheduleStrategy(Strategy strategy) {
        StrategyInstance strategyInstance = strategy.strategyInstance;
        strategyInstance.prepareSchedule(scheduleManager);
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
}
