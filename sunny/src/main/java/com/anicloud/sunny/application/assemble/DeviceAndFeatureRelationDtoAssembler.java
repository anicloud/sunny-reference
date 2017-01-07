package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceAndFeatureRelationDto;
import com.anicloud.sunny.domain.model.device.DeviceAndFeatureRelation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhaoyu on 15-7-8.
 */
public class DeviceAndFeatureRelationDtoAssembler {
    private DeviceAndFeatureRelationDtoAssembler() {}

    public static DeviceAndFeatureRelation toRelation(DeviceAndFeatureRelationDto relationDto) {
        if (relationDto == null) return null;
        return new DeviceAndFeatureRelation(
                DeviceDtoAssembler.toDevice(relationDto.deviceDto),
                DeviceFeatureDtoAssembler.toDeviceFeatureList(relationDto.deviceFeatureDtoList)
        );
    }

    public static DeviceAndFeatureRelationDto toDto(DeviceAndFeatureRelation relation) {
        if (relation == null) return null;
        return new DeviceAndFeatureRelationDto(
                DeviceDtoAssembler.fromDevice(relation.device),
                DeviceFeatureDtoAssembler.toDtoList(relation.deviceFeatureList)
        );
    }

    public static List<DeviceAndFeatureRelation> toRelationList(List<DeviceAndFeatureRelationDto> relationDtoList) {
        if (relationDtoList == null) return null;
        return relationDtoList.stream().map(DeviceAndFeatureRelationDtoAssembler::toRelation).collect(Collectors.toList());
    }

    public static List<DeviceAndFeatureRelationDto> toDtoList(List<DeviceAndFeatureRelation> relationList) {
        if (relationList == null) return null;
        return relationList.stream().map(DeviceAndFeatureRelationDtoAssembler::toDto)
                .collect(Collectors.toList());
    }
}
