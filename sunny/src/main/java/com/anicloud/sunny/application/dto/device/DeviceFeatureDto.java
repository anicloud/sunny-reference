package com.anicloud.sunny.application.dto.device;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeatureDto implements Serializable {
    private static final long serialVersionUID = 8210438835679440483L;

    public String featureNum;
    public String featureName;
    public String description;

    public Set<FeatureFunctionDto> featureFunctionDtoSet;

    public DeviceFeatureDto() {
    }

    public DeviceFeatureDto(String featureName) {
        this.featureName = featureName;
    }

    public DeviceFeatureDto(String description, Set<FeatureFunctionDto> featureFunctionDtoSet,
                            String featureName) {
        this.description = description;
        this.featureFunctionDtoSet = featureFunctionDtoSet;
        this.featureName = featureName;
    }

    public DeviceFeatureDto(String description, Set<FeatureFunctionDto> featureFunctionDtoSet,
                            String featureName, String featureNum) {
        this.description = description;
        this.featureFunctionDtoSet = featureFunctionDtoSet;
        this.featureName = featureName;
        this.featureNum = featureNum;
    }

    @Override
    public String toString() {
        return "DeviceFeatureDto{" +
                "description='" + description + '\'' +
                ", featureNum='" + featureNum + '\'' +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}
