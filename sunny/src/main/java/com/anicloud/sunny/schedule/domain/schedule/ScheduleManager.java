package com.anicloud.sunny.schedule.domain.schedule;

import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by huangbin on 7/17/15.
 */

@Component(value = "scheduleManager")
public class ScheduleManager {
    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;

    private Scheduler scheduler = schedulerFactoryBean.getScheduler();

    private List<ScheduleJob> jobList = new ArrayList<>();

    public void addJob(ScheduleJob job) {
        try {
            JobDetail jobDetail = newJob(job.jobClass)
                    .withIdentity(job.jobName, job.jobGroup)
                    .build();
            scheduler.addJob(jobDetail, true);
            for (ScheduleTrigger scheduleTrigger : job.triggers) {
                SimpleTrigger simpleTrigger = (SimpleTrigger) newTrigger()
                        .withIdentity(scheduleTrigger.triggerName, scheduleTrigger.triggerGroup)
                        .forJob(jobDetail)
                        .startAt(scheduleTrigger.startTime)
                        .build();
                scheduler.scheduleJob(simpleTrigger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteJob(ScheduleJob job) {
        JobKey jobKey = JobKey.jobKey(job.jobName, job.jobGroup);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void pauseJob(ScheduleJob job) {
        JobKey jobKey = JobKey.jobKey(job.jobName, job.jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void resumeJob(ScheduleJob job) {
        JobKey jobKey = JobKey.jobKey(job.jobName, job.jobGroup);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void triggerJobAtOnce(ScheduleJob job) {
        JobKey jobKey = JobKey.jobKey(job.jobName, job.jobGroup);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
