package com.anicloud.sunny.application.assemble;


import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceAssembleDto;
import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstance;
import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstanceAssemble;

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
                instanceDto.isScheduleNow
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
                featureInstance.isScheduleNow
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

    public static DeviceFeatureInstanceAssemble toAssemble(DeviceFeatureInstanceAssembleDto assembleDao) {
        DeviceFeatureInstanceAssemble instanceAssemble = new DeviceFeatureInstanceAssemble(
                DeviceFeatureInstanceDtoAssembler.toFeatureInstance(assembleDao.featureInstanceDto),
                DeviceFeatureInstanceDtoAssembler.toFeatureInstance(assembleDao.assembleInstanceDto)
        );
        return instanceAssemble;
    }

    public static DeviceFeatureInstanceAssembleDto toAssembleDto(DeviceFeatureInstanceAssemble assemble) {
        DeviceFeatureInstanceAssembleDto assembleDto = new DeviceFeatureInstanceAssembleDto(
                DeviceFeatureInstanceDtoAssembler.toDto(assemble.fatherInstance),
                DeviceFeatureInstanceDtoAssembler.toDto(assemble.assembleInstance)
        );
        return assembleDto;
    }

    public static List<DeviceFeatureInstanceAssemble> toAssembleList(List<DeviceFeatureInstanceAssembleDto> assembleDtoList) {
        List<DeviceFeatureInstanceAssemble> assembleList = new ArrayList<>();
        for (DeviceFeatureInstanceAssembleDto assembleDto : assembleDtoList) {
            assembleList.add(toAssemble(assembleDto));
        }
        return assembleList;
    }

    public static List<DeviceFeatureInstanceAssembleDto> toAssembleDtoList(List<DeviceFeatureInstanceAssemble> assembleList) {
        List<DeviceFeatureInstanceAssembleDto> assembleDtoList = new ArrayList<>();
        for (DeviceFeatureInstanceAssemble assemble : assembleList) {
            assembleDtoList.add(toAssembleDto(assemble));
        }
        return assembleDtoList;
    }
}
