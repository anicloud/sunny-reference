package com.anicloud.sunny.interfaces.adapter;

import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.domain.model.app.AniServiceEntrance;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceDto;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceEntranceDto;

import java.util.ArrayList;
import java.util.List;


/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
public class AniServiceDtoAdapter {
    private AniServiceDtoAdapter() {
    }

    public static AniServiceDto toDto(AniService aniService) {
        if (aniService == null) return null;
        return new AniServiceDto(aniService.aniServiceId, aniService.serviceName,
                aniService.version, aniService.clientSecret,
                aniService.resourceIds, aniService.scope,
                aniService.authorizedGrantTypes, aniService.authorities,
                aniService.webServerRedirectUri, aniService.accessTokenValidity,
                aniService.refreshTokenValidity, aniService.autoApprove,
                aniService.registerDate, aniService.archived,
                aniService.trusted, aniService.serviceServerUrl,
                aniService.logoPath, aniService.languageSet,
                aniService.tagSet, aniService.price,
                aniService.onShelf, aniService.description,
                aniService.accountId, toDtoList(aniService.entranceList)
        );
    }

    public static AniServiceEntranceDto toDto(AniServiceEntrance entrance) {
        if (entrance == null) return null;
        return new AniServiceEntranceDto(entrance.entranceId, entrance.entranceName,
                entrance.entranceUrl, entrance.logoPath,
                entrance.tagSet, entrance.description
        );
    }

    public static List<AniServiceEntranceDto> toDtoList(List<AniServiceEntrance> entranceList) {
        if (entranceList == null) return null;
        List<AniServiceEntranceDto> aniServiceEntranceDtos = new ArrayList<>();
        for (AniServiceEntrance aniServiceEntrance : entranceList) {
            aniServiceEntranceDtos.add(toDto(aniServiceEntrance));
        }
        return aniServiceEntranceDtos;
    }
}
