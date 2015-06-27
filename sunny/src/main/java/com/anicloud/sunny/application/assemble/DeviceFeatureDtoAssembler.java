package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.domain.model.device.DeviceFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-15.
 */
public class DeviceFeatureDtoAssembler {

    private DeviceFeatureDtoAssembler() {}

    public static DeviceFeature toDeviceFeature(DeviceFeatureDto deviceFeatureDto) {
        if (deviceFeatureDto == null) {
            return null;
        }

        DeviceFeature deviceFeature = new DeviceFeature(
                deviceFeatureDto.description,
                FeatureFunctionDtoAssembler.toFeatureFunctionSet(deviceFeatureDto.featureFunctionDtoSet),
                deviceFeatureDto.featureName,
                deviceFeatureDto.featureNum
        );

        return deviceFeature;
    }

    public static DeviceFeatureDto toDto(DeviceFeature deviceFeature) {
        if (deviceFeature == null) {
            return null;
        }

        DeviceFeatureDto deviceFeatureDto = new DeviceFeatureDto(
                deviceFeature.description,
                FeatureFunctionDtoAssembler.toDtoSet(deviceFeature.featureFunctionSet),
                deviceFeature.featureName,
                deviceFeature.featureNum
        );
        return deviceFeatureDto;
    }

    public static List<DeviceFeature> toDeviceFeatureList(List<DeviceFeatureDto> deviceFeatureDtoList) {
        if (deviceFeatureDtoList == null) {
            return null;
        }
        List<DeviceFeature> deviceFeatureList = new ArrayList<>(deviceFeatureDtoList.size());
        for (DeviceFeatureDto deviceFeatureDto : deviceFeatureDtoList) {
            deviceFeatureList.add(toDeviceFeature(deviceFeatureDto));
        }
        return deviceFeatureList;
    }

    public static List<DeviceFeatureDto> toDtoList(List<DeviceFeature> deviceFeatureList) {
        if (deviceFeatureList == null) {
            return null;
        }
        List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>(deviceFeatureList.size());
        for (DeviceFeature deviceFeature : deviceFeatureList) {
            deviceFeatureDtoList.add(toDto(deviceFeature));
        }
        return deviceFeatureDtoList;
    }
}
