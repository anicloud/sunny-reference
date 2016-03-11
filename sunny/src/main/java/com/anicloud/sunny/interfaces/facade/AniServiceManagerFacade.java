package com.anicloud.sunny.interfaces.facade;

import com.anicloud.sunny.interfaces.facade.dto.AniServiceDto;

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
