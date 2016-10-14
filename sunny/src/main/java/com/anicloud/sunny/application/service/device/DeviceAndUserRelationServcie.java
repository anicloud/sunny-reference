package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.dto.user.UserDto;

import java.util.List;

/**
 * Created by wyf on 16-10-13.
 */
public interface DeviceAndUserRelationServcie {
    DeviceAndUserRelationDto save(DeviceAndUserRelationDto relation);
    void batchSave(List<DeviceAndUserRelationDto> relationDtoList);
    DeviceAndUserRelationDto modify(DeviceAndUserRelationDto relation);
    void batchModify(List<DeviceAndUserRelationDto> relationDtoList);

    void remove(DeviceAndUserRelationDto relation);
    void batchRemove(List<DeviceAndUserRelationDto> relations);

    List<DeviceAndUserRelationDto> getDevicesByUser(UserDto userDto);
}
