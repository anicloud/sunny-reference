package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.domain.model.device.FeatureFunction;

import java.util.HashSet;
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

    public static Set<FeatureFunction> toFeatureFunctionSet(Set<FeatureFunctionDto> featureFunctionDtoSet) {
        if (featureFunctionDtoSet == null) {
            return null;
        }
        Set<FeatureFunction> functionSet = new HashSet<FeatureFunction>(featureFunctionDtoSet.size());
        for (FeatureFunctionDto featureFunctionDto : featureFunctionDtoSet) {
            functionSet.add(toFeatureFunction(featureFunctionDto));
        }
        return functionSet;
    }

    public static Set<FeatureFunctionDto> toDtoSet(Set<FeatureFunction> featureFunctionSet) {
        if (featureFunctionSet == null) {
            return null;
        }
        Set<FeatureFunctionDto> functionDtoSet = new HashSet<FeatureFunctionDto>(featureFunctionSet.size());
        for (FeatureFunction featureFunction : featureFunctionSet) {
            functionDtoSet.add(toDto(featureFunction));
        }
        return functionDtoSet;
    }
}
