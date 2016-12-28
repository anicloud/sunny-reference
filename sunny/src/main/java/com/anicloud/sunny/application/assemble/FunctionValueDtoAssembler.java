package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.FeatureArgValueDto;
import com.anicloud.sunny.domain.model.device.FeatureArgValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-17.
 */
public class FunctionValueDtoAssembler {
    private FunctionValueDtoAssembler() {}

    public static FeatureArgValue toFunctionValue(FeatureArgValueDto featureArgValueDto) {
        if (featureArgValueDto == null) {
            return null;
        }

        FeatureArgValue featureArgValue = new FeatureArgValue(
                featureArgValueDto.argName,
                featureArgValueDto.value
        );
        return featureArgValue;
    }

    public static FeatureArgValueDto toFunctionValueDto(FeatureArgValue featureArgValue) {
        if (featureArgValue == null) {
            return null;
        }

        FeatureArgValueDto valueDto = new FeatureArgValueDto(
                featureArgValue.argName,
                featureArgValue.value
        );
        return valueDto;
    }

    public static List<FeatureArgValue> toFunctionValueList(List<FeatureArgValueDto> featureArgValueDtoList) {
        List<FeatureArgValue> featureArgValueList = new ArrayList<>();
        for (FeatureArgValueDto valueDto : featureArgValueDtoList) {
            featureArgValueList.add(toFunctionValue(valueDto));
        }
        return featureArgValueList;
    }

    public static List<FeatureArgValueDto> toFunctionValueDtoList(List<FeatureArgValue> valueList) {
        List<FeatureArgValueDto> valueDtoList = new ArrayList<>();
        for (FeatureArgValue featureArgValue : valueList) {
            valueDtoList.add(toFunctionValueDto(featureArgValue));
        }
        return valueDtoList;
    }
}
