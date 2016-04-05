package com.anicloud.sunny.interfaces.adapter;

import com.ani.bus.service.commons.dto.aniservice.AniServiceDto;
import com.ani.bus.service.commons.dto.aniservice.AniServiceEntranceDto;
import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.domain.model.app.AniServiceEntrance;

import java.util.List;


/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
public class AniServiceDtoConverter {
    private AniServiceDtoConverter() {}

    public static AniServiceDto toDto(AniService aniService) {
        // TODO
        return null;
    }

    public static  AniServiceEntranceDto toDto(AniServiceEntrance entrance) {
        // TODO
        return null;
    }

    public static List<AniServiceEntranceDto> toDtoList(List<AniServiceEntrance> entranceList) {
        // TODO
        return null;
    }
}
