package com.anicloud.sunny.schedule.service;

import com.anicloud.sunny.schedule.domain.adapter.DtoAdapter;
import com.anicloud.sunny.schedule.domain.schedule.ScheduleManager;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import com.anicloud.sunny.schedule.dto.StrategyInstanceDto;

import javax.annotation.Resource;

/**
 * Created by huangbin on 7/10/15.
 */
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    ScheduleManager scheduleManager;

    @Override
    public void scheduleStrategy(StrategyInstanceDto strategyInstanceDto) {
        StrategyInstance strategyInstance = DtoAdapter.fromStrategyInstanceDto(strategyInstanceDto);
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
