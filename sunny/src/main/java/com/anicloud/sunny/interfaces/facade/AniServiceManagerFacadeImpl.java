package com.anicloud.sunny.interfaces.facade;

import com.ani.bus.service.commons.dto.aniservice.AniServiceDto;
import com.anicloud.sunny.application.service.app.AniServiceManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Component
public class AniServiceManagerFacadeImpl implements AniServiceManagerFacade {

    @Resource
    private AniServiceManager aniServiceManager;

    @Override
    public AniServiceDto getAniServiceInfo() {
        // TODO  by KaMili
        return null;
    }

    @Override
    public AniServiceDto save(AniServiceDto aniService) {
        // TODO  by KaMili
        return null;
    }

    @Override
    public AniServiceDto update(AniServiceDto aniService) {
        // TODO  by KaMili
        return null;
    }
}
