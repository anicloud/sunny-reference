package com.anicloud.sunny.interfaces.web.dto;

import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.infrastructure.persistence.domain.share.StrategyState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/11.
 */
public class StrategyFormDto {
    public String strategyId;
    public String strategyName;
    public StrategyState strategyState;
    public String strategyDescription;
    public String strategyStage;

    public List<DeviceFeatureInstanceFormDto> featureList;

    public static StrategyFormDto convertToStrategyForm(StrategyDto strategyDto){
        StrategyFormDto strategyFormDto = new StrategyFormDto();
        strategyFormDto.strategyId = strategyDto.strategyId;
        strategyFormDto.strategyName = strategyDto.strategyName;
        strategyFormDto.strategyState = strategyDto.state;
        strategyFormDto.strategyDescription = strategyDto.description;
        strategyFormDto.strategyStage = "";
        strategyFormDto.featureList = DeviceFeatureInstanceFormDto.convertToDeviceFeatureInstanceForms(strategyDto.deviceFeatureInstanceList);
        return strategyFormDto;
    }


    public static List<StrategyFormDto> convertToStrategyForms(List<StrategyDto> strategyDtos){
        List<StrategyFormDto> strategyFormDtos = new ArrayList<>();
        for(StrategyDto strategyDto : strategyDtos){
            strategyFormDtos.add(convertToStrategyForm(strategyDto));
        }
        return strategyFormDtos;
    }

    public static StrategyDto convertToStrategyDto(StrategyFormDto strategyFormDto,UserDto userDto){
        StrategyDto strategyDto  = new StrategyDto();

        strategyDto.strategyId = strategyFormDto.strategyId;
        strategyDto.strategyName = strategyFormDto.strategyName;
        strategyDto.state = strategyFormDto.strategyState;
        strategyDto.description = strategyFormDto.strategyDescription;

        strategyDto.owner = userDto;

        strategyDto.deviceFeatureInstanceList = DeviceFeatureInstanceFormDto.convertToFeatureInstanceDtos(strategyFormDto.featureList);


        return  strategyDto;
    }
}
