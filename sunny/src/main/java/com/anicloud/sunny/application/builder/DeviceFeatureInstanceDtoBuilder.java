package com.anicloud.sunny.application.builder;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.share.FunctionValueDto;
import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.application.dto.strategy.FeatureTriggerDto;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-19.
 */
public class DeviceFeatureInstanceDtoBuilder {
    private DeviceFeatureInstanceDto featureInstanceDto;

    public DeviceFeatureInstanceDtoBuilder() {}

    public DeviceFeatureInstanceDtoBuilder(DeviceFeatureInstanceDto featureInstanceDto) {
        this.featureInstanceDto = featureInstanceDto;
    }

    public DeviceFeatureInstanceDtoBuilder setDevice(DeviceDto device) {
        this.featureInstanceDto.deviceDto = device;
        return this;
    }

    public DeviceFeatureInstanceDtoBuilder setDeviceFeature(DeviceFeatureDto deviceFeature) {
        this.featureInstanceDto.deviceFeatureDto = deviceFeature;
        return this;
    }

    public DeviceFeatureInstanceDtoBuilder setFunctionValue(FunctionValueDto valueDto) {
        if (this.featureInstanceDto.functionValueDtoList == null) {
            this.featureInstanceDto.functionValueDtoList = new ArrayList<>();
        }
        this.featureInstanceDto.functionValueDtoList.add(valueDto);
        return this;
    }

    public DeviceFeatureInstanceDtoBuilder setFunctionValue(List<FunctionValueDto> valueDtoList) {
        this.featureInstanceDto.functionValueDtoList = valueDtoList;
        return this;
    }

    public DeviceFeatureInstanceDtoBuilder setFeatureTrigger(FeatureTriggerDto featureTrigger) {
        if (this.featureInstanceDto.triggerDtoList == null) {
            this.featureInstanceDto.triggerDtoList = new ArrayList<>();
        }
        this.featureInstanceDto.triggerDtoList.add(featureTrigger);
        return this;
    }

    public DeviceFeatureInstanceDtoBuilder setFeatureTrigger(List<FeatureTriggerDto> triggerDtoList) {
        this.featureInstanceDto.triggerDtoList = triggerDtoList;
        return this;
    }

    public DeviceFeatureInstanceDto instance() {
        return this.featureInstanceDto;
    }

    @Override
    public String toString() {
        return "DeviceFeatureInstanceDtoBuilder{" +
                "featureInstanceDto=" + featureInstanceDto +
                '}';
    }
}
