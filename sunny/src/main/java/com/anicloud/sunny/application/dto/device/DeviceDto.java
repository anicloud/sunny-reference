package com.anicloud.sunny.application.dto.device;

import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceState;

import java.io.Serializable;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceDto implements Serializable {
    private static final long serialVersionUID = -446166776319156987L;

    public String identificationCode;       // id of device, consist of masterDeviceId and slaveDeviceId
    public String name;
    public DeviceState deviceState;
    public String deviceType;
    public String deviceGroup;

    public UserDto owner;

    public DeviceDto() {
    }

    public DeviceDto(String deviceGroup, DeviceState deviceState, String deviceType,
                     String identificationCode, String name, UserDto owner) {
        this.deviceGroup = deviceGroup;
        this.deviceState = deviceState;
        this.deviceType = deviceType;
        this.identificationCode = identificationCode;
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "DeviceDto{" +
                "deviceGroup='" + deviceGroup + '\'' +
                ", identificationCode='" + identificationCode + '\'' +
                ", name='" + name + '\'' +
                ", deviceState=" + deviceState +
                ", deviceType='" + deviceType + '\'' +
                ", owner=" + owner +
                '}';
    }
}
