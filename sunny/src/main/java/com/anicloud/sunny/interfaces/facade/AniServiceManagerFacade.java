package com.anicloud.sunny.interfaces.facade;

import com.anicloud.sunny.interfaces.facade.dto.AniServiceDto;

import java.io.IOException;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public interface AniServiceManagerFacade {
    AniServiceDto getAniServiceInfo() throws IOException;
    AniServiceDto save(AniServiceDto aniService) throws IOException;
    AniServiceDto update(AniServiceDto aniService) throws IOException;
}
