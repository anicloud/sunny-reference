package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;

import java.io.IOException;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
public interface AniServicePersistService {
    AniServiceDao fetchAniServiceInfo() throws IOException;
    void update(AniServiceDao aniServiceDao) throws IOException;
}
