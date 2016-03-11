package com.anicloud.sunny.application.service.app;

import com.anicloud.sunny.domain.model.app.AniService;

import java.io.IOException;

/**
 * <p>
 * An interface for management of the current service meta information.
 * </p>
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public interface AniServiceManager {
    /**
     * get the current service meta info
     * @return service meta info
     */
    AniService getAniServiceInfo() throws IOException;

    /**
     * save the service meta information
     * @param aniService
     * @return the saved service meta info
     */
    AniService save(AniService aniService) throws IOException;

    /**
     * update the service meta information
     * @param aniService
     * @return the updated service meta information
     */
    AniService update(AniService aniService) throws IOException;
}
