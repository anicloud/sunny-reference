package com.anicloud.sunny.application.builder;

import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.infrastructure.persistence.domain.share.StrategyState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-19.
 */
public class StrategyDtoBuilder {
    private StrategyDto strategyDto;

    public StrategyDtoBuilder() {
        this.strategyDto = new StrategyDto();
    }

    public StrategyDtoBuilder(StrategyDto strategyDto) {
        this.strategyDto = strategyDto;
    }

    public StrategyDtoBuilder setStrategyName(String strategyName) {
        this.strategyDto.strategyName = strategyName;
        return this;
    }

    public StrategyDtoBuilder setStrategyState(StrategyState state) {
        this.strategyDto.state = state;
        return this;
    }

    public StrategyDtoBuilder setDescription(String description) {
        this.strategyDto.description = description;
        return this;
    }

    public StrategyDtoBuilder setOwner(UserDto owner) {
        this.strategyDto.owner = owner;
        return this;
    }

    public StrategyDtoBuilder setDeviceFeatureInstance(DeviceFeatureInstanceDto instanceDto) {
        if (this.strategyDto.deviceFeatureInstanceList == null) {
            this.strategyDto.deviceFeatureInstanceList = new ArrayList<>();
        }
        this.strategyDto.deviceFeatureInstanceList.add(instanceDto);
        return this;
    }

    public StrategyDtoBuilder setDeviceFeatureInstance(List<DeviceFeatureInstanceDto> instanceDtoList) {
        this.strategyDto.deviceFeatureInstanceList = instanceDtoList;
        return this;
    }

    public StrategyDto instance() {
        return this.strategyDto;
    }

    @Override
    public String toString() {
        return "StrategyDtoBuilder{" +
                "strategyDto=" + strategyDto +
                '}';
    }
}
