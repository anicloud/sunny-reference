package com.anicloud.sunny.application.service.init;

import com.ani.cel.service.manager.agent.oauth2.model.OAuth2AccessToken;

/**
 * Created by zhaoyu on 15-6-27.
 */
public abstract class ApplicationInitService {
    protected OAuth2AccessToken accessToken;

    public ApplicationInitService() {
    }

    protected abstract void initUser();
    protected abstract void initUserDevices();
    protected abstract void initDeviceFeatures();
    protected abstract boolean isTokenExists(OAuth2AccessToken accessToken);

    public void initApplication(OAuth2AccessToken accessToken) {
        if (isTokenExists(accessToken)) {
            return;
        }

        this.accessToken = accessToken;
        this.initUser();
        this.initUserDevices();
        this.initDeviceFeatures();
    }
}
