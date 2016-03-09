package com.anicloud.sunny.jobSchedule.examples.examples01;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @autor zhaoyu
 * @date 16-3-4
 * @since JDK 1.7
 */
public class SimpleJob implements Job {
    private static Logger _log = LoggerFactory.getLogger(SimpleJob.class);

    public SimpleJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Say Hello to the World and display the date/time
        JobKey key = context.getJobDetail().getKey();
        _log.info("job key is : " + key.toString());
        _log.info("Hello World! - " + new Date());
    }
}
