package com.anicloud.sunny.schedule.domain.schedule;

import org.quartz.SimpleTrigger;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by huangbin on 7/17/15.
 */
public class ScheduleTrigger implements Serializable {
    public String jobName;
    public String jobGroup;
    public String triggerName;
    public String triggerGroup;
    public Date startTime;
    public Integer repeatInterval;
    public Integer repeatCount;

    public ScheduleTrigger() {
    }

    public ScheduleTrigger(String jobName, String jobGroup, String triggerName, String triggerGroup,
                           Date startTime, Integer repeatInterval, Integer repeatCount) {
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.triggerName = triggerName;
        this.triggerGroup = triggerGroup;
        this.startTime = startTime;
        this.repeatInterval = repeatInterval;
        this.repeatCount = repeatCount;
    }
}
