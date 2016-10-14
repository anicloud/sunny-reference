package com.anicloud.sunny.application.dto.device;

import com.anicloud.sunny.application.dto.user.UserDto;

import java.io.Serializable;

/**
 * Created by wyf on 16-10-12.
 */
public class DeviceAndUserRelationDto implements Serializable{

    public DeviceDto deviceDto;
    public UserDto userDto;
    public String initParam;
    public String screenName;
    public String deviceGroup;

    public DeviceAndUserRelationDto(){}

    public DeviceAndUserRelationDto(DeviceDto deviceDto, UserDto userDto,
                                    String initParam, String screenName, String deviceGroup){
        this.deviceDto = deviceDto;
        this.userDto = userDto;
        this.initParam = initParam;
        this.screenName = screenName;
        this.deviceGroup = deviceGroup;
    }

    @Override
    public String toString() {
        return "DeviceAndUserRelationDto{" +
                "deviceDto='" + deviceDto + '\'' +
                ", userDto='" + userDto + '\'' +
                ", initParam='" + initParam + '\'' +
                ", screenName='" + screenName + '\'' +
                ", deviceGroup='" + deviceGroup + '\'' +
                '}';
    }
}
