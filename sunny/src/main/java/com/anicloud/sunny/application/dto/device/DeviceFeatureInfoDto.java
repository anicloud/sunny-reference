package com.anicloud.sunny.application.dto.device;

import java.util.List;

/**
 * Created by zhaoyu on 15-7-11.
 */
public class DeviceFeatureInfoDto {
    public String featureId;
    public String featureName;
    public String description;

    public List<FeatureArgDto> argDtoList;

    public DeviceFeatureInfoDto() {
    }

    public DeviceFeatureInfoDto(String featureId, String featureName,
                                String description, List<FeatureArgDto> argDtoList) {
        this.featureId = featureId;
        this.featureName = featureName;
        this.description = description;
        this.argDtoList = argDtoList;
    }

    @Override
    public String toString() {
        return "DeviceFeatureInfoDto{" +
                "featureId='" + featureId + '\'' +
                ", featureName='" + featureName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
