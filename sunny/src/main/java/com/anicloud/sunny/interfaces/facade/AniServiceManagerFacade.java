package com.anicloud.sunny.interfaces.facade;

import com.ani.bus.service.commons.dto.aniservice.AniServiceDto;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public interface AniServiceManagerFacade {
    AniServiceDto getAniServiceInfo();
    AniServiceDto save(AniServiceDto aniService);
    AniServiceDto update(AniServiceDto aniService);
}
