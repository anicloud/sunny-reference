package com.anicloud.sunny.application.dto.device;


import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceLogicState;

import java.io.Serializable;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceDto implements Serializable {
    private static final long serialVersionUID = -446166776319156987L;

    public String identificationCode;       // id of device, consist of masterDeviceId and slaveDeviceId
    public String name;
    // physical state
    public DeviceState deviceState;
    // logic state
    public DeviceLogicState logicState;
    public String deviceType;
    public String deviceGroup;

    public UserDto owner;

    public DeviceDto() {
    }

    public DeviceDto(String identificationCode) {
        this.identificationCode = identificationCode;
    }

    public DeviceDto(String deviceGroup, DeviceState deviceState, String deviceType,
                     String identificationCode, String name, UserDto owner, DeviceLogicState logicState) {
        this.deviceGroup = deviceGroup;
        this.deviceState = deviceState;
        this.deviceType = deviceType;
        this.identificationCode = identificationCode;
        this.name = name;
        this.owner = owner;
        this.logicState = logicState;
    }

    @Override
    public String toString() {
        return "DeviceDto{" +
                "deviceGroup='" + deviceGroup + '\'' +
                ", identificationCode='" + identificationCode + '\'' +
                ", name='" + name + '\'' +
                ", deviceState=" + deviceState +
                ", logicState=" + logicState +
                ", deviceType='" + deviceType + '\'' +
                ", owner=" + owner +
                '}';
    }
}
