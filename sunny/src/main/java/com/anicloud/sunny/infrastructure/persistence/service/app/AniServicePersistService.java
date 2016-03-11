package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public interface AniServicePersistService {
    AniServiceDao findAniServiceInfo() throws IOException;
    AniServiceDao save(AniServiceDao aniServiceDao) throws IOException;
    AniServiceDao update(AniServiceDao aniServiceDao) throws IOException;
}
