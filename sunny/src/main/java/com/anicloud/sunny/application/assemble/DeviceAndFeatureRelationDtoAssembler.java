package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceAndFeatureRelationDto;
import com.anicloud.sunny.domain.model.device.DeviceAndFeatureRelation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
public class DeviceAndFeatureRelationDtoAssembler {
    private DeviceAndFeatureRelationDtoAssembler() {}

    public static DeviceAndFeatureRelation toRelation(DeviceAndFeatureRelationDto relationDto) {
        if (relationDto == null) return null;
        DeviceAndFeatureRelation relation = new DeviceAndFeatureRelation(
                DeviceDtoAssembler.toDevice(relationDto.deviceDto),
                DeviceFeatureDtoConverter.toDeviceFeatureList(relationDto.deviceFeatureDtoList)
        );
        return relation;
    }

    public static DeviceAndFeatureRelationDto toDto(DeviceAndFeatureRelation relation) {
        if (relation == null) return null;
        DeviceAndFeatureRelationDto relationDto = new DeviceAndFeatureRelationDto(
                DeviceDtoAssembler.fromDevice(relation.device),
                DeviceFeatureDtoConverter.toDtoList(relation.deviceFeatureList)
        );
        return relationDto;
    }

    public static List<DeviceAndFeatureRelation> toRelationList(List<DeviceAndFeatureRelationDto> relationDtoList) {
        if (relationDtoList == null) return null;
        List<DeviceAndFeatureRelation> relationList = new ArrayList<>();
        for (DeviceAndFeatureRelationDto relationDto : relationDtoList) {
            relationList.add(toRelation(relationDto));
        }
        return relationList;
    }

    public static List<DeviceAndFeatureRelationDto> toDtoList(List<DeviceAndFeatureRelation> relationList) {
        if (relationList == null) return null;
        List<DeviceAndFeatureRelationDto> relationDtoList = new ArrayList<>();
        for (DeviceAndFeatureRelation relation : relationList) {
            relationDtoList.add(toDto(relation));
        }
        return relationDtoList;
    }
}
