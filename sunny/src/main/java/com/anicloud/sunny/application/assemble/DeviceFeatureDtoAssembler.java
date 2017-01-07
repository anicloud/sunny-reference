package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureInfoDto;
import com.anicloud.sunny.domain.model.device.DeviceFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhaoyu on 15-6-15.
 */
public class DeviceFeatureDtoAssembler {

    private DeviceFeatureDtoAssembler() {}

    public static DeviceFeature toDeviceFeature(DeviceFeatureDto deviceFeatureDto) {
        if (deviceFeatureDto == null) {
            return null;
        }

        return new DeviceFeature(
                deviceFeatureDto.description,
                deviceFeatureDto.featureArgFuncArgMapList,
                FeatureArgAssembler.toFeatureArgList(deviceFeatureDto.argDtoList),
                FeatureFunctionDtoAssembler.toFeatureFunctionList(deviceFeatureDto.featureFunctionDtoList),
                deviceFeatureDto.featureId,
                deviceFeatureDto.featureName,
                deviceFeatureDto.privilegeType
        );
    }

    public static DeviceFeatureDto toDto(DeviceFeature deviceFeature) {
        if (deviceFeature == null) {
            return null;
        }

        return new DeviceFeatureDto(
                FeatureArgAssembler.toDtoList(deviceFeature.featureArgList),
                deviceFeature.description,
                deviceFeature.featureArgFuncArgMapList,
                FeatureFunctionDtoAssembler.toDtoList(deviceFeature.featureFunctionList),
                deviceFeature.featureId,
                deviceFeature.featureName,
                deviceFeature.privilegeType
        );
    }

    public static List<DeviceFeature> toDeviceFeatureList(List<DeviceFeatureDto> deviceFeatureDtoList) {
        if (deviceFeatureDtoList == null) {
            return null;
        }
        List<DeviceFeature> deviceFeatureList = new ArrayList<>(deviceFeatureDtoList.size());
        deviceFeatureList.addAll(deviceFeatureDtoList.stream()
                .map(DeviceFeatureDtoAssembler::toDeviceFeature).collect(Collectors.toList()));
        return deviceFeatureList;
    }

    public static List<DeviceFeatureDto> toDtoList(List<DeviceFeature> deviceFeatureList) {
        if (deviceFeatureList == null) {
            return null;
        }
        List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>(deviceFeatureList.size());
        deviceFeatureDtoList.addAll(deviceFeatureList.stream().map(DeviceFeatureDtoAssembler::toDto)
                .collect(Collectors.toList()));
        return deviceFeatureDtoList;
    }

    public static DeviceFeatureInfoDto toDeviceFeatureInfoDto(DeviceFeature deviceFeature) {
        if (deviceFeature == null) return null;
        return new DeviceFeatureInfoDto(
                deviceFeature.featureId,
                deviceFeature.featureName,
                deviceFeature.description,
                FeatureArgAssembler.toDtoList(deviceFeature.featureArgList)
        );
    }

    public static List<DeviceFeatureInfoDto> toDeviceFeatureInfoDtoList(List<DeviceFeature> deviceFeatureList) {
        if (deviceFeatureList == null) return null;
        return deviceFeatureList.stream().map(DeviceFeatureDtoAssembler::toDeviceFeatureInfoDto)
                .collect(Collectors.toList());
    }
}
