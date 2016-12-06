package com.anicloud.sunny.schedule.domain.strategy;

import com.anicloud.sunny.schedule.domain.schedule.*;

import java.io.Serializable;
import java.util.*;

/**
 * Created by huangbin on 7/18/15.
 */
public class StrategyInstance implements Schedulable, ScheduleStateListener, Serializable {
    private static final long serialVersionUID = 2408334140270041423L;

    public String strategyId;
    public ScheduleState state;
    public Integer stage;
    public List<FeatureInstance> featureInstanceList;

    public StrategyAction action;
    public Long timeStamp;
    public boolean isScheduled;

    public Date startTime;
    public boolean isScheduleNow;
    public boolean isRepeat;
    public String[] repeatWeek;

    transient public ScheduleStateListener listener;
    transient public ScheduleManager scheduleManager;

    public StrategyInstance() {
    }

//    public StrategyInstance(String strategyId, ScheduleState state,
//                            Integer stage, List<FeatureInstance> featureInstanceList,
//                            StrategyAction action,
//                            Long timeStamp) {
//        this.strategyId = strategyId;
//        this.state = state;
//        this.stage = stage;
//        this.featureInstanceList = featureInstanceList;
//        this.action = action;
//        this.timeStamp = timeStamp;
//        this.isScheduled = false;
//    }

    public StrategyInstance(String strategyId, ScheduleState state, Integer stage, List<FeatureInstance> featureInstanceList, StrategyAction action, Long timeStamp, boolean isScheduled, Date startTime, boolean isScheduleNow, boolean isRepeat, String[] repeatWeek) {
        this.strategyId = strategyId;
        this.state = state;
        this.stage = stage;
        this.featureInstanceList = featureInstanceList;
        this.action = action;
        this.timeStamp = timeStamp;
        this.isScheduled = isScheduled;
        this.startTime = startTime;
        this.isScheduleNow = isScheduleNow;
        this.isRepeat = isRepeat;
        this.repeatWeek = repeatWeek;
    }

    public void  prepareSchedule(ScheduleManager scheduleManager, ScheduleStateListener listener, Long hashUserId) {
        List<FeatureInstance> newFeatureList = new ArrayList<>();
        if(featureInstanceList != null && featureInstanceList.size()>0) {
            FeatureInstance startFeature = featureInstanceList.get(0);
            startFeature.scheduleManager = scheduleManager;

            Set<ScheduleTrigger> scheduleTriggers = new HashSet<>();
            startFeature.triggerInstanceList = null;
            ScheduleTrigger startTrigger = new ScheduleTrigger(
                    strategyId + 0,
                    strategyId,
                    strategyId+0+0,
                    strategyId+0,
                    startTime,
                    null,
                    null,
                    repeatWeek,
                    isRepeat
            );
            scheduleTriggers.add(startTrigger);
            ScheduleJob startJob = new ScheduleJob(
                    strategyId + 0,
                    strategyId,
                    ScheduleState.NONE,
                    "",
                    ScheduleJobEntry.class,
                    scheduleTriggers,
                    startFeature,
                    isScheduleNow
            );
            startFeature.hashUserId = hashUserId;
            startFeature.scheduleJob = startJob;
            startFeature.listener = this;
            newFeatureList.add(startFeature);

            for (int i = 0; i < featureInstanceList.size(); i++) {
                FeatureInstance featureInstance = featureInstanceList.get(i);
                featureInstance.scheduleManager = scheduleManager;
                featureInstance.hashUserId = hashUserId;
                featureInstance.listener = this;
            }
            newFeatureList.addAll(featureInstanceList);
        }
        featureInstanceList = newFeatureList;
        this.listener = listener;
        this.scheduleManager = scheduleManager;
        this.isScheduled = true;
    }

    @Override
    public boolean start() {
        switch (state) {
            case NONE:
                featureInstanceList.get(stage).start();
                return true;
            case RUNNING:
                break;
            case SUSPENDED:
                break;
            case DONE:
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean stop() {
        scheduleManager.deleteJob(featureInstanceList.get(0).scheduleJob);
        switch (state) {
            case NONE:
                break;
            case RUNNING:
            case SUSPENDED:
                featureInstanceList.get(stage).stop();
                return true;
            case DONE:
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean pause() {
        if (!this.isScheduled) {
            return false;
        }
        switch (state) {
            case NONE:
            case RUNNING:
                featureInstanceList.get(stage).pause();
                return true;
            case SUSPENDED:
                break;
            case DONE:
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean resume() {
        if (!this.isScheduled) {
            return false;
        }
        switch (state) {
            case NONE:
                break;
            case RUNNING:
                break;
            case SUSPENDED:
                featureInstanceList.get(stage).resume();
                return true;
            case DONE:
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onScheduleStateChanged(Object src, ScheduleState state) {
        switch (state) {
            case NONE:
                break;
            case SUSPENDED:
                this.state = ScheduleState.SUSPENDED;
                listener.onScheduleStateChanged(this, this.state);
                break;
            case DONE:
                stage++;
                if (stage < featureInstanceList.size()) {
                    this.state = ScheduleState.NONE;
                    FeatureInstance featureInstance = featureInstanceList.get(stage);
                    Set<ScheduleTrigger> scheduleTriggers = new HashSet<>();
                    Date startTime = new Date(System.currentTimeMillis()+featureInstanceList.get(stage-1).intervalTime);
                    ScheduleTrigger scheduleTrigger = new ScheduleTrigger(
                            strategyId + stage,
                            strategyId,
                            strategyId+stage+0,
                            strategyId+stage,
                            startTime,
                            null,
                            null,
                            null,
                            false
                    );
                    scheduleTriggers.add(scheduleTrigger);
                    featureInstance.scheduleJob = null;
                    featureInstance.scheduleJob = new ScheduleJob(
                            strategyId + stage,
                            strategyId,
                            ScheduleState.NONE,
                            "",
                            ScheduleJobEntry.class,
                            scheduleTriggers,
                            featureInstance,
                            false
                    );

                    start();
                } else {
                    this.state = ScheduleState.NONE;
                    listener.onScheduleStateChanged(this, ScheduleState.DONE);
                }
                break;
            case RUNNING:
                this.state = ScheduleState.RUNNING;
                listener.onScheduleStateChanged(this, this.state);
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StrategyInstance)) return false;

        StrategyInstance that = (StrategyInstance) o;

        return strategyId.equals(that.strategyId);

    }

    @Override
    public int hashCode() {
        return strategyId.hashCode();
    }
}
