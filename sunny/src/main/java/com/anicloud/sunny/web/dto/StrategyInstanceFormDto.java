package com.anicloud.sunny.web.dto;

import com.ani.agent.service.commons.object.enumeration.DataType;
import com.ani.octopus.commons.stub.type.DataPrimitiveType;
import com.ani.octopus.commons.stub.type.DataPrimitiveTypes;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.domain.model.device.FeatureArgValue;
import com.anicloud.sunny.domain.model.device.FeatureFunction;
import com.anicloud.sunny.domain.model.device.FunctionArgument;
import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstance;
import com.anicloud.sunny.domain.model.strategy.FeatureTrigger;
import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.infrastructure.persistence.domain.share.TriggerType;
import com.anicloud.sunny.schedule.domain.strategy.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

/**
 * Created by wyf on 16-12-29.
 */
public class StrategyInstanceFormDto {

    public String strategyInstanceId;
    public StrategyFormDto strategyModel;
    public Integer stage;
    public StrategyAction action;
    public ScheduleState state;
    public Date startTime;
    public boolean isScheduleNow;
    public boolean isRepeat;
    public String[] repeatWeek;

    public static StrategyInstance convertToStrategyInstance(StrategyInstanceFormDto strategyInstanceFormDto,User user){
        StrategyInstance strategyInstance  = new StrategyInstance();
        if(strategyInstanceFormDto != null) {
            strategyInstance.strategyInstanceId = strategyInstanceFormDto.strategyInstanceId;
            strategyInstance.strategyModel = StrategyFormDto.convertToStrategy(strategyInstanceFormDto.strategyModel,user);
            strategyInstance.startTime = strategyInstanceFormDto.startTime;
            strategyInstance.isRepeat = strategyInstanceFormDto.isRepeat;
            strategyInstance.isScheduleNow = strategyInstanceFormDto.isScheduleNow;
            strategyInstance.repeatWeek = strategyInstanceFormDto.repeatWeek;
        }
        strategyInstance.featureInstanceList = prepareFeatureInstanceList(strategyInstance.strategyModel.deviceFeatureInstanceList);
        return  strategyInstance;
    }

    public static List<FeatureInstance> prepareFeatureInstanceList(List<DeviceFeatureInstance> deviceFeatureInstanceList){
        List<FeatureInstance> featureInstanceList = new ArrayList<>();
        for (DeviceFeatureInstance deviceFeatureInstance : deviceFeatureInstanceList) {
            DeviceFeature deviceFeature = deviceFeatureInstance.deviceFeature;
            List<FeatureFunction> featureFunctionList = deviceFeature.featureFunctionList;

            List<FunctionInstance> functionInstanceList = new ArrayList<>();
            for (FeatureFunction featureFunction : featureFunctionList) {
                FunctionInstance functionInstance =
                        toFunctionInstance(deviceFeature, deviceFeatureInstance.featureArgValueList, featureFunction);
                functionInstanceList.add(functionInstance);
            }

            FeatureInstance featureInstance = new FeatureInstance(
                    deviceFeature.featureId,
                    deviceFeatureInstance.device.identificationCode,
                    ScheduleState.NONE,
                    0,
                    functionInstanceList,
                    toTriggerInstanceList(deviceFeatureInstance.triggerList),
                    deviceFeatureInstance.isScheduleNow,
                    deviceFeatureInstance.intervalTime
            );
            featureInstanceList.add(featureInstance);
        }
        return featureInstanceList;
    }

    public static FunctionInstance toFunctionInstance(DeviceFeature deviceFeature, List<FeatureArgValue> valueList, FeatureFunction featureFunction) {
        List<Argument> inputList = new ArrayList<>();
        for (FunctionArgument functionArgument : featureFunction.inputArgList) {
            String value = getArgumentValue(deviceFeature, valueList, featureFunction.featureFunctionId, functionArgument.name);
            Argument argument = new Argument(
                    functionArgument.name,
                    value,
                    getArgumentType(functionArgument.dataType)
            );
            inputList.add(argument);
        }

        FunctionInstance FunctionInstance = new FunctionInstance(
                featureFunction.featureFunctionId,
                featureFunction.stubId,
                featureFunction.groupId,
                featureFunction.functionName,
                inputList,
                null
        );
        return FunctionInstance;
    }

    public static TriggerInstance toTriggerInstance(FeatureTrigger featureTrigger) {
        if (featureTrigger == null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.setDateFormat(new SimpleDateFormat(Constants.TIME_TRIGGER_DATE_PATTERN));
        TriggerInstance triggerInstance = null;
        if (featureTrigger.triggerType == TriggerType.TIMER) {
            try {
                triggerInstance = objectMapper.readValue(featureTrigger.value, TriggerInstance.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return triggerInstance;
    }

    public static List<TriggerInstance> toTriggerInstanceList(List<FeatureTrigger> featureTriggerList) {
        if (featureTriggerList == null) return null;
        List<TriggerInstance> triggerInstanceList = new ArrayList<>();
        for (FeatureTrigger featureTrigger : featureTriggerList) {
            triggerInstanceList.add(toTriggerInstance(featureTrigger));
        }
        return triggerInstanceList;
    }

    public static DataPrimitiveType getArgumentType(DataType dataType) {
        DataPrimitiveType type = null;
        if (dataType != null) {
            switch (dataType) {
                case INTEGER:
                    type = new DataPrimitiveType(DataPrimitiveTypes.INTEGER);
                    break;
                case PERCENTAGE:
                    type = new DataPrimitiveType(DataPrimitiveTypes.PERCENTAGE);
                    break;
                case FLOAT:
                    type = new DataPrimitiveType(DataPrimitiveTypes.FLOAT);
                    break;
                case BOOLEAN:
                    type = new DataPrimitiveType(DataPrimitiveTypes.BOOLEAN);
                    break;
                case STRING:
                    type = new DataPrimitiveType(DataPrimitiveTypes.STRING);
                    break;
                default:
                    type = new DataPrimitiveType(DataPrimitiveTypes.STRING);
                    break;
            }
        }
        return type;
    }

    public static String getArgumentValue(DeviceFeature deviceFeature, List<FeatureArgValue> valueList,  String functionId, String argName) {
        String argKey = null;
        String functionArg = functionId + ":" +argName;
        List<Map<String, List<String>>> mapList = deviceFeature.featureArgFuncArgMapList;
        for (Map<String, List<String>> map : mapList) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                List<String> values = map.get(key);
                for (String value : values) {
                    if (value.equals(functionArg)) {
                        argKey = key;
                        break;
                    }
                }
            }
        }

        String value = null;
        for (FeatureArgValue featureArgValue : valueList) {
            if (featureArgValue.argName.equals(argKey)) {
                value = featureArgValue.value;
                break;
            }
        }
        return value;
    }

    public static StrategyInstanceFormDto convertToStrategyForm(StrategyInstance strategyInstance){
        StrategyInstanceFormDto strategyInstanceFormDto = new StrategyInstanceFormDto();
        if(strategyInstance != null) {
            strategyInstanceFormDto.strategyInstanceId = strategyInstance.strategyInstanceId;
            strategyInstanceFormDto.strategyModel = StrategyFormDto.convertToStrategyFormDto(strategyInstance.strategyModel);
            strategyInstanceFormDto.state = strategyInstance.state;
            strategyInstanceFormDto.stage = strategyInstance.stage;
            strategyInstanceFormDto.action = strategyInstance.action;
            strategyInstanceFormDto.startTime = strategyInstance.startTime;
            strategyInstanceFormDto.isScheduleNow = strategyInstance.isScheduleNow;
            strategyInstanceFormDto.isRepeat = strategyInstance.isRepeat;
            strategyInstanceFormDto.repeatWeek = strategyInstance.repeatWeek;
        }
        return strategyInstanceFormDto;
    }


    public static List<StrategyInstanceFormDto> convertToStrategyForms(List<StrategyInstance> StrategyInstance){
        List<StrategyInstanceFormDto> strategyInstanceFormDtos = new ArrayList<>();
        if(StrategyInstance != null) {
            for (StrategyInstance strategyInstance : StrategyInstance) {
                strategyInstanceFormDtos.add(convertToStrategyForm(strategyInstance));
            }
        }
        return strategyInstanceFormDtos;
    }
}
