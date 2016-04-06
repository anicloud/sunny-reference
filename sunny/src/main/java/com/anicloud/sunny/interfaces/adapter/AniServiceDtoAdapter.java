package com.anicloud.sunny.interfaces.adapter;

import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.domain.model.app.AniServiceEntrance;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceDto;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceEntranceDto;

import java.util.List;


/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
public class AniServiceDtoAdapter {
    private AniServiceDtoAdapter() {}

    public static AniServiceDto toDto(AniService aniService) {
        // TODO
        return null;
    }

    public static AniServiceEntranceDto toDto(AniServiceEntrance entrance) {
        // TODO
        return null;
    }

    public static List<AniServiceEntranceDto> toDtoList(List<AniServiceEntrance> entranceList) {
        // TODO
        return null;
    }
}
