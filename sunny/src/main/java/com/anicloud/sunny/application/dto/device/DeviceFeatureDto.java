package com.anicloud.sunny.application.dto.device;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeatureDto implements Serializable {
    private static final long serialVersionUID = 8210438835679440483L;

    public String featureId;
    public String featureName;
    public String description;

    public List<FeatureArgDto> argDtoList;
    public List<FeatureFunctionDto> featureFunctionDtoList;
    public List<Map<String, List<String>>> featureArgFuncArgMapList;

    public DeviceFeatureDto() {
    }

    public DeviceFeatureDto(String featureName) {
        this.featureName = featureName;
    }

    public DeviceFeatureDto(List<FeatureArgDto> argDtoList, String description,
                            List<Map<String, List<String>>> featureArgFuncArgMapList,
                            List<FeatureFunctionDto> featureFunctionDtoList,
                            String featureId, String featureName) {
        this.argDtoList = argDtoList;
        this.description = description;
        this.featureArgFuncArgMapList = featureArgFuncArgMapList;
        this.featureFunctionDtoList = featureFunctionDtoList;
        this.featureId = featureId;
        this.featureName = featureName;
    }

    @Override
    public String toString() {
        return "DeviceFeatureDto{" +
                "featureId='" + featureId + '\'' +
                ", featureName='" + featureName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}