package com.anicloud.sunny.schedule.domain.schedule;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;
import java.util.Calendar;

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
                if(scheduler.checkExists(jobDetail.getKey())) {
                    scheduler.deleteJob(jobDetail.getKey());
                }
                scheduler.addJob(jobDetail, false);
                scheduler.triggerJob(jobDetail.getKey());
            } else {
                Trigger trigger;
                for (ScheduleTrigger scheduleTrigger : job.triggers) {
                    if(scheduleTrigger.isRepeat) {
                        trigger = TriggerBuilder.newTrigger()
                                .withIdentity(scheduleTrigger.triggerName,scheduleTrigger.triggerGroup)
                                .forJob(jobDetail)
                                .withSchedule(CronScheduleBuilder.cronSchedule(getCronTriggerExpress(scheduleTrigger.startTime,scheduleTrigger.repeatWeek)))
                                .build();
                    } else {
                        trigger = TriggerBuilder.newTrigger()
                                .withIdentity(scheduleTrigger.triggerName, scheduleTrigger.triggerGroup)
                                .forJob(jobDetail)
                                .startAt(scheduleTrigger.startTime)
                                .build();
                    }
                    if (scheduler.checkExists(jobDetail.getKey())) {
                        scheduler.scheduleJob(trigger);
                    } else {
                        scheduler.scheduleJob(jobDetail, trigger);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getCronTriggerExpress(Date startTime,String[] repeatWeek) {
        if(startTime != null && repeatWeek != null) {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startTime);
            int minute = startCalendar.get(Calendar.MINUTE);
            int hour = startCalendar.get(Calendar.HOUR_OF_DAY);
            StringBuilder sb = new StringBuilder("");
            for(String week:repeatWeek) {
                sb.append(week);
                sb.append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            String str = "0 " + minute + " " + hour + " ? " + "* " + sb.toString();
            return  str;
        }
        return null;
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
