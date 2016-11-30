package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.domain.model.device.Device;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-14.
 */
public class DeviceDtoAssembler {

    private DeviceDtoAssembler() {}

    public static Device toDevice(DeviceDto deviceDto) {
        if (deviceDto == null) {
            return null;
        }

        Device device = new Device(
                deviceDto.deviceGroup,
                deviceDto.deviceState,
                deviceDto.deviceType,
                deviceDto.identificationCode,
                deviceDto.name,
                deviceDto.ownerId,
                deviceDto.logicState,
                deviceDto.logoUrl
        );
        return device;
    }

    public static DeviceDto fromDevice(Device device) {
        if (device == null) {
            return null;
        }

        DeviceDto deviceDto = new DeviceDto(
                device.deviceGroup,
                device.deviceState,
                device.deviceType,
                device.identificationCode,
                device.name,
                device.ownerId,
                device.logicState,
                device.logoUrl
        );
        return deviceDto;
    }

    public static List<DeviceDto> fromDeviceList(List<Device> deviceList) {
        List<DeviceDto> deviceDtoList = new ArrayList<DeviceDto>(deviceList.size());
        for (Device device : deviceList) {
            deviceDtoList.add(fromDevice(device));
        }
        return deviceDtoList;
    }

    public static List<Device> toDeviceList(List<DeviceDto> deviceDtoList) {
        List<Device> deviceList = new ArrayList<Device>(deviceDtoList.size());
        for (DeviceDto deviceDto : deviceDtoList) {
            deviceList.add(toDevice(deviceDto));
        }
        return deviceList;
    }
}
