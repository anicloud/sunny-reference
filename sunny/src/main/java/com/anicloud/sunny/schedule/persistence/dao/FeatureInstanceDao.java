package com.anicloud.sunny.schedule.persistence.dao;

import javax.persistence.*;
import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
@Entity
@Table(name = "t_schedule_feature")
public class FeatureInstanceDao extends AbstractEntity {
    @Column(name = "featureId", nullable = false, unique = true, length = 100)
    public String featureId;
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    public ScheduleState state;
    @Column(name = "stage")
    public Integer stage;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    public List<FunctionInstanceDao> functionInstanceDaoList;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    public List<TriggerInstanceDao> triggerInstanceDaoList;

    public FeatureInstanceDao() {
    }

    public FeatureInstanceDao(String featureId, ScheduleState state, Integer stage,
                              List<FunctionInstanceDao> functionInstanceDaoList, List<TriggerInstanceDao> triggerInstanceDaoList) {
        this.featureId = featureId;
        this.state = state;
        this.stage = stage;
        this.functionInstanceDaoList = functionInstanceDaoList;
        this.triggerInstanceDaoList = triggerInstanceDaoList;
    }
}
