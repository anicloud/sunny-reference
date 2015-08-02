package com.anicloud.sunny.schedule.domain.schedule;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by huangbin on 7/17/15.
 */

@Component
public class ScheduleManager implements Serializable {
    private final static Logger LOGGER = LoggerFactory.getLogger(ScheduleManager.class);

    @Autowired
    private org.springframework.scheduling.quartz.SchedulerFactoryBean schedulerFactoryBean;

    private Scheduler scheduler;

    private List<ScheduleJob> jobList = new ArrayList<>();

    public ScheduleManager() {
    }

    @PostConstruct
    public void init() {
        LOGGER.info("ScheduleManager PostConstruct");
        scheduler = schedulerFactoryBean.getScheduler();
    }

    public void addJob(ScheduleJob job) {
        try {
            JobDetail jobDetail = newJob(job.jobClass)
                    .withIdentity(job.jobName, job.jobGroup)
                    .storeDurably(true)
                    .build();
            jobDetail.getJobDataMap().put("ScheduleJob", job);
            if (job.isScheduleNow) {
                scheduler.addJob(jobDetail, false);
                scheduler.triggerJob(jobDetail.getKey());
            } else {
                for (ScheduleTrigger scheduleTrigger : job.triggers) {
                    SimpleTrigger simpleTrigger = (SimpleTrigger) newTrigger()
                            .withIdentity(scheduleTrigger.triggerName, scheduleTrigger.triggerGroup)
                            .forJob(jobDetail)
                            .startAt(scheduleTrigger.startTime)
                            .withSchedule(
                                    simpleSchedule()
                                            .withIntervalInSeconds(scheduleTrigger.repeatInterval)
                                            .withRepeatCount(scheduleTrigger.repeatCount)
                                            .withMisfireHandlingInstructionFireNow()
                            )
                            .build();
                    if (scheduler.checkExists(jobDetail.getKey())) {
                        scheduler.scheduleJob(simpleTrigger);
                    } else {
                        scheduler.scheduleJob(jobDetail, simpleTrigger);
                    }
                }
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
