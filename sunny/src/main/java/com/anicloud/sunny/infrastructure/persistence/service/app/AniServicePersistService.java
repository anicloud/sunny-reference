package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public interface AniServicePersistService {
    AniServiceDao findAniServiceInfo();
    AniServiceDao save(AniServiceDao aniServiceDao);
    AniServiceDao update(AniServiceDao aniServiceDao);
}
