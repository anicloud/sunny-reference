package com.anicloud.sunny.schedule.domain.strategy;


import com.anicloud.sunny.schedule.domain.schedule.*;

import java.util.List;

/**
 * Created by huangbin on 7/18/15.
 */
public class FeatureInstance implements ScheduleTask, Schedulable {
    public String featureId;
    public FeatureState state;
    public Integer stage;
    public List<FunctionInstance> functionInstanceList;
    public List<TriggerInstance> triggerInstanceList;
//
    public ScheduleJob scheduleJob;
    public ScheduleManager scheduleManager;
    public ScheduleTaskListener listener;

    public static Integer reenter = -1;

    public FeatureInstance(String featureId, FeatureState state, Integer stage,
                           List<FunctionInstance> functionInstanceList, List<TriggerInstance> triggerInstanceList) {
        this.featureId = featureId;
        this.state = state;
        this.stage = stage;
        this.functionInstanceList = functionInstanceList;
        this.triggerInstanceList = triggerInstanceList;
    }

    @Override
    public void run() {
        synchronized (reenter) {
            reenter++;
            if (reenter > 0) {
                reenter--;
                return;
            }
        }

        for (int i= stage; stage < functionInstanceList.size(); i++) {
            if (state == FeatureState.RUNNING) {
                functionInstanceList.get(stage).execute();
            } else {
                break;
            }
        }

        if (stage == functionInstanceList.size()) {
            state = FeatureState.DONE;
            listener.onTaskDone();
        }

        synchronized (reenter) {
            reenter--;
        }
    }

    @Override
    public boolean start() {
        switch (state) {
            case NONE:
                scheduleManager.addJob(scheduleJob);
                state = FeatureState.RUNNING;
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
        switch (state) {
            case NONE:
                break;
            case RUNNING:
            case SUSPENDED:
                scheduleManager.deleteJob(scheduleJob);
                state = FeatureState.DONE;
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
        switch (state) {
            case NONE:
                scheduleManager.pauseJob(scheduleJob);
                state = FeatureState.SUSPENDED;
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
        switch (state) {
            case NONE:
                break;
            case RUNNING:
                break;
            case SUSPENDED:
                scheduleManager.resumeJob(scheduleJob);
                state = FeatureState.RUNNING;
                return true;
            case DONE:
                break;
            default:
                break;
        }
        return false;
    }
}
