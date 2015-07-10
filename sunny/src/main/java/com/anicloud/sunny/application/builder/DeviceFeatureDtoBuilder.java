package com.anicloud.sunny.application.builder;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureArgDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyu on 15-6-18.
 */
public class DeviceFeatureDtoBuilder {
    private DeviceFeatureDto deviceFeatureDto;

    public DeviceFeatureDtoBuilder() {
        this.deviceFeatureDto = new DeviceFeatureDto();
    }

    public DeviceFeatureDtoBuilder(DeviceFeatureDto deviceFeatureDto) {
        this.deviceFeatureDto = deviceFeatureDto;
    }

    public DeviceFeatureDtoBuilder setFeatureName(String featureName) {
        this.deviceFeatureDto.featureName = featureName;
        return this;
    }

    public DeviceFeatureDtoBuilder setFeatureId(String featureId) {
        this.deviceFeatureDto.featureId = featureId;
        return this;
    }

    public DeviceFeatureDtoBuilder setDescription(String description) {
        this.deviceFeatureDto.description = description;
        return this;
    }

    public DeviceFeatureDtoBuilder setFeatureFunction(FeatureFunctionDto featureFunction) {
        if (this.deviceFeatureDto.featureFunctionDtoList == null) {
            this.deviceFeatureDto.featureFunctionDtoList = new ArrayList<>();
        }
        this.deviceFeatureDto.featureFunctionDtoList.add(featureFunction);
        return this;
    }

    public DeviceFeatureDtoBuilder setFeatureFunction(List<FeatureFunctionDto> dtoList) {
        this.deviceFeatureDto.featureFunctionDtoList = dtoList;
        return this;
    }

    public DeviceFeatureDtoBuilder setFeatureArg(FeatureArgDto featureArgDto) {
        if (deviceFeatureDto.argDtoList == null)
            deviceFeatureDto.argDtoList = new ArrayList<>();
        this.deviceFeatureDto.argDtoList.add(featureArgDto);
        return this;
    }

    public DeviceFeatureDtoBuilder setFeatureArg(List<FeatureArgDto> featureArgDtoList) {
        this.deviceFeatureDto.argDtoList = featureArgDtoList;
        return this;
    }

    public DeviceFeatureDtoBuilder setArgFunctionArgMap(Map<String, List<String>> map) {
        if (this.deviceFeatureDto.featureArgFuncArgMapList == null) {
            this.deviceFeatureDto.featureArgFuncArgMapList= new ArrayList<>();
        }
        this.deviceFeatureDto.featureArgFuncArgMapList.add(map);
        return this;
    }

    public DeviceFeatureDtoBuilder setArgFunctionArgMap(List<Map<String, List<String>>> mapList) {
        this.deviceFeatureDto.featureArgFuncArgMapList = mapList;
        return this;
    }
    public DeviceFeatureDto instance() {
        return this.deviceFeatureDto;
    }
}
