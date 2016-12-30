package com.anicloud.sunny.schedule.service;

import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import com.anicloud.sunny.schedule.dto.StrategyInstanceDto;

/**
 * Created by huangbin on 7/9/15.
 */
public interface ScheduleService {
    public void scheduleStrategy(StrategyInstance strategyInstance);
}
