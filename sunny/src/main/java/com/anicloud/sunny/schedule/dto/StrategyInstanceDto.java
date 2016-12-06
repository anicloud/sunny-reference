package com.anicloud.sunny.schedule.dto;


import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;
import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;

import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 7/17/15.
 */
public class StrategyInstanceDto {
    public String strategyId;
    public ScheduleState state;
    public Integer stage;

    public List<FeatureInstanceDto> featureInstanceDtoList;
    public StrategyAction action;

    public Long timeStamp;
    public Date startTime;
    public boolean isScheduleNow;
    public boolean isRepeat;
    public String[] repeatWeek;

    public StrategyInstanceDto(String strategyId,
                               ScheduleState state, Integer stage,
                               List<FeatureInstanceDto> featureInstanceDtoList,
                               StrategyAction action, Long timeStamp,
                               Date startTime, String[] repeatWeek,
                               boolean isRepeat, boolean isScheduleNow) {
        this.strategyId = strategyId;
        this.state = state;
        this.stage = stage;
        this.featureInstanceDtoList = featureInstanceDtoList;
        this.action = action;
        this.timeStamp = timeStamp;
        this.startTime = startTime;
        this.repeatWeek = repeatWeek;
        this.isRepeat = isRepeat;
        this.isScheduleNow = isScheduleNow;
    }
}
