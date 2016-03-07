package com.anicloud.sunny.application.service.app;

import com.anicloud.sunny.domain.model.app.AniService;

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
    AniService getAniServiceInfo();

    /**
     * save the service meta information
     * @param aniService
     * @return the saved service meta info
     */
    AniService save(AniService aniService);

    /**
     * update the service meta information
     * @param aniService
     * @return the updated service meta information
     */
    AniService update(AniService aniService);
}
