package com.anicloud.sunny.application.dto.strategy;

import java.io.Serializable;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeatureInstanceAssembleDto implements Serializable {
    private static final long serialVersionUID = 7411792874172822485L;

    public DeviceFeatureInstanceDto featureInstanceDto;
    public DeviceFeatureInstanceDto assembleInstanceDto;

    public DeviceFeatureInstanceAssembleDto() {
    }

    public DeviceFeatureInstanceAssembleDto(DeviceFeatureInstanceDto featureInstanceDto,
                                            DeviceFeatureInstanceDto assembleInstanceDto) {
        this.featureInstanceDto = featureInstanceDto;
        this.assembleInstanceDto = assembleInstanceDto;
    }

}
