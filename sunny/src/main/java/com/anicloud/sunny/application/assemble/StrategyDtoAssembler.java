package com.anicloud.sunny.application.assemble;

import com.ani.agent.service.commons.object.enumeration.DataType;
import com.ani.octopus.commons.stub.type.DataPrimitiveType;
import com.ani.octopus.commons.stub.type.DataPrimitiveTypes;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.domain.model.device.FeatureFunction;
import com.anicloud.sunny.domain.model.device.FunctionArgument;
import com.anicloud.sunny.domain.model.device.FeatureArgValue;
import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstance;
import com.anicloud.sunny.domain.model.strategy.FeatureTrigger;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.infrastructure.persistence.domain.share.TriggerType;
import com.anicloud.sunny.schedule.domain.adapter.DtoAdapter;
import com.anicloud.sunny.schedule.domain.strategy.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

/**
 * Created by zhaoyu on 15-6-17.
 */
public class StrategyDtoAssembler {
    private StrategyDtoAssembler() {}

    public static Strategy toStrategy(StrategyDto strategyDto) {
        if (strategyDto == null) {
            return null;
        }

        Strategy strategy = new Strategy(
                strategyDto.strategyId,
                strategyDto.strategyName,
                strategyDto.description,
                UserDtoAssembler.toUser(strategyDto.owner),
                strategyDto.startTime,
                strategyDto.repeatWeek,
                strategyDto.isRepeat,
                strategyDto.isScheduleNow,
                DeviceFeatureInstanceDtoAssembler.toFeatureInstanceList(strategyDto.deviceFeatureInstanceList)
        );
        // to instance
        StrategyInstance instance = toStrategyInstance(strategy);
        strategy.strategyInstance = instance;
        return strategy;
    }

    public static StrategyDto toDto(Strategy strategy) {
        if (strategy == null) {
            return null;
        }

        StrategyDto strategyDto = new StrategyDto(
                strategy.strategyId,
                strategy.strategyName,
                strategy.description,
                strategy.startTime,
                strategy.isScheduleNow,
                strategy.isRepeat,
                strategy.repeatWeek,
                UserDtoAssembler.fromUser(strategy.owner),
                DeviceFeatureInstanceDtoAssembler.toDtoList(strategy.deviceFeatureInstanceList)
        );
        if (strategy.strategyInstance != null) {
            strategyDto.strategyInstanceDto = DtoAdapter.toStrategyInstanceDto(strategy.strategyInstance);
        }
        return strategyDto;
    }

    public static List<Strategy> toStrategyList(List<StrategyDto> strategyDtoList) {
        List<Strategy> strategyList = new ArrayList<>();
        for (StrategyDto strategyDto : strategyDtoList) {
            strategyList.add(toStrategy(strategyDto));
        }
        return strategyList;
    }

    public static List<StrategyDto> toDtoList(List<Strategy> strategyList) {
        List<StrategyDto> strategyDtoList = new ArrayList<>();
        for (Strategy strategy : strategyList) {
            strategyDtoList.add(toDto(strategy));
        }
        return strategyDtoList;
    }

    public static StrategyInstance toStrategyInstance(Strategy strategy) {

        List<FeatureInstance> featureInstanceList = new ArrayList<>();
        for (DeviceFeatureInstance deviceFeatureInstance : strategy.deviceFeatureInstanceList) {
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

        StrategyInstance instance = new StrategyInstance(
                strategy.strategyId,
                ScheduleState.NONE,
                0,
                featureInstanceList,
                StrategyAction.START,
                System.currentTimeMillis(),
                false,
                strategy.startTime,
                strategy.isScheduleNow,
                strategy.isRepeat,
                strategy.repeatWeek
        );
        return instance;
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

//    public static Strategy fromRunDeviceFeatureInstanceDto(UserDto userDto, DeviceFeatureInstanceDto deviceFeatureInstanceDto) {
//        DeviceFeatureInstance deviceFeatureInstance = DeviceFeatureInstanceDtoAssembler.
//                toFeatureInstance(deviceFeatureInstanceDto);
//
//        Strategy strategy = new Strategy(
//                NumGenerate.generate(),
//                Constants.STRATEGY_AS_DEVICE_FEATURE_RUN_NAME,
//                null,
//                UserDtoAssembler.toUser(userDto),
//                Arrays.asList(deviceFeatureInstance)
//        );
//        // to instance
//        StrategyInstance instance = toStrategyInstance(strategy);
//        strategy.strategyInstance = instance;
//        return strategy;
//    }
}
