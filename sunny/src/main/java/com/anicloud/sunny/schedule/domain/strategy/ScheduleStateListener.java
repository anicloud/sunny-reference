package com.anicloud.sunny.schedule.domain.strategy;

import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by huangbin on 7/24/15.
 */
public interface ScheduleStateListener {
    void onScheduleStateChanged(Object src, ScheduleState state) ;
}
