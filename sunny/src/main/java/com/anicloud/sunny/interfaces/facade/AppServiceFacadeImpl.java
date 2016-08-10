package com.anicloud.sunny.interfaces.facade;

import com.anicloud.sunny.application.service.adapter.AniServiceAdapter;
import com.anicloud.sunny.application.service.app.AppService;
import com.anicloud.sunny.domain.adapter.AniServiceDaoAdapter;
import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.interfaces.adapter.AniServiceDtoAdapter;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceDto;
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
        return AniServiceDtoAdapter.toDto(
                appService.getAniServiceInfo()
        );
    }

    @Override
    public void update(AniServiceDto aniServiceDto) throws IOException {
        AniService aniService = AniServiceAdapter.toService(aniServiceDto);
        appService.update(aniService);
    }
}
