package com.anicloud.sunny.application.service.init;

import com.ani.cel.service.manager.agent.core.AnicelServiceConfig;
import com.ani.cel.service.manager.agent.oauth2.model.OAuth2AccessToken;
import com.ani.cel.service.manager.agent.user.model.SysUserDto;
import com.ani.cel.service.manager.agent.user.service.UserService;
import com.ani.cel.service.manager.agent.user.service.UserServiceImpl;
import com.anicloud.sunny.application.assemble.UserDtoAssembler;
import com.anicloud.sunny.application.dto.user.UserDto;

/**
 * Created by zhaoyu on 15-6-27.
 */
public abstract class ApplicationInitService {
    protected OAuth2AccessToken accessToken;
    protected UserService userService;

    protected UserDto userDto;

    public ApplicationInitService() {
        userService = new UserServiceImpl(AnicelServiceConfig.getInstance());
    }

    protected abstract void initUser(UserDto userDto);
    protected abstract void initUserDevicesAndDeviceFeatures();
    protected abstract boolean isUserNotExists(String hashUserId);

    public UserDto initApplication(OAuth2AccessToken accessToken) {
        this.accessToken = accessToken;
        SysUserDto sysUserDto = userService.getUserInfoByAccessToken(accessToken.getAccessToken());
        if (isUserNotExists(sysUserDto.hashUserId)) {
            this.initUser(UserDtoAssembler.toUser(sysUserDto, accessToken));
            initUserDevicesAndDeviceFeatures();
        }

        return userDto;
    }
}
