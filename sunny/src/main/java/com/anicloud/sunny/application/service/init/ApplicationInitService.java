package com.anicloud.sunny.application.service.init;

import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.anicloud.sunny.application.dto.user.UserDto;

import java.util.List;


/**
 * Created by zhaoyu on 15-6-27.
 */
public abstract class ApplicationInitService {

    protected abstract UserDto initUser(UserDto userDto);
    protected abstract void initUserDeviceAndDeviceFeatureRelation(UserDto accountDto,List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtoList) throws Exception;
    protected abstract boolean isUserNotExists(Long accountId);

    public abstract UserDto initApplication(AniOAuthAccessToken accessToken) throws Exception;
    public abstract void updateUserDeviceAndDeviceFeatureRelation(DeviceMasterObjInfoDto masterDto);
}
