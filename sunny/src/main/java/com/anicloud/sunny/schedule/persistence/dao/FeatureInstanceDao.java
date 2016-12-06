package com.anicloud.sunny.schedule.persistence.dao;

import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;

import javax.persistence.*;
import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
@Entity
@Table(name = "t_schedule_feature")
public class FeatureInstanceDao extends AbstractEntity {
    @Column(name = "featureId", nullable = false, length = 100)
    public String featureId;
    @Column(name = "deviceId", length = 100)
    public String deviceId;
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    public ScheduleState state;
    @Column(name = "stage")
    public Integer stage;
    @Column(name = "isScheduleNow")
    public boolean isScheduleNow;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    public List<FunctionInstanceDao> functionInstanceDaoList;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    public List<TriggerInstanceDao> triggerInstanceDaoList;
    @Column(name = "intervalTime")
    public Long intervalTime;

    public FeatureInstanceDao() {
    }

    public FeatureInstanceDao(String featureId, String deviceId, ScheduleState state, Integer stage,
                              List<FunctionInstanceDao> functionInstanceDaoList,
                              List<TriggerInstanceDao> triggerInstanceDaoList,
                              boolean isScheduleNow,Long intervalTime) {
        this.featureId = featureId;
        this.deviceId = deviceId;
        this.state = state;
        this.stage = stage;
        this.functionInstanceDaoList = functionInstanceDaoList;
        this.triggerInstanceDaoList = triggerInstanceDaoList;
        this.isScheduleNow = isScheduleNow;
        this.intervalTime = intervalTime;
    }
}
