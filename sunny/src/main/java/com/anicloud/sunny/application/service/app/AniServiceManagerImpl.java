package com.anicloud.sunny.application.service.app;

import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.infrastructure.persistence.service.app.AniServicePersistService;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 * An implementation of <code>{@link com.anicloud.sunny.application.service.app.AniServiceManager}</code> that
 * does all of its work of manager the AniService meta information.
 * </p>
 *
 * <p>
 * We have provided two implementations of the meta data source. One is from the Json file, the another is from
 * the SQL database. It depends on the inject Bean of AniServicePersistService' implementation.
 * <ul>
 *     <li>JdbcAniServicePersistServiceImpl</li>
 *     <li>JsonAniServicePersistServiceImpl</li>
 * </ul>
 * </p>
 *
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */

public class AniServiceManagerImpl implements AniServiceManager{

    @Resource
    private AniServicePersistService aniServicePersistService;

    @Override
    public AniService getAniServiceInfo() {
        return null;
    }

    @Override
    public AniService save(AniService aniService) {
        return null;
    }

    @Override
    public AniService update(AniService aniService) {
        return null;
    }
}
