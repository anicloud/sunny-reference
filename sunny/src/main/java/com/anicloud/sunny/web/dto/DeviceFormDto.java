package com.anicloud.sunny.web.dto;


import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.anicloud.sunny.domain.model.device.Device;
import com.anicloud.sunny.domain.model.device.DeviceAndUserRelation;

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

    public static DeviceFormDto convertToDeviceForm(Device device){
        DeviceFormDto deviceFormDto = new DeviceFormDto();
        if(device != null) {
            deviceFormDto.id = device.identificationCode;
            deviceFormDto.name = device.name;
            deviceFormDto.deviceState = device.deviceState;
            deviceFormDto.deviceType = device.deviceType;
            deviceFormDto.deviceGroup = device.deviceGroup;
            deviceFormDto.logoUrl = device.logoUrl;
        }
        return deviceFormDto;
    }

    public static List<DeviceFormDto> convertToDeviceForms(List<Device> devices){
        List<DeviceFormDto> deviceFormDtos = new ArrayList<>();
        if(devices != null) {
            for (Device device : devices) {
                deviceFormDtos.add(convertToDeviceForm(device));
            }
        }
        return deviceFormDtos;
    }

    public static Device convertToDeviceDto(DeviceFormDto deviceFormDto){
        Device device = new Device();
        if(deviceFormDto != null) {
            device.identificationCode = deviceFormDto.id;
            device.name = deviceFormDto.name;
            device.deviceState = deviceFormDto.deviceState;
            device.deviceType = deviceFormDto.deviceType;
            device.deviceGroup = deviceFormDto.deviceGroup;
            device.logoUrl = deviceFormDto.logoUrl;
        }
        return device;
    }

    public static DeviceFormDto convertToDeviceForm(DeviceAndUserRelation relation){
        DeviceFormDto deviceFormDto = new DeviceFormDto();
        if(relation != null) {
            deviceFormDto.id = relation.device.identificationCode;
            deviceFormDto.name = relation.screenName;
            deviceFormDto.deviceState = relation.device.deviceState;
            deviceFormDto.deviceType = relation.device.deviceType;
            deviceFormDto.deviceGroup = relation.deviceGroup;
            deviceFormDto.initParam = relation.initParam;
            deviceFormDto.logoUrl = relation.device.logoUrl;
        }
        return deviceFormDto;
    }

    public static List<DeviceFormDto> convertToDeviceFormsByRelations(List<DeviceAndUserRelation> relations){
        List<DeviceFormDto> deviceFormDtos = new ArrayList<>();
        if(relations != null) {
            for (DeviceAndUserRelation relation : relations) {
                deviceFormDtos.add(convertToDeviceForm(relation));
            }
        }
        return deviceFormDtos;
    }
}
