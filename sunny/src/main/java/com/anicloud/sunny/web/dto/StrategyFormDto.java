package com.anicloud.sunny.web.dto;

import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;
import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/11.
 */
public class StrategyFormDto {
    public String strategyId;
    public String strategyName;
    public String strategyDescription;
    public List<DeviceFeatureInstanceFormDto> featureList;

    public static Strategy convertToStrategy(StrategyFormDto strategyFormDto, User user){
        Strategy strategy = new Strategy();
        if(strategyFormDto != null) {
            strategy.strategyId = strategyFormDto.strategyId;
            strategy.strategyName = strategyFormDto.strategyName;
            strategy.description = strategyFormDto.strategyDescription;
            strategy.owner = user;
            strategy.deviceFeatureInstanceList = DeviceFeatureInstanceFormDto.convertToFeatureInstanceDtos(strategyFormDto.featureList);
        }
        return strategy;
    }

    public static StrategyFormDto convertToStrategyFormDto(Strategy strategy) {
        StrategyFormDto strategyFormDto = new StrategyFormDto();
        if (strategy != null) {
            strategyFormDto.strategyId = strategy.strategyId;
            strategyFormDto.strategyName = strategy.strategyName;
            strategyFormDto.strategyDescription = strategy.description;
            strategyFormDto.featureList = DeviceFeatureInstanceFormDto.convertToDeviceFeatureInstanceForms(strategy.deviceFeatureInstanceList);
        }
        return strategyFormDto;
    }
}
