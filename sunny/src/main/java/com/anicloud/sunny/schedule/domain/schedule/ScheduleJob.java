package com.anicloud.sunny.schedule.domain.schedule;

import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;

import java.util.Set;

/**
 * Created by huangbin on 7/16/15.
 */
public class ScheduleJob {
    public String jobName;
    public String jobGroup;
    public ScheduleState jobState;
    public String description;
    public Class jobClass;

    public Set<ScheduleTrigger> triggers;
    public ScheduleTask task;
    public boolean isScheduleNow;

    public ScheduleJob() {
    }

    public ScheduleJob(String jobName, String jobGroup, ScheduleState jobState, String description,
                       Class jobClass, Set<ScheduleTrigger> triggers, ScheduleTask task, boolean isScheduleNow) {
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobState = jobState;
        this.description = description;
        this.jobClass = jobClass;
        this.triggers = triggers;
        this.task = task;
        this.isScheduleNow = isScheduleNow;
    }
}
