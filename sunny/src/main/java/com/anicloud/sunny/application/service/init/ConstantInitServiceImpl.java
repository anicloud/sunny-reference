package com.anicloud.sunny.application.service.init;

import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.service.app.AppService;
import com.anicloud.sunny.infrastructure.persistence.service.app.SunnyStubPersistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * @autor zhaoyu
 * @date 16-4-12
 * @since JDK 1.7
 */
@Service
public class ConstantInitServiceImpl implements ConstantInitService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConstantInitServiceImpl.class);

    @Resource(name = "appService")
    private AppService appService;
    @Resource
    private SunnyStubPersistService sunnyStubPersistService;

    @Override
    @PostConstruct
    public void constantInitService() {

        try {
            Constants.aniServiceDto = appService.getAniServiceInfo();
            Constants.SUNNY_STUB_MAPPINGS = sunnyStubPersistService.fetchSunnyStubMappings();
            LOGGER.debug("init AniService information.");
        } catch (IOException e) {
            LOGGER.error("read sunny basic info error. msg {}.", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
