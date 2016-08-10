package com.anicloud.sunny.application.service.adapter;

import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.domain.model.app.AniServiceEntrance;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceDto;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceEntranceDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hey on 16-8-8.
 */
public class AniServiceAdapter {


    public AniServiceAdapter() {
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
