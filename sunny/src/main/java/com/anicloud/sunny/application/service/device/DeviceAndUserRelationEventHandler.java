package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.dto.user.UserDto;

import java.util.List;

/**
 * Created by wyf on 16-10-13.
 */
public class DeviceAndUserRelationEventHandler  implements  DeviceAndUserRelationServcie{
    @Override
    public DeviceAndUserRelationDto save(DeviceAndUserRelationDto relation) {
        return null;
    }

    @Override
    public void batchSave(List<DeviceAndUserRelationDto> relationDtoList) {

    }

    @Override
    public DeviceAndUserRelationDto modify(DeviceAndUserRelationDto relation) {
        return null;
    }

    @Override
    public void batchModify(List<DeviceAndUserRelationDto> relationDtoList) {

    }

    @Override
    public void remove(DeviceAndUserRelationDto relation) {

    }

    @Override
    public void batchRemove(List<DeviceAndUserRelationDto> relations) {

    }

    @Override
    public List<DeviceAndUserRelationDto> getDevicesByUser(UserDto userDto) {
        return null;
    }
}
