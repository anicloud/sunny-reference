package com.anicloud.sunny.schedule.domain.adapter;

import com.anicloud.sunny.application.assemble.StrategyDtoAssembler;
import com.anicloud.sunny.schedule.domain.strategy.*;
import com.anicloud.sunny.schedule.dto.*;
import com.sun.org.apache.xpath.internal.Arg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
public class DtoAdapter {

    public static ArgumentDto toArgumentDto(Argument argument) {
        return new ArgumentDto(argument.name, argument.value);
    }

    public static List<ArgumentDto> toArgumentDtoList(List<Argument> argumentList) {
        List<ArgumentDto> argumentDtoList = new ArrayList<>();
        if (argumentList != null) {
            for (Argument argument : argumentList) {
                argumentDtoList.add(toArgumentDto(argument));
            }
        }
        return argumentDtoList;
    }

    public static FunctionInstanceDto toFunctionInstanceDto(FunctionInstance functionInstance) {
        FunctionInstanceDto functionInstanceDto = new FunctionInstanceDto(
                functionInstance.functionId,
                functionInstance.name,
                functionInstance.group,
                toArgumentDtoList(functionInstance.inputList),
                toArgumentDtoList(functionInstance.outputList));
        return functionInstanceDto;
    }

    public static List<FunctionInstanceDto> toFunctionInstanceDtoList(List<FunctionInstance> functionInstanceList) {
        List<FunctionInstanceDto> functionInstanceDtoList = new ArrayList<>();
        if (functionInstanceList != null ) {
            for (FunctionInstance functionInstance : functionInstanceList) {
                functionInstanceDtoList.add(toFunctionInstanceDto(functionInstance));
            }
        }
        return functionInstanceDtoList;
    }

    public static TriggerInstanceDto toTriggerInstanceDto(TriggerInstance triggerInstance) {
        TriggerInstanceDto triggerInstanceDto = new TriggerInstanceDto(
                triggerInstance.startTime,
                triggerInstance.repeatInterval,
                triggerInstance.repeatCount);
        return triggerInstanceDto;
    }

    public static List<TriggerInstanceDto> toTriggerInstanceDtoList(List<TriggerInstance> triggerInstanceList) {
        List<TriggerInstanceDto> triggerInstanceDtoList = new ArrayList<>();
        if (triggerInstanceList != null) {
            for (TriggerInstance triggerInstance : triggerInstanceList) {
                triggerInstanceDtoList.add(toTriggerInstanceDto(triggerInstance));
            }
        }
        return triggerInstanceDtoList;
    }

    public static FeatureInstanceDto toFeatureInstanceDto(FeatureInstance featureInstance) {
        FeatureInstanceDto featureInstanceDto = new FeatureInstanceDto(
                featureInstance.featureId,
                featureInstance.state,
                featureInstance.stage,
                toFunctionInstanceDtoList(featureInstance.functionInstanceList),
                toTriggerInstanceDtoList(featureInstance.triggerInstanceList));
        return featureInstanceDto;
    }

    public static List<FeatureInstanceDto> toFeatureInstanceDtoList(List<FeatureInstance> featureInstanceList) {
        List<FeatureInstanceDto> featureInstanceDtoList = new ArrayList<>();
        if (featureInstanceList != null) {
            for (FeatureInstance featureInstance : featureInstanceList) {
                featureInstanceDtoList.add(toFeatureInstanceDto(featureInstance));
            }
        }
        return featureInstanceDtoList;
    }

    public static StrategyInstanceDto toStrategyInstanceDto(StrategyInstance strategyInstance) {
        StrategyInstanceDto strategyInstanceDto = new StrategyInstanceDto(
                strategyInstance.strategyId,
                strategyInstance.state,
                strategyInstance.stage,
                toFeatureInstanceDtoList(strategyInstance.featureInstanceList),
                strategyInstance.action,
                strategyInstance.timeStamp);
        return strategyInstanceDto;
    }

    public static Argument fromArgumentDto(ArgumentDto argumentDto) {
        Argument argument = new Argument(argumentDto.name, argumentDto.value);
        return  argument;
    }

    public static List<Argument> fromArgumentDtoList(List<ArgumentDto> argumentDtoList) {
        List<Argument> argumentList = new LinkedList<>();
        if (argumentDtoList != null) {
            for (ArgumentDto argumentDto : argumentDtoList) {
                argumentList.add(fromArgumentDto(argumentDto));
            }
        }
        return argumentList;
    }

    public static FunctionInstance fromFunctionInstanceDto(FunctionInstanceDto functionInstanceDto) {
        FunctionInstance functionInstance = new FunctionInstance(
                functionInstanceDto.functionId,
                functionInstanceDto.name,
                functionInstanceDto.group,
                fromArgumentDtoList(functionInstanceDto.inputList),
                fromArgumentDtoList(functionInstanceDto.outputList));
        return functionInstance;
    }

    public static List<FunctionInstance> fromFunctionInstanceDtoList(List<FunctionInstanceDto> functionInstanceDtoList) {
        List<FunctionInstance> functionInstanceList = new ArrayList<>();
        if (functionInstanceDtoList != null) {
            for (FunctionInstanceDto functionInstanceDto : functionInstanceDtoList) {
                functionInstanceList.add(fromFunctionInstanceDto(functionInstanceDto));
            }
        }
        return functionInstanceList;
    }

    public static TriggerInstance fromTriggerInstanceDto(TriggerInstanceDto triggerInstanceDto) {
        TriggerInstance triggerInstance = new TriggerInstance(
                triggerInstanceDto.startTime,
                triggerInstanceDto.repeatInterval,
                triggerInstanceDto.repeatCount);
        return triggerInstance;
    }

    public static List<TriggerInstance> fromTriggerInstanceDtoList(List<TriggerInstanceDto> triggerInstanceDtoList) {
        List<TriggerInstance> triggerInstanceList = new ArrayList<>();
        if (triggerInstanceDtoList != null) {
            for (TriggerInstanceDto triggerInstanceDto : triggerInstanceDtoList) {
                triggerInstanceList.add(fromTriggerInstanceDto(triggerInstanceDto));
            }
        }
        return triggerInstanceList;
    }

    public static FeatureInstance fromFeatureInstanceDto(FeatureInstanceDto featureInstanceDto) {
        FeatureInstance featureInstance = new FeatureInstance(
                featureInstanceDto.featureId,
                featureInstanceDto.deviceDto.identificationCode,
                featureInstanceDto.state,
                featureInstanceDto.stage,
                fromFunctionInstanceDtoList(featureInstanceDto.functionInstanceDtoList),
                fromTriggerInstanceDtoList(featureInstanceDto.triggerInstanceDtoList),
                Boolean.FALSE
        );
        return featureInstance;
    }

    public static List<FeatureInstance> fromFeatureInstanceDtoList(List<FeatureInstanceDto> featureInstanceDtoList) {
        List<FeatureInstance> featureInstanceList = new ArrayList<>();
        if (featureInstanceDtoList != null) {
            for (FeatureInstanceDto featureInstanceDto : featureInstanceDtoList) {
                featureInstanceList.add(fromFeatureInstanceDto(featureInstanceDto));
            }
        }
        return featureInstanceList;
    }

    public static StrategyInstance fromStrategyInstanceDto(StrategyInstanceDto strategyInstanceDto) {
        StrategyInstance strategyInstance = new StrategyInstance(
                strategyInstanceDto.strategyId,
                strategyInstanceDto.state,
                strategyInstanceDto.stage,
                fromFeatureInstanceDtoList(strategyInstanceDto.featureInstanceDtoList),
                strategyInstanceDto.action,
                strategyInstanceDto.timeStamp);
        return strategyInstance;
    }

}
