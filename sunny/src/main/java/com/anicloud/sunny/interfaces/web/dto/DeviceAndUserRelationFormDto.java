package com.anicloud.sunny.interfaces.web.dto;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 16-10-24.
 */
public class DeviceAndUserRelationFormDto {
    public DeviceFormDto deviceFormDto;
    public Long userId;
    public String initParam;
    public String screenName;
    public String deviceGroup;

    public DeviceAndUserRelationFormDto(DeviceFormDto deviceFormDto,Long userId,String initParam, String screenName, String deviceGroup) {
        this.deviceFormDto = deviceFormDto;
        this.userId = userId;
        this.initParam = initParam;
        this.screenName = screenName;
        this.deviceGroup = deviceGroup;
    }

    public static DeviceAndUserRelationFormDto convertToDeviceAndUserRelationForm(DeviceAndUserRelationDto relationDto){
        DeviceAndUserRelationFormDto deviceAndUserRelationFormDto = null;
        if(relationDto != null) {
            deviceAndUserRelationFormDto = new DeviceAndUserRelationFormDto(DeviceFormDto.convertToDeviceForm(relationDto.deviceDto),
                    relationDto.userDto.hashUserId,
                    relationDto.initParam,
                    relationDto.screenName,
                    relationDto.deviceGroup);
        }
        return deviceAndUserRelationFormDto;
    }

    public static List<DeviceAndUserRelationFormDto> convertToDeviceAndUserRelationForms(List<DeviceAndUserRelationDto> relationDtos) {
        List<DeviceAndUserRelationFormDto> relationFormDtos = new ArrayList<>();
        if(relationDtos != null) {
            for (DeviceAndUserRelationDto relationDto:relationDtos) {
                relationFormDtos.add(convertToDeviceAndUserRelationForm(relationDto));
            }
        }
        return relationFormDtos;
    }
}
