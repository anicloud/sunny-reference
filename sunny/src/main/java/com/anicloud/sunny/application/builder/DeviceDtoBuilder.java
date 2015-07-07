package com.anicloud.sunny.application.builder;

import com.ani.cel.service.manager.agent.core.share.DeviceState;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.user.UserDto;

/**
 * Created by zhaoyu on 15-6-18.
 */
public class DeviceDtoBuilder {
    private DeviceDto deviceDto;

    public DeviceDtoBuilder() {
        this.deviceDto = new DeviceDto();
    }

    public DeviceDtoBuilder(DeviceDto deviceDto) {
        this.deviceDto = deviceDto;
    }

    public DeviceDtoBuilder setIdentificationCode(String identificationCode) {
        this.deviceDto.identificationCode = identificationCode;
        return this;
    }

    public DeviceDtoBuilder setDeviceName(String deviceName) {
        this.deviceDto.name = deviceName;
        return this;
    }

    public DeviceDtoBuilder setDeviceState(DeviceState deviceState) {
        this.deviceDto.deviceState = deviceState;
        return this;
    }

    public DeviceDtoBuilder setDeviceType(String deviceType) {
        this.deviceDto.deviceType = deviceType;
        return this;
    }

    public DeviceDtoBuilder setDeviceGroup(String deviceGroup) {
        this.deviceDto.deviceGroup = deviceGroup;
        return this;
    }

    public DeviceDtoBuilder setOwner(UserDto owner) {
        this.deviceDto.owner = owner;
        return this;
    }

    public DeviceDto instance() {
        return this.deviceDto;
    }

    @Override
    public String toString() {
        return "DeviceDtoBuilder{" +
                "deviceDto=" + deviceDto +
                '}';
    }
}
