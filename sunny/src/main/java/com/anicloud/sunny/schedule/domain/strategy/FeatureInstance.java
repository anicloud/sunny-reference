package com.anicloud.sunny.schedule.domain.strategy;


import com.anicloud.sunny.application.service.holder.SpringContextHolder;
import com.anicloud.sunny.application.service.strategy.CurrentFeatureEventHandler;
import com.anicloud.sunny.application.service.strategy.CurrentFeatureService;
import com.anicloud.sunny.infrastructure.persistence.domain.device.CurrentFeatureInstanceDao;
import com.anicloud.sunny.schedule.domain.schedule.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.util.List;

/**
 * Created by huangbin on 7/18/15.
 */
public class FeatureInstance implements ScheduleTask, Schedulable, Serializable {
    private final static Logger log = LoggerFactory.getLogger(FeatureInstance.class);
    public String featureId;
    public String deviceId;
    public ScheduleState state;
    public Integer stage;
    public List<FunctionInstance> functionInstanceList;
    public List<TriggerInstance> triggerInstanceList;
    public boolean isScheduleNow;
    public Long hashUserId;
    transient public ScheduleJob scheduleJob;
    transient public ScheduleManager scheduleManager;
    transient public ScheduleStateListener listener;

    //private CurrentFeatureService currentFeatureService = (CurrentFeatureEventHandler) SpringContextHolder.getBean("currentFeatureEventHandler");

    public Integer reenter = -1;

    public FeatureInstance() {
    }

    public FeatureInstance(String featureId, String deviceId,
                           ScheduleState state, Integer stage,
                           List<FunctionInstance> functionInstanceList,
                           List<TriggerInstance> triggerInstanceList,
                           boolean isScheduleNow) {
        this.featureId = featureId;
        this.deviceId = deviceId;
        this.state = state;
        this.stage = stage;
        this.functionInstanceList = functionInstanceList;
        this.triggerInstanceList = triggerInstanceList;
        this.isScheduleNow = isScheduleNow;
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
            if (state == ScheduleState.RUNNING) {
                functionInstanceList.get(stage).execute(this.hashUserId, this.deviceId);
                stage++;
            } else {
                break;
            }
        }

        if (stage == functionInstanceList.size()) {
            state = ScheduleState.DONE;
            listener.onScheduleStateChanged(this, ScheduleState.DONE);
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
                state = ScheduleState.RUNNING;
//                CurrentFeatureInstanceDao dao = new CurrentFeatureInstanceDao(featureId,deviceId,1,hashUserId);
//                CurrentFeatureInstanceDao newDao = currentFeatureService.saveCurrentFeature(dao);
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
        switch (state) {
            case NONE:
                break;
            case RUNNING:
            case SUSPENDED:
                scheduleManager.deleteJob(scheduleJob);
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
        switch (state) {
            case NONE:
            case RUNNING:
                scheduleManager.pauseJob(scheduleJob);
                state = ScheduleState.SUSPENDED;
                listener.onScheduleStateChanged(this, state);
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
        switch (state) {
            case NONE:
                break;
            case RUNNING:
                break;
            case SUSPENDED:
                scheduleManager.resumeJob(scheduleJob);
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
}
