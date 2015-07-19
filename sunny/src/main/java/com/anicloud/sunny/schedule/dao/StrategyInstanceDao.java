package com.anicloud.sunny.schedule.dao;

import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;
import com.anicloud.sunny.schedule.domain.strategy.StrategyState;

import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
public class StrategyInstanceDao {
    public String strategyId;
    public StrategyState state;
    public Integer stage;

    public List<FeatureInstanceDao> featureInstanceDaoList;

    public StrategyAction action;

    public StrategyInstanceDao(String strategyId, StrategyState state, Integer stage,
                               List<FeatureInstanceDao> featureInstanceDaoList, StrategyAction action) {
        this.strategyId = strategyId;
        this.state = state;
        this.stage = stage;
        this.featureInstanceDaoList = featureInstanceDaoList;
        this.action = action;
    }
}
