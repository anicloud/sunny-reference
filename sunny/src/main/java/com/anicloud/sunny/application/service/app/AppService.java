package com.anicloud.sunny.application.service.app;

import com.anicloud.sunny.application.dto.app.AppClientDto;

/**
 * Created by zhaoyu on 15-6-27.
 */
public interface AppService {
    public AppClientDto save(AppClientDto dto);
    public AppClientDto modify(AppClientDto dto);
    public AppClientDto findByClientName(String clientName);
}
