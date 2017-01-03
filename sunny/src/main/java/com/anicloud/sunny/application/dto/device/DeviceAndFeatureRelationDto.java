package com.anicloud.sunny.application.dto.device;


import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
public class DeviceAndFeatureRelationDto implements Serializable {
    public DeviceAndUserRelationDto deviceAndUserRelationDto;
    public List<DeviceFeatureDto> deviceFeatureDtoList;

    public DeviceAndFeatureRelationDto() {
    }

    public DeviceAndFeatureRelationDto(DeviceAndUserRelationDto deviceAndUserRelationDto,
                                       List<DeviceFeatureDto> deviceFeatureDtoList) {
        this.deviceAndUserRelationDto = deviceAndUserRelationDto;
        this.deviceFeatureDtoList = deviceFeatureDtoList;
    }
}
