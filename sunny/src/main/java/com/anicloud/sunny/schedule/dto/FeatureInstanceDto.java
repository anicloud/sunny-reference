package com.anicloud.sunny.schedule.dto;

import com.anicloud.sunny.schedule.domain.strategy.FeatureState;

import java.util.List;
import java.util.Set;

/**
 * Created by huangbin on 7/19/15.
 */
public class FeatureInstanceDto {
    public String featureId;
    public FeatureState state;
    public Integer stage;

    public List<FunctionInstanceDto> functionInstanceDtoList;
    public List<TriggerInstanceDto> triggerInstanceDtoList;

    public FeatureInstanceDto(String featureId, FeatureState state, Integer stage,
                              List<FunctionInstanceDto> functionInstanceDtoList, List<TriggerInstanceDto> triggerInstanceDtoList) {
        this.featureId = featureId;
        this.state = state;
        this.stage = stage;
        this.functionInstanceDtoList = functionInstanceDtoList;
        this.triggerInstanceDtoList = triggerInstanceDtoList;
    }
}
