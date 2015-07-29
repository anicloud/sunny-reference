package com.anicloud.sunny.interfaces.web.dto;

import com.ani.cel.service.manager.agent.core.share.DeviceState;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceLogicState;

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

    public DeviceFormDto(){

    }
    public DeviceFormDto(String id, String name, DeviceState deviceState,
                     String deviceType, String deviceGroup) {
        this.id = id;
        this.name = name;
        this.deviceState = deviceState;
        this.deviceType = deviceType;
        this.deviceGroup = deviceGroup;
    }

    public static DeviceFormDto convertToDeviceForm(DeviceDto deviceDto){
        DeviceFormDto deviceFormDto = new DeviceFormDto();
        if(deviceDto != null) {
            deviceFormDto.id = deviceDto.identificationCode;
            deviceFormDto.name = deviceDto.name;
            deviceFormDto.deviceState = deviceDto.deviceState;
            deviceFormDto.deviceType = deviceDto.deviceType;
            deviceFormDto.deviceGroup = deviceDto.deviceGroup;
        }
        return deviceFormDto;
    }

    public static List<DeviceFormDto> convertToDeviceForms(List<DeviceDto> deviceDtos){
        List<DeviceFormDto> deviceFormDtos = new ArrayList<>();
        for (DeviceDto deviceDto : deviceDtos){
            deviceFormDtos.add(convertToDeviceForm(deviceDto));
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
        }
        return deviceDto;
    }


}
