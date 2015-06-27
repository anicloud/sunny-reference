package com.anicloud.sunny.application.service.init;

import com.ani.cel.service.manager.agent.oauth2.model.OAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhaoyu on 15-6-27.
 */
@Service
@Transactional
public class ApplicationInitServiceImpl extends ApplicationInitService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationInitServiceImpl.class);

    @Override
    public void initUser() {
        LOGGER.info("initUser {}", super.accessToken);
    }

    @Override
    public void initUserDevices() {
        LOGGER.info("initUserDevices");
    }

    @Override
    public void initDeviceFeatures() {
        LOGGER.info("initDeviceFeatures");
    }

    @Override
    protected boolean isTokenExists(OAuth2AccessToken accessToken) {
        return false;
    }
}
