package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.domain.model.device.FeatureFunction;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ArgumentType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhaoyu on 15-6-15.
 */
public class FeatureFunctionDtoAssembler {
    private FeatureFunctionDtoAssembler() {}

    public static FeatureFunction toFeatureFunction(FeatureFunctionDto featureFunctionDto) {
        if (featureFunctionDto == null) {
            return null;
        }

        return new FeatureFunction(
                featureFunctionDto.featureFunctionId,
                featureFunctionDto.stubId,
                featureFunctionDto.groupId,
                featureFunctionDto.functionName,
                featureFunctionDto.functionType,
                FunctionArgumentDtoAssembler.toFunctionArgumentList(featureFunctionDto.inputArgList, ArgumentType.INPUT_ARGUMENT),
                FunctionArgumentDtoAssembler.toFunctionArgumentList(featureFunctionDto.outputArgList, ArgumentType.OUTPUT_ARGUMENT)
        );
    }

    public static FeatureFunctionDto toDto(FeatureFunction featureFunction) {
        if (featureFunction == null) {
            return null;
        }
        return new FeatureFunctionDto(
                featureFunction.featureFunctionId,
                featureFunction.stubId,
                featureFunction.groupId,
                featureFunction.functionName,
                featureFunction.functionType,
                FunctionArgumentDtoAssembler.toDtoList(featureFunction.inputArgList),
                FunctionArgumentDtoAssembler.toDtoList(featureFunction.outputArgList)
        );
    }

    public static List<FeatureFunction> toFeatureFunctionList(List<FeatureFunctionDto> featureFunctionDtoList) {
        if (featureFunctionDtoList == null) {
            return null;
        }
        return featureFunctionDtoList.stream().map(FeatureFunctionDtoAssembler::toFeatureFunction)
                .collect(Collectors.toList());
    }

    public static List<FeatureFunctionDto> toDtoList(List<FeatureFunction> featureFunctionList) {
        if (featureFunctionList == null) {
            return null;
        }
        return featureFunctionList.stream().map(FeatureFunctionDtoAssembler::toDto)
                .collect(Collectors.toList());
    }
}
