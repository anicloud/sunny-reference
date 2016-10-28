package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.dto.user.UserDto;

import java.util.List;

/**
 * Created by wyf on 16-10-13.
 */
public interface DeviceAndUserRelationServcie {
    DeviceAndUserRelationDto saveRelation(DeviceAndUserRelationDto relationDto);
    void batchSave(List<DeviceAndUserRelationDto> relationDtoList);
    DeviceAndUserRelationDto modifyRelation(DeviceAndUserRelationDto relationDto);
    void batchModify(List<DeviceAndUserRelationDto> relationDtoList);

    void removeRelation(DeviceAndUserRelationDto relationDto);
    void batchRemove(List<DeviceAndUserRelationDto> relations);

    List<DeviceAndUserRelationDto> getRelationsByUser(UserDto userDto);
    DeviceAndUserRelationDto getDeviceAndUserRelation(String identificationCode,Long hashUserId);
}
