package com.anicloud.sunny.application.dto.strategy;

import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.schedule.dto.StrategyInstanceDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class StrategyDto implements Serializable {

    private static final long serialVersionUID = 614957459211270794L;

    public String strategyId;
    public String strategyName;
    public String description;

    public UserDto owner;
    public List<DeviceFeatureInstanceDto> deviceFeatureInstanceList;

    public StrategyDto() {
    }

    public StrategyDto(String strategyId, String strategyName, String description,
                       UserDto owner, List<DeviceFeatureInstanceDto> deviceFeatureInstanceList) {
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.description = description;
        this.owner = owner;
        this.deviceFeatureInstanceList = deviceFeatureInstanceList;
    }

//    public StrategyDto(String strategyId, String strategyName,
//                       String description, UserDto owner,
//                       List<DeviceFeatureInstanceDto> deviceFeatureInstanceList,
//                       StrategyInstanceDto strategyInstanceDto) {
//        this.strategyId = strategyId;
//        this.strategyName = strategyName;
//        this.description = description;
//        this.owner = owner;
//        this.deviceFeatureInstanceList = deviceFeatureInstanceList;
//        this.strategyInstanceDto = strategyInstanceDto;
//    }

    @Override
    public String toString() {
        return "StrategyDto{" +
                "strategyId='" + strategyId + '\'' +
                ", strategyName='" + strategyName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
