package com.anicloud.sunny.application.service.init;

import com.ani.octopus.commons.accout.dto.AccountDto;
import com.ani.octopus.service.agent.service.oauth.dto.AniOAuthAccessToken;
import com.anicloud.sunny.application.dto.user.UserDto;

import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-27.
 */
public abstract class ApplicationInitService {
    protected abstract UserDto initUser(UserDto userDto);
    protected abstract void initUserDeviceAndDeviceFeatureRelation(AccountDto accountDto, AniOAuthAccessToken accessToken) throws Exception;
    protected abstract boolean isUserNotExists(Long accountId);

    public abstract UserDto initApplication(AniOAuthAccessToken accessToken) throws Exception;
}
