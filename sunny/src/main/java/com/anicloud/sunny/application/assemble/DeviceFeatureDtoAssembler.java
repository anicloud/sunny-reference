package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureInfoDto;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.domain.model.device.FeatureArg;

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
                deviceFeatureDto.featureArgFuncArgMapList,
                FeatureArgAssembler.toFeatureArgList(deviceFeatureDto.argDtoList),
                FeatureFunctionDtoAssembler.toFeatureFunctionList(deviceFeatureDto.featureFunctionDtoList),
                deviceFeatureDto.featureId,
                deviceFeatureDto.featureName,
                deviceFeatureDto.privilegeType
        );

        return deviceFeature;
    }

    public static DeviceFeatureDto toDto(DeviceFeature deviceFeature) {
        if (deviceFeature == null) {
            return null;
        }

        DeviceFeatureDto deviceFeatureDto = new DeviceFeatureDto(
                FeatureArgAssembler.toDtoList(deviceFeature.featureArgList),
                deviceFeature.description,
                deviceFeature.featureArgFuncArgMapList,
                FeatureFunctionDtoAssembler.toDtoList(deviceFeature.featureFunctionList),
                deviceFeature.featureId,
                deviceFeature.featureName,
                deviceFeature.privilegeType
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
        List<DeviceFeatureInfoDto> infoDtoList = new ArrayList<>();
        for (DeviceFeature deviceFeature : deviceFeatureList) {
            infoDtoList.add(toDeviceFeatureInfoDto(deviceFeature));
        }
        return infoDtoList;
    }
}
