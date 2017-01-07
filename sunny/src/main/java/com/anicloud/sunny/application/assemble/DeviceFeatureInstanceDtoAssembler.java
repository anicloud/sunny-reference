package com.anicloud.sunny.application.assemble;


import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstance;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhaoyu on 15-6-17.
 */
public class DeviceFeatureInstanceDtoAssembler {
    private DeviceFeatureInstanceDtoAssembler() {}

    public static DeviceFeatureInstance toFeatureInstance(DeviceFeatureInstanceDto instanceDto) {
        if (instanceDto == null) {
            return null;
        }
        return new DeviceFeatureInstance(
                instanceDto.featureInstanceId,
                DeviceDtoAssembler.toDevice(instanceDto.deviceDto),
                DeviceFeatureDtoAssembler.toDeviceFeature(instanceDto.deviceFeatureDto),
                FunctionValueDtoAssembler.toFunctionValueList(instanceDto.featureArgValueDtoList),
                FeatureTriggerDtoAssembler.toFeatureTriggerList(instanceDto.triggerDtoList),
                instanceDto.isScheduleNow,
                instanceDto.intervalTime
        );
    }

    public static DeviceFeatureInstanceDto toDto(DeviceFeatureInstance featureInstance) {
        if (featureInstance == null) {
            return null;
        }
        return new DeviceFeatureInstanceDto(
                featureInstance.featureInstanceId,
                DeviceDtoAssembler.fromDevice(featureInstance.device),
                DeviceFeatureDtoAssembler.toDto(featureInstance.deviceFeature),
                FunctionValueDtoAssembler.toFunctionValueDtoList(featureInstance.featureArgValueList),
                FeatureTriggerDtoAssembler.toDtoList(featureInstance.triggerList),
                featureInstance.isScheduleNow,
                featureInstance.intervalTime
        );
    }

    public static List<DeviceFeatureInstance> toFeatureInstanceList(List<DeviceFeatureInstanceDto> instanceDtoList) {
        return instanceDtoList.stream().map(DeviceFeatureInstanceDtoAssembler::toFeatureInstance)
                .collect(Collectors.toList());
    }

    public static List<DeviceFeatureInstanceDto> toDtoList(List<DeviceFeatureInstance> instanceList) {
        return instanceList.stream().map(DeviceFeatureInstanceDtoAssembler::toDto)
                .collect(Collectors.toList());
    }

}
