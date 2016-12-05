package com.anicloud.sunny.interfaces.web.dto;

import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;
import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;

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
    public ScheduleState state;
    public Integer stage;
    public StrategyAction action;

    public Date startTime;
    public boolean isScheduleNow;
    public boolean isRepeat;
    public String[] repeatWeek;

    public List<DeviceFeatureInstanceFormDto> featureList;

    public static StrategyFormDto convertToStrategyForm(StrategyDto strategyDto){
        StrategyFormDto strategyFormDto = new StrategyFormDto();
        if(strategyDto != null) {
            strategyFormDto.strategyId = strategyDto.strategyId;
            strategyFormDto.strategyName = strategyDto.strategyName;
            strategyFormDto.strategyDescription = strategyDto.description;
            strategyFormDto.state = strategyDto.strategyInstanceDto.state;
            strategyFormDto.stage = strategyDto.strategyInstanceDto.stage;
            strategyFormDto.action = strategyDto.strategyInstanceDto.action;
            strategyFormDto.startTime = strategyDto.startTime;
            strategyFormDto.isScheduleNow = strategyDto.isScheduleNow;
            strategyFormDto.isRepeat = strategyDto.isRepeat;
            strategyFormDto.repeatWeek = strategyDto.repeatWeek;
            strategyFormDto.featureList = DeviceFeatureInstanceFormDto.convertToDeviceFeatureInstanceForms(strategyDto.deviceFeatureInstanceList);
        }
        return strategyFormDto;
    }


    public static List<StrategyFormDto> convertToStrategyForms(List<StrategyDto> strategyDtos){
        List<StrategyFormDto> strategyFormDtos = new ArrayList<>();
        if(strategyDtos != null) {
            for (StrategyDto strategyDto : strategyDtos) {
                strategyFormDtos.add(convertToStrategyForm(strategyDto));
            }
        }
        return strategyFormDtos;
    }

    public static StrategyDto convertToStrategyDto(StrategyFormDto strategyFormDto,UserDto userDto){
        StrategyDto strategyDto  = new StrategyDto();
        if(strategyFormDto != null) {
            strategyDto.strategyId = strategyFormDto.strategyId;
            strategyDto.strategyName = strategyFormDto.strategyName;
            strategyDto.description = strategyFormDto.strategyDescription;
            strategyDto.owner = userDto;
            strategyDto.deviceFeatureInstanceList = DeviceFeatureInstanceFormDto.convertToFeatureInstanceDtos(strategyFormDto.featureList);
            strategyDto.startTime = strategyFormDto.startTime;
            strategyDto.isRepeat = strategyFormDto.isRepeat;
            strategyDto.isScheduleNow = strategyFormDto.isScheduleNow;
            strategyDto.repeatWeek = strategyFormDto.repeatWeek;
        }

        return  strategyDto;
    }
}
