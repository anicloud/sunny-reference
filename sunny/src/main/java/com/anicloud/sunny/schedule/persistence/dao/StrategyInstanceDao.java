package com.anicloud.sunny.schedule.persistence.dao;

import com.anicloud.sunny.infrastructure.persistence.domain.strategy.StrategyDao;
import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;
import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
@Entity
@Table(name = "t_schedule_strategy")
public class StrategyInstanceDao extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "strategyModel", referencedColumnName = "id")
    public StrategyDao strategyModel;

    @Column(name = "strategyInstanceId", nullable = false, unique = true)
    public String strategyInstanceId;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    public ScheduleState state;
    public Integer stage;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "strategy_id", referencedColumnName = "id")
    public List<FeatureInstanceDao> featureInstanceDaoList;

    @Column(name = "action", nullable = false)
    @Enumerated(EnumType.STRING)
    public StrategyAction action;
    @Column(name = "timeStamp", nullable = false)
    public Long timeStamp;

    @Column(name = "startTime")
    public Date startTime;
    @Column(name = "repeatWeek")
    public String repeatWeek;
    @Column(name = "isRepeat")
    public boolean isRepeat;
    @Column(name = "isScheduleNow")
    public boolean isScheduleNow;

    public StrategyInstanceDao() {
    }

    public StrategyInstanceDao(StrategyDao strategyModel,String strategyInstanceId, ScheduleState state, Integer stage,
                               List<FeatureInstanceDao> featureInstanceDaoList,
                               StrategyAction action,
                               Long timeStamp,Date startTime,String repeatWeek,
                               boolean isRepeat, boolean isScheduleNow) {
        this.strategyModel = strategyModel;
        this.strategyInstanceId = strategyInstanceId;
        this.state = state;
        this.stage = stage;
        this.featureInstanceDaoList = featureInstanceDaoList;
        this.action = action;
        this.timeStamp = timeStamp;
        this.startTime = startTime;
        this.repeatWeek = repeatWeek;
        this.isRepeat = isRepeat;
        this.isScheduleNow = isScheduleNow;
    }
}
