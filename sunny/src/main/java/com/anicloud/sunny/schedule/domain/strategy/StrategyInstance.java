package com.anicloud.sunny.schedule.domain.strategy;

import com.anicloud.sunny.schedule.domain.schedule.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by huangbin on 7/18/15.
 */
public class StrategyInstance implements Schedulable, ScheduleStateListener {
    public String strategyId;
    public ScheduleState state;
    public Integer stage;
    public List<FeatureInstance> featureInstanceList;

    public StrategyAction action;
    public Long timeStamp;

    public boolean isScheduled;
    public ScheduleStateListener listener;

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

    public void  prepareSchedule(ScheduleManager scheduleManager, ScheduleStateListener listener, String accessToken) {
        for (FeatureInstance featureInstance : featureInstanceList) {
            featureInstance.scheduleManager = scheduleManager;

            Set<ScheduleTrigger> scheduleTriggerList = new HashSet<>();
            for (int i=0; i<featureInstance.triggerInstanceList.size(); i++) {
                TriggerInstance triggerInstance = featureInstance.triggerInstanceList.get(i);
                ScheduleTrigger scheduleTrigger = new ScheduleTrigger(
                        featureInstance.featureId,
                        strategyId,
                        featureInstance.featureId+i,
                        featureInstance.featureId,
                        triggerInstance.startTime,
                        triggerInstance.repeatInterval,
                        triggerInstance.repeatCount);
                scheduleTriggerList.add(scheduleTrigger);
            }
            ScheduleJob scheduleJob = new ScheduleJob(
                    featureInstance.featureId,
                    strategyId,
                    ScheduleState.NONE,
                    "",
                    ScheduleJobEntry.class,
                    scheduleTriggerList,
                    featureInstance,
                    featureInstance.isScheduleNow);

            featureInstance.accessToken = accessToken;
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
                if (stage < featureInstanceList.size()) {
                    featureInstanceList.get(stage).start();
                    state = ScheduleState.RUNNING;
                } else {
                    state = ScheduleState.DONE;
                }
                listener.onScheduleStateChanged(this, state);
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
                state = ScheduleState.DONE;
                listener.onScheduleStateChanged(this, state);
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
                featureInstanceList.get(stage).pause();
                state = ScheduleState.SUSPENDED;
                listener.onScheduleStateChanged(this, state);
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
                state = ScheduleState.RUNNING;
                listener.onScheduleStateChanged(this, state);
                return true;
            case DONE:
                break;
            default:
                break;
        }
        return false;
    }

    private void next() {
        switch (state) {
            case NONE:
                break;
            case RUNNING:
//                add the next job
                stage += 1;
                state = ScheduleState.NONE;
                start();
                break;
            case SUSPENDED:
                break;
            case DONE:
                break;
            default:
                break;
        }
    }

    @Override
    public void onScheduleStateChanged(Object src, ScheduleState state) {
        if (state == ScheduleState.DONE) {
            next();
        }
    }
}
