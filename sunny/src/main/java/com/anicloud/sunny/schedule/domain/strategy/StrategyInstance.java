package com.anicloud.sunny.schedule.domain.strategy;

import com.anicloud.sunny.schedule.domain.schedule.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    transient public ScheduleStateListener listener;

    public StrategyInstance() {
    }

    public StrategyInstance(String strategyId, ScheduleState state,
                            Integer stage, List<FeatureInstance> featureInstanceList,
                            StrategyAction action,
                            Long timeStamp) {
        this.strategyId = strategyId;
        this.state = state;
        this.stage = stage;
        this.featureInstanceList = featureInstanceList;
        this.action = action;
        this.timeStamp = timeStamp;
        this.isScheduled = false;
    }

    public void  prepareSchedule(ScheduleManager scheduleManager, ScheduleStateListener listener, String hashUserId) {
        for (int i=0; i<featureInstanceList.size(); i++) {
            FeatureInstance featureInstance = featureInstanceList.get(i);
            featureInstance.scheduleManager = scheduleManager;

            Set<ScheduleTrigger> scheduleTriggerList = new HashSet<>();
            for (int j=0; j<featureInstance.triggerInstanceList.size(); j++) {
                TriggerInstance triggerInstance = featureInstance.triggerInstanceList.get(j);
                ScheduleTrigger scheduleTrigger = new ScheduleTrigger(
                        strategyId + i,
                        strategyId,
                        strategyId + i + j,
                        strategyId + i,
                        triggerInstance.startTime,
                        triggerInstance.repeatInterval,
                        triggerInstance.repeatCount);
                scheduleTriggerList.add(scheduleTrigger);
            }
            ScheduleJob scheduleJob = new ScheduleJob(
                    strategyId + i,
                    strategyId,
                    ScheduleState.NONE,
                    "",
                    ScheduleJobEntry.class,
                    scheduleTriggerList,
                    featureInstance,
                    featureInstance.isScheduleNow);

            featureInstance.hashUserId = hashUserId;
            featureInstance.scheduleJob = scheduleJob;
            featureInstance.listener = this;
        }
        this.listener = listener;
        this.isScheduled = true;
    }

    @Override
    public boolean start() {
        if (!this.isScheduled) {
            return false;
        }
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
        if (!this.isScheduled) {
            return false;
        }
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
                    start();
                } else {
                    this.state = ScheduleState.DONE;
                    listener.onScheduleStateChanged(this, this.state);
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
