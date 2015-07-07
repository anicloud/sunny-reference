package com.anicloud.sunny.application.service.device;

import com.ani.cel.service.manager.agent.core.share.DeviceState;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.user.UserDto;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface DeviceService {
    public DeviceDto saveDevice(DeviceDto deviceDto);
    public void batchSave(List<DeviceDto> deviceDtoList);
    public DeviceDto modifyDevice(DeviceDto deviceDto);
    public void removeDevice(DeviceDto deviceDto);

    public void modifyDeviceState(DeviceDto deviceDto, DeviceState deviceState);

    public DeviceDto getDeviceByIdentificationCode(String identificationCode);
    /**
     * find by user hashUserId or email
     * @param userDto
     * @return
     */
    public List<DeviceDto> getDeviceByUser(UserDto userDto);
    public List<DeviceDto> getDeviceByUserAndGroup(UserDto userDto, String deviceGroup);
    public List<DeviceDto> getDeviceByUserAndType(UserDto userDto, String deviceType);
    public List<DeviceDto> getDeviceByUserAndState(UserDto userDto, DeviceState state);
    public List<String> getUserDeviceGroupList(UserDto userDto);
}
