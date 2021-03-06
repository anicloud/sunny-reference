package com.anicloud.sunny.schedule.dto;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;

import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
public class FeatureInstanceDto {
    public DeviceDto deviceDto;

    public String featureId;
    public ScheduleState state;
    public Integer stage;

    public List<FunctionInstanceDto> functionInstanceDtoList;
    public List<TriggerInstanceDto> triggerInstanceDtoList;

    public Long intervalTime;

    public FeatureInstanceDto() {
    }

    public FeatureInstanceDto(String featureId, ScheduleState state, Integer stage,
                              List<FunctionInstanceDto> functionInstanceDtoList, List<TriggerInstanceDto> triggerInstanceDtoList,
                              Long intervalTime) {
        this.featureId = featureId;
        this.state = state;
        this.stage = stage;
        this.functionInstanceDtoList = functionInstanceDtoList;
        this.triggerInstanceDtoList = triggerInstanceDtoList;
        this.intervalTime = intervalTime;
    }
}
