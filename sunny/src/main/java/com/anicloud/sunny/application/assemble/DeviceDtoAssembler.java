package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.domain.model.device.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhaoyu on 15-6-14.
 */
public class DeviceDtoAssembler {

    private DeviceDtoAssembler() {}

    public static Device toDevice(DeviceDto deviceDto) {
        if (deviceDto == null) {
            return null;
        }

        return new Device(
                deviceDto.deviceGroup,
                deviceDto.deviceState,
                deviceDto.deviceType,
                deviceDto.identificationCode,
                deviceDto.name,
                deviceDto.ownerId,
                deviceDto.logicState,
                deviceDto.logoUrl
        );
    }

    public static DeviceDto fromDevice(Device device) {
        if (device == null) {
            return null;
        }

        return new DeviceDto(
                device.deviceGroup,
                device.deviceState,
                device.deviceType,
                device.identificationCode,
                device.name,
                device.ownerId,
                device.logicState,
                device.logoUrl
        );
    }

    public static List<DeviceDto> fromDeviceList(List<Device> deviceList) {
        List<DeviceDto> deviceDtoList = new ArrayList<>(deviceList.size());
        deviceDtoList.addAll(deviceList.stream().map(DeviceDtoAssembler::fromDevice)
                .collect(Collectors.toList()));
        return deviceDtoList;
    }

    public static List<Device> toDeviceList(List<DeviceDto> deviceDtoList) {
        List<Device> deviceList = new ArrayList<>(deviceDtoList.size());
        deviceList.addAll(deviceDtoList.stream().map(DeviceDtoAssembler::toDevice)
                .collect(Collectors.toList()));
        return deviceList;
    }
}
