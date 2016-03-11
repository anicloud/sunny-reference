package com.anicloud.sunny.interfaces.facade;

import com.anicloud.sunny.application.service.app.AniServiceManager;
import com.anicloud.sunny.domain.adapter.DaoAdapter;
import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.interfaces.facade.adapter.DtoAdapter;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @autor KaMiLi
 * @date 16-3-7
 * @since JDK 1.7
 */
@Component
public class AniServiceManagerFacadeImpl implements AniServiceManagerFacade {

    @Resource
    private AniServiceManager aniServiceManager;

    @Override
    public AniServiceDto getAniServiceInfo() {
        AniService aniService=aniServiceManager.getAniServiceInfo();
        return DtoAdapter.toDto(aniService);
    }

    @Override
    public AniServiceDto save(AniServiceDto aniServiceDto) {
        AniService aniService= DtoAdapter.toDomain(aniServiceDto);
       aniServiceDto=DtoAdapter.toDto(aniServiceManager.save(aniService));
        return aniServiceDto;
    }

    @Override
    public AniServiceDto update(AniServiceDto aniServiceDto) {
        AniService aniService=DtoAdapter.toDomain(aniServiceDto);
       aniServiceDto= DtoAdapter.toDto(aniServiceManager.update(aniService));
        return aniServiceDto;
    }
}
