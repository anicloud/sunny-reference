package com.anicloud.sunny.schedule.domain.strategy;

import com.anicloud.sunny.schedule.domain.schedule.ScheduleTrigger;

import java.util.Date;

/**
 * Created by huangbin on 7/19/15.
 */
public class TriggerInstance {
    public Date startTime;
    public Integer repeatInterval;
    public Integer repeatCount;

    public TriggerInstance() {
    }

    public TriggerInstance(Date startTime, Integer repeatInterval, Integer repeatCount) {
        this.startTime = startTime;
        this.repeatInterval = repeatInterval;
        this.repeatCount = repeatCount;
    }
}
