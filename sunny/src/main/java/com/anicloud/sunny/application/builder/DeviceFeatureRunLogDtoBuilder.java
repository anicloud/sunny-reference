package com.anicloud.sunny.application.builder;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureRunLogDto;
import com.anicloud.sunny.application.dto.share.FunctionValueDto;
import com.anicloud.sunny.application.dto.user.UserDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-18.
 */
public class DeviceFeatureRunLogDtoBuilder {
    private DeviceFeatureRunLogDto deviceFeatureRunLogDto;

    public DeviceFeatureRunLogDtoBuilder() {
        this.deviceFeatureRunLogDto = new DeviceFeatureRunLogDto();
    }

    public DeviceFeatureRunLogDtoBuilder(DeviceFeatureRunLogDto deviceFeatureRunLogDto) {
        this.deviceFeatureRunLogDto = deviceFeatureRunLogDto;
    }

    public DeviceFeatureRunLogDtoBuilder setDevice(DeviceDto device) {
        this.deviceFeatureRunLogDto.deviceDto = device;
        return this;
    }

    public DeviceFeatureRunLogDtoBuilder setDeviceFeature(DeviceFeatureDto deviceFeature) {
        this.deviceFeatureRunLogDto.deviceFeatureDto = deviceFeature;
        return this;
    }

    public DeviceFeatureRunLogDtoBuilder setOwner(UserDto owner) {
        this.deviceFeatureRunLogDto.owner = owner;
        return this;
    }

    public DeviceFeatureRunLogDtoBuilder setFunctionValue(FunctionValueDto functionValueDto) {
        if (this.deviceFeatureRunLogDto.functionValueDtoList == null) {
            this.deviceFeatureRunLogDto.functionValueDtoList = new ArrayList<>();
        }
        this.deviceFeatureRunLogDto.functionValueDtoList.add(functionValueDto);
        return this;
    }

    public DeviceFeatureRunLogDtoBuilder setFunctionValue(List<FunctionValueDto> valueDtoList) {
        this.deviceFeatureRunLogDto.functionValueDtoList = valueDtoList;
        return this;
    }

    public DeviceFeatureRunLogDto instance() {
        return this.deviceFeatureRunLogDto;
    }
}
