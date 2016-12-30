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
                DeviceFeatureInstanceDtoAssembler.toFeatureInstanceList(strategyDto.deviceFeatureInstanceList)
        );;
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
                UserDtoAssembler.fromUser(strategy.owner),
                DeviceFeatureInstanceDtoAssembler.toDtoList(strategy.deviceFeatureInstanceList)
        );

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
