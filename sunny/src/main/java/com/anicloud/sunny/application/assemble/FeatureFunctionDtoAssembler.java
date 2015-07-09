package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.domain.model.device.FeatureFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-15.
 */
public class FeatureFunctionDtoAssembler {
    private FeatureFunctionDtoAssembler() {}

    public static FeatureFunction toFeatureFunction(FeatureFunctionDto featureFunctionDto) {
        if (featureFunctionDto == null) {
            return null;
        }
        FeatureFunction featureFunction = new FeatureFunction(
                FunctionArgumentDtoAssembler.toFunctionArgumentSet(featureFunctionDto.functionArgumentDtoSet),
                featureFunctionDto.functionGroup,
                featureFunctionDto.functionName,
                featureFunctionDto.functionType,
                featureFunctionDto.sequenceNum
        );
        return featureFunction;
    }

    public static FeatureFunctionDto toDto(FeatureFunction featureFunction) {
        if (featureFunction == null) {
            return null;
        }
        FeatureFunctionDto featureFunctionDto = new FeatureFunctionDto(
                FunctionArgumentDtoAssembler.toDtoSet(featureFunction.functionArgumentSet),
                featureFunction.functionGroup,
                featureFunction.functionName,
                featureFunction.functionType,
                featureFunction.sequenceNum
        );
        return featureFunctionDto;
    }

    public static List<FeatureFunction> toFeatureFunctionList(List<FeatureFunctionDto> featureFunctionDtoList) {
        if (featureFunctionDtoList == null) {
            return null;
        }
        List<FeatureFunction> functionSet = new ArrayList<>();
        for (FeatureFunctionDto featureFunctionDto : featureFunctionDtoList) {
            functionSet.add(toFeatureFunction(featureFunctionDto));
        }
        return functionSet;
    }

    public static List<FeatureFunctionDto> toDtoList(List<FeatureFunction> featureFunctionList) {
        if (featureFunctionList == null) {
            return null;
        }
        List<FeatureFunctionDto> functionDtoList = new ArrayList<>();
        for (FeatureFunction featureFunction : featureFunctionList) {
            functionDtoList.add(toDto(featureFunction));
        }
        return functionDtoList;
    }
}
