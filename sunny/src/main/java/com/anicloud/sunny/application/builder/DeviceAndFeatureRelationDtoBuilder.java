package com.anicloud.sunny.application.builder;

import com.anicloud.sunny.application.dto.device.DeviceAndFeatureRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
public class DeviceAndFeatureRelationDtoBuilder {
    private DeviceAndFeatureRelationDto featureRelationDto;

    public DeviceAndFeatureRelationDtoBuilder() {
        this.featureRelationDto = new DeviceAndFeatureRelationDto();
    }

    public DeviceAndFeatureRelationDtoBuilder setDevice(DeviceDto device) {
        this.featureRelationDto.deviceDto = device;
        return this;
    }

    public DeviceAndFeatureRelationDtoBuilder setRelateDeviceFeature(DeviceFeatureDto deviceFeature) {
        if (this.featureRelationDto.deviceFeatureDtoList == null) {
            this.featureRelationDto.deviceFeatureDtoList = new ArrayList<>();
        }
        this.featureRelationDto.deviceFeatureDtoList.add(deviceFeature);
        return this;
    }

    public DeviceAndFeatureRelationDtoBuilder setRelateDeviceFeature(List<DeviceFeatureDto> featureDtoList) {
        this.featureRelationDto.deviceFeatureDtoList = featureDtoList;
        return this;
    }

    public DeviceAndFeatureRelationDto instance() {
        return featureRelationDto;
    }
}
