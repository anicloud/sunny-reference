package com.anicloud.sunny.schedule.domain.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by huangbin on 7/17/15.
 */
public class ScheduleJobEntry implements Job {

    public ScheduleJobEntry() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob)jobExecutionContext.getMergedJobDataMap().get("ScheduleJob");
        scheduleJob.task.run();
    }
}
