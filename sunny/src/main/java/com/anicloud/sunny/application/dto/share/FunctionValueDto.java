package com.anicloud.sunny.application.dto.share;

import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;

import java.io.Serializable;

/**
 * Created by zhaoyu on 15-6-16.
 */
public class FunctionValueDto implements Serializable {
    private static final long serialVersionUID = 4214473683870438852L;

    public String argName;
    public String value;

    public FunctionValueDto() {
    }

    public FunctionValueDto(String argName, String value) {
        this.argName = argName;
        this.value = value;
    }

    @Override
    public String toString() {
        return "FunctionValueDto{" +
                "argName='" + argName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
