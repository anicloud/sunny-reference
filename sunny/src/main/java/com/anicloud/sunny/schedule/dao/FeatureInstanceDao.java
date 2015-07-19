package com.anicloud.sunny.schedule.dao;

import com.anicloud.sunny.schedule.domain.strategy.FeatureState;

import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
public class FeatureInstanceDao {
    public String featureId;
    public FeatureState state;
    public Integer stage;

    public List<FunctionInstanceDao> functionInstanceDaoList;
    public List<TriggerInstanceDao> triggerInstanceDaoList;

    public FeatureInstanceDao(String featureId, FeatureState state, Integer stage,
                              List<FunctionInstanceDao> functionInstanceDaoList, List<TriggerInstanceDao> triggerInstanceDaoList) {
        this.featureId = featureId;
        this.state = state;
        this.stage = stage;
        this.functionInstanceDaoList = functionInstanceDaoList;
        this.triggerInstanceDaoList = triggerInstanceDaoList;
    }
}
