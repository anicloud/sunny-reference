package com.anicloud.sunny.schedule.dto;

import java.util.Date;

/**
 * Created by huangbin on 7/19/15.
 */
public class TriggerInstanceDto {
    public Date startTime;
    public Integer repeatInterval;
    public Integer repeatCount;

    public TriggerInstanceDto(Date startTime, Integer repeatInterval, Integer repeatCount) {
        this.startTime = startTime;
        this.repeatInterval = repeatInterval;
        this.repeatCount = repeatCount;
    }
}
