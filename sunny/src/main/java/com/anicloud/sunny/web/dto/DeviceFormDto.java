package com.anicloud.sunny.web.dto;


import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/10.
 */
public class DeviceFormDto {
    public String id;
    public String name;
    public DeviceState deviceState;
    public String deviceType;
    public String deviceGroup;
    public Object initParam;
    public String logoUrl;

    public DeviceFormDto(){

    }
    public DeviceFormDto(String id, String name, DeviceState deviceState,
                     String deviceType, String deviceGroup, String logoUrl) {
        this.id = id;
        this.name = name;
        this.deviceState = deviceState;
        this.deviceType = deviceType;
        this.deviceGroup = deviceGroup;
        this.logoUrl = logoUrl;
    }

    public static DeviceFormDto convertToDeviceForm(DeviceDto deviceDto){
        DeviceFormDto deviceFormDto = new DeviceFormDto();
        if(deviceDto != null) {
            deviceFormDto.id = deviceDto.identificationCode;
            deviceFormDto.name = deviceDto.name;
            deviceFormDto.deviceState = deviceDto.deviceState;
            deviceFormDto.deviceType = deviceDto.deviceType;
            deviceFormDto.deviceGroup = deviceDto.deviceGroup;
            deviceFormDto.logoUrl = deviceDto.logoUrl;
        }
        return deviceFormDto;
    }

    public static List<DeviceFormDto> convertToDeviceForms(List<DeviceDto> deviceDtos){
        List<DeviceFormDto> deviceFormDtos = new ArrayList<>();
        if(deviceDtos != null) {
            for (DeviceDto deviceDto : deviceDtos) {
                deviceFormDtos.add(convertToDeviceForm(deviceDto));
            }
        }
        return deviceFormDtos;
    }

    public static DeviceDto convertToDeviceDto(DeviceFormDto deviceFormDto){
        DeviceDto deviceDto = new DeviceDto();
        if(deviceFormDto != null) {
            deviceDto.identificationCode = deviceFormDto.id;
            deviceDto.name = deviceFormDto.name;
            deviceDto.deviceState = deviceFormDto.deviceState;
            deviceDto.deviceType = deviceFormDto.deviceType;
            deviceDto.deviceGroup = deviceFormDto.deviceGroup;
            deviceDto.logoUrl = deviceFormDto.logoUrl;
        }
        return deviceDto;
    }

    public static DeviceFormDto convertToDeviceForm(DeviceAndUserRelationDto relationDto){
        DeviceFormDto deviceFormDto = new DeviceFormDto();
        if(relationDto != null) {
            deviceFormDto.id = relationDto.deviceDto.identificationCode;
            deviceFormDto.name = relationDto.screenName;
            deviceFormDto.deviceState = relationDto.deviceDto.deviceState;
            deviceFormDto.deviceType = relationDto.deviceDto.deviceType;
            deviceFormDto.deviceGroup = relationDto.deviceGroup;
            deviceFormDto.initParam = relationDto.initParam;
            deviceFormDto.logoUrl = relationDto.deviceDto.logoUrl;
        }
        return deviceFormDto;
    }

    public static List<DeviceFormDto> convertToDeviceFormsByRelations(List<DeviceAndUserRelationDto> relations){
        List<DeviceFormDto> deviceFormDtos = new ArrayList<>();
        if(relations != null) {
            for (DeviceAndUserRelationDto relationDto : relations) {
                deviceFormDtos.add(convertToDeviceForm(relationDto));
            }
        }
        return deviceFormDtos;
    }
}
