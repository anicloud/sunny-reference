package com.anicloud.sunny.schedule.persistence.dao;

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
    @Column(name = "strategyId", nullable = false, unique = true)
    public String strategyId;
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

    public static String repeatWeektoString(String[] weeks){
        String result = null;
        if(weeks != null && weeks.length >0 ) {
            result = Arrays.toString(weeks);
            result = result.substring(1,result.length()-1);
        }
        return result;
    }

    public static String[] repeatWeektoArray(String repeatWeek){
        if(repeatWeek != null)
            return repeatWeek.replaceAll(" ","").split(",");
        else
            return null;
    }

    public StrategyInstanceDao() {
    }

    public StrategyInstanceDao(String strategyId, ScheduleState state, Integer stage,
                               List<FeatureInstanceDao> featureInstanceDaoList,
                               StrategyAction action,
                               Long timeStamp,Date startTime,String repeatWeek,
                               boolean isRepeat, boolean isScheduleNow) {
        this.strategyId = strategyId;
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
