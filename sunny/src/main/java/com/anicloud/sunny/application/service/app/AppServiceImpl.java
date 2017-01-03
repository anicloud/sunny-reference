package com.anicloud.sunny.application.service.app;

import com.anicloud.sunny.application.assemble.AniServiceDtoAssembler;
import com.anicloud.sunny.application.dto.app.AniServiceDto;
import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.anicloud.sunny.infrastructure.persistence.service.app.AniServicePersistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zhaoyu on 15-6-27.
 */
@Service(value = "appService")
public class AppServiceImpl implements AppService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AppServiceImpl.class);

    @Resource
    private AniServicePersistService aniServicePersistService;

    @Override
    public AniServiceDto getAniServiceInfo() throws IOException {
        AniServiceDao aniServiceDao = aniServicePersistService.fetchAniServiceInfo();
        return AniServiceDtoAssembler.toDto(AniService.toDomain(aniServiceDao));
    }

    @Override
    public void update(AniServiceDto aniServiceDto) throws IOException {
        AniService aniService = AniServiceDtoAssembler.toService(aniServiceDto);
        AniService.update(aniServicePersistService,aniService);
    }
}
