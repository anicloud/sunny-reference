package com.anicloud.sunny.application.dto.device;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
public class DeviceAndFeatureRelationDto implements Serializable {
    public DeviceDto deviceDto;
    public List<DeviceFeatureDto> deviceFeatureDtoList;

    public DeviceAndFeatureRelationDto() {
    }

    public DeviceAndFeatureRelationDto(DeviceDto deviceDto,
                                       List<DeviceFeatureDto> deviceFeatureDtoList) {
        this.deviceDto = deviceDto;
        this.deviceFeatureDtoList = deviceFeatureDtoList;
    }
}
