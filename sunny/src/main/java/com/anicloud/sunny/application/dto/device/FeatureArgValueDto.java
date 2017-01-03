package com.anicloud.sunny.application.dto.device;

import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;

import java.io.Serializable;

/**
 * Created by zhaoyu on 15-6-16.
 */
public class FeatureArgValueDto implements Serializable {
    private static final long serialVersionUID = 4214473683870438852L;

    public String argName;
    public String value;

    public FeatureArgValueDto() {
    }

    public FeatureArgValueDto(String argName, String value) {
        this.argName = argName;
        this.value = value;
    }

    @Override
    public String toString() {
        return "FeatureArgValueDto{" +
                "argName='" + argName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
