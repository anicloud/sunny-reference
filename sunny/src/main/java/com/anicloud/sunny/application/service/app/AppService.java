package com.anicloud.sunny.application.service.app;

import com.anicloud.sunny.application.dto.app.AniServiceDto;

import java.io.IOException;

/**
 * Created by zhaoyu on 15-6-27.
 */
public interface AppService {
    AniServiceDto getAniServiceInfo() throws IOException;
    void update(AniServiceDto aniServiceDto) throws IOException;
}
