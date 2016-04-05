package com.anicloud.sunny.application.service.init;

import com.ani.cel.service.manager.agent.core.AnicelServiceConfig;
import com.ani.cel.service.manager.agent.oauth2.model.OAuth2AccessToken;
import com.ani.cel.service.manager.agent.user.model.SysUserDto;
import com.ani.cel.service.manager.agent.user.service.UserService;
import com.ani.cel.service.manager.agent.user.service.UserServiceImpl;
import com.anicloud.sunny.application.assemble.UserDtoAssembler;
import com.anicloud.sunny.application.dto.user.UserDto;

import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-27.
 */
public abstract class ApplicationInitService {
    protected UserService userService;

    public ApplicationInitService() {
        userService = new UserServiceImpl(AnicelServiceConfig.getInstance());
    }

    protected abstract UserDto initUser(UserDto userDto);
    protected abstract void initUserDeviceAndDeviceFeatureRelation(UserDto userDto, OAuth2AccessToken accessToken);
    protected abstract UserDto isUserNotExists(String hashUserId);

    public UserDto initApplication(OAuth2AccessToken accessToken) {
        SysUserDto sysUserDto = userService.getUserInfoByAccessToken(accessToken.getAccessToken());
        UserDto userDto = isUserNotExists(sysUserDto.hashUserId);
        if (userDto == null) {
            userDto = initUser(UserDtoAssembler.toUser(sysUserDto, accessToken));
            initUserDeviceAndDeviceFeatureRelation(userDto, accessToken);
        }
        return userDto;
    }
}
