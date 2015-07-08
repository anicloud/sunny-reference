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
        DeviceAndFeatureRelation relation = new DeviceAndFeatureRelation(
                DeviceDtoAssembler.toDevice(relationDto.deviceDto),
                DeviceFeatureDtoAssembler.toDeviceFeatureList(relationDto.deviceFeatureDtoList)
        );
        return relation;
    }

    public static DeviceAndFeatureRelationDto toDto(DeviceAndFeatureRelation relation) {
        DeviceAndFeatureRelationDto relationDto = new DeviceAndFeatureRelationDto(
                DeviceDtoAssembler.fromDevice(relation.device),
                DeviceFeatureDtoAssembler.toDtoList(relation.deviceFeatureList)
        );
        return relationDto;
    }

    public static List<DeviceAndFeatureRelation> toRelationList(List<DeviceAndFeatureRelationDto> relationDtoList) {
        List<DeviceAndFeatureRelation> relationList = new ArrayList<>();
        for (DeviceAndFeatureRelationDto relationDto : relationDtoList) {
            relationList.add(toRelation(relationDto));
        }
        return relationList;
    }

    public static List<DeviceAndFeatureRelationDto> toDtoList(List<DeviceAndFeatureRelation> relationList) {
        List<DeviceAndFeatureRelationDto> relationDtoList = new ArrayList<>();
        for (DeviceAndFeatureRelation relation : relationList) {
            relationDtoList.add(toDto(relation));
        }
        return relationDtoList;
    }
}
