package com.anicloud.sunny.application.dto.device;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeatureDto implements Serializable {
    private static final long serialVersionUID = 8210438835679440483L;

    public String featureNum;
    public String featureName;
    public String description;

    public List<FeatureFunctionDto> featureFunctionDtoList;

    public DeviceFeatureDto() {
    }

    public DeviceFeatureDto(String featureName) {
        this.featureName = featureName;
    }

    public DeviceFeatureDto(String description, List<FeatureFunctionDto> featureFunctionDtoSet,
                            String featureName) {
        this.description = description;
        this.featureFunctionDtoList = featureFunctionDtoList;
        this.featureName = featureName;
    }

    public DeviceFeatureDto(String description, List<FeatureFunctionDto> featureFunctionDtoList,
                            String featureName, String featureNum) {
        this.description = description;
        this.featureFunctionDtoList = featureFunctionDtoList;
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
