package com.anicloud.sunny.application.service.device;


import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.anicloud.sunny.application.assemble.DeviceDtoAssembler;
import com.anicloud.sunny.application.assemble.UserDtoAssembler;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.domain.model.device.Device;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceLogicState;
import com.anicloud.sunny.infrastructure.persistence.service.DevicePersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */

@Service
@Transactional
public class DeviceServiceEventHandler implements DeviceService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceServiceEventHandler.class);

    @Resource
    private DevicePersistenceService devicePersistenceService;

    @Override
    public DeviceDto saveDevice(DeviceDto deviceDto) {
        Device device = DeviceDtoAssembler.toDevice(deviceDto);
        device = Device.save(devicePersistenceService, device);
        return DeviceDtoAssembler.fromDevice(device);
    }

    @Override
    public void batchSave(List<DeviceDto> deviceDtoList) {
        for (DeviceDto deviceDto : deviceDtoList)  {
            saveDevice(deviceDto);
        }
    }

    @Override
    public DeviceDto modifyDevice(DeviceDto deviceDto) {
        Device device = DeviceDtoAssembler.toDevice(deviceDto);
        device = Device.modify(devicePersistenceService, device);
        return DeviceDtoAssembler.fromDevice(device);
    }

    @Override
    public void removeDevice(DeviceDto deviceDto) {
        Device.remove(devicePersistenceService, DeviceDtoAssembler.toDevice(deviceDto));
    }

    @Override
    public void modifyDeviceState(DeviceDto deviceDto, DeviceState deviceState) {
        Device.modifyDeviceState(devicePersistenceService, DeviceDtoAssembler.toDevice(deviceDto), deviceState);
    }

    @Override
    public void modifyDeviceLogicState(DeviceDto deviceDto, DeviceLogicState logicState) {
        Device.modifyDeviceLogicState(devicePersistenceService, DeviceDtoAssembler.toDevice(deviceDto), logicState);
    }

    @Override
    @Cacheable(value = "userDeviceListCache")
    public List<DeviceDto> getDeviceByUser(UserDto userDto) {
        List<Device> deviceList = Device.getDeviceByUser(devicePersistenceService, UserDtoAssembler.toUser(userDto));
        return DeviceDtoAssembler.fromDeviceList(deviceList);
    }

    @Override
    public List<DeviceDto> getDeviceByUserAndGroup(UserDto userDto, String deviceGroup) {
        List<Device> deviceList = Device.getDeviceByUserAndGroup(devicePersistenceService, UserDtoAssembler.toUser(userDto), deviceGroup);
        return DeviceDtoAssembler.fromDeviceList(deviceList);

    }

    @Override
    public List<DeviceDto> getDeviceByUserAndType(UserDto userDto, String deviceType) {
        List<Device> deviceList = Device.getDeviceByUserAndType(devicePersistenceService, UserDtoAssembler.toUser(userDto), deviceType);
        return DeviceDtoAssembler.fromDeviceList(deviceList);
    }

    @Override
    public List<DeviceDto> getDeviceByUserAndState(UserDto userDto, DeviceState state) {
        List<Device> deviceList = Device.getDeviceByUserAndState(devicePersistenceService, UserDtoAssembler.toUser(userDto), state);
        return DeviceDtoAssembler.fromDeviceList(deviceList);
    }

    @Override
    public List<DeviceDto> getDeviceByUserAndLogicState(UserDto userDto, DeviceLogicState logicState) {
        List<Device> deviceList = Device.getDeviceByUserAndLogicState(devicePersistenceService, UserDtoAssembler.toUser(userDto), logicState);
        return DeviceDtoAssembler.fromDeviceList(deviceList);
    }

    @Override
    public List<String> getUserDeviceGroupList(UserDto userDto) {
        return Device.getUserDeviceGroupList(devicePersistenceService, UserDtoAssembler.toUser(userDto));
    }

    @Override
    public DeviceDto getDeviceByIdentificationCode(String identificationCode) {
        Device device = Device.getDeviceByIdentificationCode(devicePersistenceService, identificationCode);
        return DeviceDtoAssembler.fromDevice(device);
    }
}
