package com.anicloud.sunny.schedule.service;

import com.anicloud.sunny.schedule.dto.StrategyInstanceDto;

/**
 * Created by huangbin on 7/9/15.
 */
public interface ScheduleService {
    void scheduleStrategy(StrategyInstanceDto strategyInstanceDto);
}
