package com.anicloud.sunny.schedule.domain.strategy;

import com.anicloud.sunny.schedule.domain.schedule.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by huangbin on 7/18/15.
 */
public class StrategyInstance implements Schedulable, ScheduleTaskListener {
    public String strategyId;
    public StrategyState state;

    public Integer stage;
    public List<FeatureInstance> featureInstanceList;

    public StrategyAction action;


    public StrategyInstance(String strategyId, StrategyState state, Integer stage,
                            List<FeatureInstance> featureInstanceList, StrategyAction action) {
        this.strategyId = strategyId;
        this.state = state;
        this.stage = stage;
        this.featureInstanceList = featureInstanceList;
        this.action = action;
    }

    public void  prepareSchedule(ScheduleManager scheduleManager) {
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
                    featureInstance);

            featureInstance.scheduleJob = scheduleJob;

            featureInstance.listener = this;
        }
    }

    @Override
    public boolean start() {
        switch (state) {
            case NONE:
                featureInstanceList.get(stage).start();
                state = StrategyState.RUNNING;
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
                featureInstanceList.get(stage).stop();
                state = StrategyState.DONE;
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
                featureInstanceList.get(stage).pause();
                state = StrategyState.SUSPENDED;
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
                featureInstanceList.get(stage).resume();
                state = StrategyState.RUNNING;
                return true;
            case DONE:
                break;
            default:
                break;
        }
        return false;
    }

    public void next() {
        switch (state) {
            case NONE:
                break;
            case RUNNING:
//                add the next job
                stage += 1;
                state = StrategyState.NONE;
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
    public void onTaskDone() {
        next();
    }
}
