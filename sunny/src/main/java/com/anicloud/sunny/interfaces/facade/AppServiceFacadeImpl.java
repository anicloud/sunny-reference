package com.anicloud.sunny.interfaces.facade;

import com.ani.bus.service.commons.dto.aniservice.AniServiceDto;
import com.anicloud.sunny.application.service.app.AppService;
import com.anicloud.sunny.interfaces.adapter.AniServiceDtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
@Component(value = "appServiceFacade")
public class AppServiceFacadeImpl implements AppServiceFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(AppServiceFacadeImpl.class);

    @Resource(name = "appService")
    private AppService appService;

    @Override
    public AniServiceDto getAniServiceInfo() throws IOException {
        return AniServiceDtoConverter.toDto(
                appService.getAniServiceInfo()
        );
    }
}
