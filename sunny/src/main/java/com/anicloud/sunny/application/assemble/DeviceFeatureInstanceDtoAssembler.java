package com.anicloud.sunny.application.assemble;


import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-17.
 */
public class DeviceFeatureInstanceDtoAssembler {
    private DeviceFeatureInstanceDtoAssembler() {}

    public static DeviceFeatureInstance toFeatureInstance(DeviceFeatureInstanceDto instanceDto) {
        if (instanceDto == null) {
            return null;
        }
        DeviceFeatureInstance instance = new DeviceFeatureInstance(
                instanceDto.featureInstanceId,
                DeviceDtoAssembler.toDevice(instanceDto.deviceDto),
                DeviceFeatureDtoAssembler.toDeviceFeature(instanceDto.deviceFeatureDto),
                FunctionValueDtoAssembler.toFunctionValueList(instanceDto.featureArgValueDtoList),
                FeatureTriggerDtoAssembler.toFeatureTriggerList(instanceDto.triggerDtoList),
                instanceDto.isScheduleNow,
                instanceDto.intervalTime
        );
        return instance;
    }

    public static DeviceFeatureInstanceDto toDto(DeviceFeatureInstance featureInstance) {
        if (featureInstance == null) {
            return null;
        }
        DeviceFeatureInstanceDto instanceDto = new DeviceFeatureInstanceDto(
                featureInstance.featureInstanceId,
                DeviceDtoAssembler.fromDevice(featureInstance.device),
                DeviceFeatureDtoAssembler.toDto(featureInstance.deviceFeature),
                FunctionValueDtoAssembler.toFunctionValueDtoList(featureInstance.featureArgValueList),
                FeatureTriggerDtoAssembler.toDtoList(featureInstance.triggerList),
                featureInstance.isScheduleNow,
                featureInstance.intervalTime
        );
        return instanceDto;
    }

    public static List<DeviceFeatureInstance> toFeatureInstanceList(List<DeviceFeatureInstanceDto> instanceDtoList) {
        List<DeviceFeatureInstance> instanceList = new ArrayList<>();
        for (DeviceFeatureInstanceDto instanceDto : instanceDtoList) {
            instanceList.add(toFeatureInstance(instanceDto));
        }
        return instanceList;
    }

    public static List<DeviceFeatureInstanceDto> toDtoList(List<DeviceFeatureInstance> instanceList) {
        List<DeviceFeatureInstanceDto> instanceDtoList = new ArrayList<>();
        for (DeviceFeatureInstance featureInstance : instanceList) {
            instanceDtoList.add(toDto(featureInstance));
        }
        return instanceDtoList;
    }

}
