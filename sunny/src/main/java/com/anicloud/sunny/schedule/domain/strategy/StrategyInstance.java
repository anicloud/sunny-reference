package com.anicloud.sunny.schedule.domain.strategy;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.service.device.DeviceAndUserRelationServcie;
import com.anicloud.sunny.application.service.holder.SpringContextHolder;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.schedule.domain.schedule.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * Created by huangbin on 7/18/15.
 */
public class StrategyInstance implements Schedulable, ScheduleStateListener, Serializable {
    private static final long serialVersionUID = 2408334140270041423L;

    public Strategy strategyModel;
    public String strategyInstanceId;
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
    transient public static ObjectMapper objectMapper = new ObjectMapper();

    public StrategyInstance() {
    }

    public StrategyInstance(Strategy strategyModel,String strategyInstanceId, ScheduleState state, Integer stage, List<FeatureInstance> featureInstanceList,
                            StrategyAction action, Long timeStamp, boolean isScheduled,Date startTime, boolean isScheduleNow, boolean isRepeat, String[] repeatWeek) {
        this.strategyModel = strategyModel;
        this.strategyInstanceId = strategyInstanceId;
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
//        List<FeatureInstance> newFeatureList = new ArrayList<>();
        if(featureInstanceList != null && featureInstanceList.size()>0) {
            FeatureInstance startFeature = featureInstanceList.get(0);
            startFeature.scheduleManager = scheduleManager;

            Set<ScheduleTrigger> scheduleTriggers = new HashSet<>();
            startFeature.triggerInstanceList = null;
            Date featureStartTime = new Date(startTime.getTime()+startFeature.intervalTime);
            ScheduleTrigger startTrigger = new ScheduleTrigger(
                    strategyInstanceId + 0,
                    strategyInstanceId,
                    strategyInstanceId+0+0,
                    strategyInstanceId+0,
                    featureStartTime,
                    null,
                    null,
                    repeatWeek,
                    isRepeat
            );
            scheduleTriggers.add(startTrigger);
            ScheduleJob startJob = new ScheduleJob(
                    strategyInstanceId + 0,
                    strategyInstanceId,
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
//            newFeatureList.add(startFeature);

            for (int i = 0; i < featureInstanceList.size(); i++) {
                FeatureInstance featureInstance = featureInstanceList.get(i);
                featureInstance.scheduleManager = scheduleManager;
                featureInstance.hashUserId = hashUserId;
                featureInstance.listener = this;
            }
//            newFeatureList.addAll(featureInstanceList);
        }
//        featureInstanceList = newFeatureList;
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
                //更新initparam
                updateInitParam();

                stage++;
                if (stage < featureInstanceList.size()) {
                    this.state = ScheduleState.NONE;
                    FeatureInstance featureInstance = featureInstanceList.get(stage);
                    Set<ScheduleTrigger> scheduleTriggers = new HashSet<>();
                    Date _startTime = new Date(System.currentTimeMillis()+featureInstanceList.get(stage).intervalTime);
                    ScheduleTrigger scheduleTrigger = new ScheduleTrigger(
                            strategyInstanceId + stage,
                            strategyInstanceId,
                            strategyInstanceId+stage+0,
                            strategyInstanceId+stage,
                            _startTime,
                            null,
                            null,
                            null,
                            false
                    );
                    scheduleTriggers.add(scheduleTrigger);
                    featureInstance.scheduleJob = null;
                    featureInstance.scheduleJob = new ScheduleJob(
                            strategyInstanceId + stage,
                            strategyInstanceId,
                            ScheduleState.NONE,
                            "",
                            ScheduleJobEntry.class,
                            scheduleTriggers,
                            featureInstance,
                            false
                    );

                    start();
                } else {
                    if(!isRepeat) {
                        this.state = ScheduleState.NONE;
                        listener.onScheduleStateChanged(this, ScheduleState.DONE);
                    } else {
                        stage = 0;
                        listener.onScheduleStateChanged(this,ScheduleState.RUNNING);
                    }
                }
                break;
            case RUNNING:
                this.state = ScheduleState.RUNNING;
                listener.onScheduleStateChanged(this, this.state);
                break;
        }
    }

    private void updateInitParam(){
        try{
            DeviceAndUserRelationServcie relationService = (DeviceAndUserRelationServcie) SpringContextHolder.getBean("deviceAndUserRelationServcie");
            JmsTemplate paramJmsTemplate = (JmsTemplate) SpringContextHolder.getBean("paramJmsTemplate");
            FeatureInstance featureInstance = featureInstanceList.get(stage);
            DeviceAndUserRelationDto relationDto = relationService.getDeviceAndUserRelation(featureInstance.deviceId,featureInstance.hashUserId);

            Map<String,String> params;
            boolean isChanged = false;
            if (!StringUtils.isEmpty(relationDto.initParam))
                params =  objectMapper.readValue(relationDto.initParam,Map.class);
            else
                params = new HashMap<>();
            for (FunctionInstance func : featureInstance.functionInstanceList) {
                if(func.inputList != null) {
                    for (Argument arg : func.inputList) {
                        params.put(arg.name,arg.value);
                        isChanged = true;
                    }
                }
            }
            if (isChanged) {
                relationDto.initParam = objectMapper.writeValueAsString(params);
                relationService.modifyRelation(relationDto);
                paramJmsTemplate.convertAndSend(relationDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StrategyInstance)) return false;

        StrategyInstance that = (StrategyInstance) o;

        return strategyInstanceId.equals(that.strategyInstanceId);

    }

    @Override
    public int hashCode() {
        return strategyInstanceId.hashCode();
    }
}
