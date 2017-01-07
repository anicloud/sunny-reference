package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.domain.model.device.DeviceAndUserRelation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wyf on 16-10-12.
 */
public class DeviceAndUserRelationDtoAssembler {

    private DeviceAndUserRelationDtoAssembler() {}

    public static DeviceAndUserRelation toRelation(DeviceAndUserRelationDto deviceAndUserRelationDto){
        if (deviceAndUserRelationDto == null) return null;
        return new DeviceAndUserRelation(
                DeviceDtoAssembler.toDevice(deviceAndUserRelationDto.deviceDto),
                UserDtoAssembler.toUser(deviceAndUserRelationDto.userDto),
                deviceAndUserRelationDto.initParam,
                deviceAndUserRelationDto.screenName,
                deviceAndUserRelationDto.deviceGroup
        );
    }

    public static DeviceAndUserRelationDto toDto(DeviceAndUserRelation deviceAndUserRelation){
        if (deviceAndUserRelation == null) return null;
        return new DeviceAndUserRelationDto(
                DeviceDtoAssembler.fromDevice(deviceAndUserRelation.device),
                UserDtoAssembler.fromUser(deviceAndUserRelation.user),
                deviceAndUserRelation.initParam,
                deviceAndUserRelation.screenName,
                deviceAndUserRelation.deviceGroup
        );
    }

    public static List<DeviceAndUserRelation> toRelationList(List<DeviceAndUserRelationDto> relationDtoList) {
        if (relationDtoList == null) return null;
        return relationDtoList.stream().map(DeviceAndUserRelationDtoAssembler::toRelation)
                .collect(Collectors.toList());
    }

    public static List<DeviceAndUserRelationDto> toDtoList(List<DeviceAndUserRelation> relationList) {
        if (relationList == null) return null;
        return relationList.stream().map(DeviceAndUserRelationDtoAssembler::toDto)
                .collect(Collectors.toList());
    }
}
