package com.anicloud.sunny.interfaces.facade.adapter;

import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceDto;

/**
 * Created by MRK on 2016/3/8.
 */
public class DtoAdapter {
    private DtoAdapter() {}

    public static AniServiceDto toDto(AniService aniService){
        if(aniService==null){
            return null;
        }
        return new AniServiceDto(
                aniService.id,
                aniService.aniServiceId,
                aniService.serviceName,
                aniService.version,
                aniService.clientSecret,
                aniService.resourceIds,
                aniService.scope,
                aniService.authorizedGrantTypes,
                aniService.authorities,
                aniService.webServerRedirectUri,
                aniService.accessTokenValidity,
                aniService.refreshTokenValidity,
                aniService.autoApprove,
                aniService.registerDate,
                aniService.archived,
                aniService.trusted,
                aniService.serviceServerUrl,
                aniService.logoPath,
                aniService.languageSet,
                aniService.tagSet,
                aniService.price,
                aniService.onShelf,
                aniService.description,
                aniService.accountId,
                aniService.entranceList
        );
    }


    public static AniService toDomain(AniServiceDto aniServiceDto){
        if(aniServiceDto == null){
            return null;
        }

        return new AniService(
                aniServiceDto.id,
                aniServiceDto.aniServiceId,
                aniServiceDto.serviceName,
                aniServiceDto.version,
                aniServiceDto.clientSecret,
                aniServiceDto.resourceIds,
                aniServiceDto.scope,
                aniServiceDto.authorizedGrantTypes,
                aniServiceDto.authorities,
                aniServiceDto.webServerRedirectUri,
                aniServiceDto.accessTokenValidity,
                aniServiceDto.refreshTokenValidity,
                aniServiceDto.autoApprove,
                aniServiceDto.registerDate,
                aniServiceDto.archived,
                aniServiceDto.trusted,
                aniServiceDto.serviceServerUrl,
                aniServiceDto.logoPath,
                aniServiceDto.languageSet,
                aniServiceDto.tagSet,
                aniServiceDto.price,
                aniServiceDto.onShelf,
                aniServiceDto.description,
                aniServiceDto.accountId,
                aniServiceDto.entranceList

        );

    }


}
