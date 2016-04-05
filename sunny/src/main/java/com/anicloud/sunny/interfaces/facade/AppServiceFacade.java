package com.anicloud.sunny.interfaces.facade;

import com.ani.bus.service.commons.dto.aniservice.AniServiceDto;

import java.io.IOException;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
public interface AppServiceFacade {
    AniServiceDto getAniServiceInfo() throws IOException;
}
