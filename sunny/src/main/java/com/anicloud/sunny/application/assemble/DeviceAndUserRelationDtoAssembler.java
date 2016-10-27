package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.domain.model.device.DeviceAndUserRelation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 16-10-12.
 */
public class DeviceAndUserRelationDtoAssembler {

    private DeviceAndUserRelationDtoAssembler() {}

    public static DeviceAndUserRelation toRelation(DeviceAndUserRelationDto deviceAndUserRelationDto){
        if (deviceAndUserRelationDto == null) return null;
        DeviceAndUserRelation deviceAndUserRelation = new DeviceAndUserRelation(
                DeviceDtoAssembler.toDevice(deviceAndUserRelationDto.deviceDto),
                UserDtoAssembler.toUser(deviceAndUserRelationDto.userDto),
                deviceAndUserRelationDto.initParam,
                deviceAndUserRelationDto.screenName,
                deviceAndUserRelationDto.deviceGroup
        );
        return deviceAndUserRelation;
    }

    public static DeviceAndUserRelationDto toDto(DeviceAndUserRelation deviceAndUserRelation){
        if (deviceAndUserRelation == null) return null;
        DeviceAndUserRelationDto deviceAndUserRelationDto = new DeviceAndUserRelationDto(
                DeviceDtoAssembler.fromDevice(deviceAndUserRelation.device),
                UserDtoAssembler.fromUser(deviceAndUserRelation.user),
                deviceAndUserRelation.initParam,
                deviceAndUserRelation.screenName,
                deviceAndUserRelation.deviceGroup
        );
        return deviceAndUserRelationDto;
    }

    public static List<DeviceAndUserRelation> toRelationList(List<DeviceAndUserRelationDto> relationDtoList) {
        if (relationDtoList == null) return null;
        List<DeviceAndUserRelation> relationList = new ArrayList<>();
        for (DeviceAndUserRelationDto relationDto : relationDtoList) {
            relationList.add(toRelation(relationDto));
        }
        return relationList;
    }

    public static List<DeviceAndUserRelationDto> toDtoList(List<DeviceAndUserRelation> relationList) {
        if (relationList == null) return null;
        List<DeviceAndUserRelationDto> relationDtoList = new ArrayList<>();
        for (DeviceAndUserRelation relation : relationList) {
            relationDtoList.add(toDto(relation));
        }
        return relationDtoList;
    }
}
