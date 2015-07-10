package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.domain.model.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

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
                strategyDto.state,
                strategyDto.description,
                UserDtoAssembler.toUser(strategyDto.owner),
                DeviceFeatureInstanceDtoAssembler.toFeatureInstanceList(strategyDto.deviceFeatureInstanceList)
        );
        return strategy;
    }

    public static StrategyDto toDto(Strategy strategy) {
        if (strategy == null) {
            return null;
        }

        StrategyDto strategyDto = new StrategyDto(
                strategy.strategyId,
                strategy.strategyName,
                strategy.state,
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
}
