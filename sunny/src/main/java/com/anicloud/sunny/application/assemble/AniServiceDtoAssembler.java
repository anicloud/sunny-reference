package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.domain.model.app.AniServiceEntrance;
import com.anicloud.sunny.application.dto.app.AniServiceDto;
import com.anicloud.sunny.application.dto.app.AniServiceEntranceDto;

import java.util.ArrayList;
import java.util.List;


/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
public class AniServiceDtoAssembler {
    private AniServiceDtoAssembler() {
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

    public static AniService toService(AniServiceDto aniServiceDto){
        if (aniServiceDto == null) return null;
        return new AniService(
                null,
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
                toServiceList(aniServiceDto.entranceList)
        );
    }
    public static AniServiceEntrance toService(AniServiceEntranceDto entranceDto) {
        if (entranceDto == null) return null;
        return new AniServiceEntrance(entranceDto.entranceId, entranceDto.entranceName,
                entranceDto.entranceUrl, entranceDto.logoPath,
                entranceDto.tagSet, entranceDto.description
        );
    }

    public static List<AniServiceEntrance> toServiceList(List<AniServiceEntranceDto> entranceDtoList) {
        if (entranceDtoList == null) return null;
        List<AniServiceEntrance> aniServiceEntrances = new ArrayList<>();
        for (AniServiceEntranceDto aniServiceEntranceDto : entranceDtoList) {
            aniServiceEntrances.add(toService(aniServiceEntranceDto));
        }
        return aniServiceEntrances;
    }
}
