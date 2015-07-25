package com.anicloud.sunny.schedule.persistence.dao;

import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;

import javax.persistence.*;
import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
@Entity
@Table(name = "t_schedule_strategy")
public class StrategyInstanceDao extends AbstractEntity {
    @Column(name = "strategyId", nullable = false, unique = true)
    public String strategyId;
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    public StrategyState state;
    public Integer stage;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "strategy_id", referencedColumnName = "id")
    public List<FeatureInstanceDao> featureInstanceDaoList;

    @Column(name = "action", nullable = false)
    @Enumerated(EnumType.STRING)
    public StrategyAction action;
    @Column(name = "timeStamp", nullable = false)
    public Long timeStamp;

    public StrategyInstanceDao() {
    }

    public StrategyInstanceDao(String strategyId, StrategyState state, Integer stage,
                               List<FeatureInstanceDao> featureInstanceDaoList,
                               StrategyAction action,
                               Long timeStamp) {
        this.strategyId = strategyId;
        this.state = state;
        this.stage = stage;
        this.featureInstanceDaoList = featureInstanceDaoList;
        this.action = action;
        this.timeStamp = timeStamp;
    }
}
