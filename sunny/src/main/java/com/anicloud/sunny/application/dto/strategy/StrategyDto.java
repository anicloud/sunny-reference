package com.anicloud.sunny.application.dto.strategy;

import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.infrastructure.persistence.domain.share.StrategyState;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class StrategyDto implements Serializable {
    private static final long serialVersionUID = 614957459211270794L;

    public String strategyNum;
    public String strategyName;
    public StrategyState state;
    public String description;

    public UserDto owner;
    public List<DeviceFeatureInstanceDto> deviceFeatureInstanceList;

    public StrategyDto() {
    }

    public StrategyDto(String strategyNum, String strategyName,
                       StrategyState state, String description,
                       UserDto owner, List<DeviceFeatureInstanceDto> deviceFeatureInstanceList) {
        this.strategyNum = strategyNum;
        this.strategyName = strategyName;
        this.state = state;
        this.description = description;
        this.owner = owner;
        this.deviceFeatureInstanceList = deviceFeatureInstanceList;
    }

    @Override
    public String toString() {
        return "StrategyDto{" +
                "strategyNum='" + strategyNum + '\'' +
                ", strategyName='" + strategyName + '\'' +
                ", state=" + state +
                ", description='" + description + '\'' +
                '}';
    }
}
