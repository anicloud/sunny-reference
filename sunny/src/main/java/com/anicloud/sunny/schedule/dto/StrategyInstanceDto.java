package com.anicloud.sunny.schedule.dto;


import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;

import java.util.List;

/**
 * Created by huangbin on 7/17/15.
 */
public class StrategyInstanceDto {
    public String strategyId;
    public StrategyState state;
    public Integer stage;

    public List<FeatureInstanceDto> featureInstanceDtoList;
    public StrategyAction action;

    public Long timeStamp;

    public StrategyInstanceDto(String strategyId,
                               StrategyState state, Integer stage,
                               List<FeatureInstanceDto> featureInstanceDtoList,
                               StrategyAction action,
                               Long timeStamp) {
        this.strategyId = strategyId;
        this.state = state;
        this.stage = stage;
        this.featureInstanceDtoList = featureInstanceDtoList;
        this.action = action;
        this.timeStamp = timeStamp;
    }
}
