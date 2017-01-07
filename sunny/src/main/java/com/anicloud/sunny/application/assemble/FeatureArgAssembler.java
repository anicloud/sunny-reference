package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.FeatureArgDto;
import com.anicloud.sunny.domain.model.device.FeatureArg;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhaoyu on 15-7-10.
 */
public class FeatureArgAssembler {
    private FeatureArgAssembler() {}

    public static FeatureArg toFeatureArg(FeatureArgDto featureArgDto) {
        if (featureArgDto == null) return null;
        return new FeatureArg(
                featureArgDto.dataType,
                featureArgDto.name,
                featureArgDto.screenName
        );
    }

    public static FeatureArgDto toDto(FeatureArg featureArg) {
        if (featureArg == null) return null;
        return new FeatureArgDto(
                featureArg.dataType,
                featureArg.name,
                featureArg.screenName
        );
    }

    public static List<FeatureArg> toFeatureArgList(List<FeatureArgDto> featureArgDtoList) {
        if (featureArgDtoList == null) return null;
        return featureArgDtoList.stream().map(FeatureArgAssembler::toFeatureArg)
                .collect(Collectors.toList());
    }

    public static List<FeatureArgDto> toDtoList(List<FeatureArg> featureArgList) {
        if (featureArgList == null) return null;
        return featureArgList.stream().map(FeatureArgAssembler::toDto)
                .collect(Collectors.toList());
    }
}
