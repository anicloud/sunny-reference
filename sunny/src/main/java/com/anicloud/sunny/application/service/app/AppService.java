package com.anicloud.sunny.application.service.app;

import com.anicloud.sunny.application.dto.app.AppClientDto;
import com.anicloud.sunny.domain.model.app.AniService;

import java.io.IOException;

/**
 * Created by zhaoyu on 15-6-27.
 */
public interface AppService {
    AniService getAniServiceInfo() throws IOException;
    void update(AniService aniService) throws IOException;
}
