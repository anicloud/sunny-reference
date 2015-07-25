package com.anicloud.sunny.schedule.persistence.dao;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangbin on 7/19/15.
 */
@Entity
@Table(name = "t_schedule_trigger")
public class TriggerInstanceDao extends AbstractEntity {
    private static final long serialVersionUID = -7301209673495171194L;

    @Column(name = "startTime")
    @Temporal(TemporalType.TIMESTAMP)
    public Date startTime;
    @Column(name = "repeatInterval")
    public Integer repeatInterval;
    @Column(name = "repeatCount")
    public Integer repeatCount;

    public TriggerInstanceDao() {
    }

    public TriggerInstanceDao(Date startTime, Integer repeatInterval, Integer repeatCount) {
        this.startTime = startTime;
        this.repeatInterval = repeatInterval;
        this.repeatCount = repeatCount;
    }

    @Override
    public String toString() {
        return "TriggerInstanceDao{" +
                "startTime=" + startTime +
                ", repeatInterval=" + repeatInterval +
                ", repeatCount=" + repeatCount +
                '}';
    }
}
