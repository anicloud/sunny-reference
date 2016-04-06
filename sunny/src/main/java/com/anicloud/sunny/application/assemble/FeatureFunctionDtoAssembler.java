package com.anicloud.sunny.application.assemble;

import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.domain.model.device.FeatureFunction;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ArgumentType;

import java.util.ArrayList;
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
                featureFunctionDto.featureFunctionId,
                featureFunctionDto.stubId,
                featureFunctionDto.groupId,
                featureFunctionDto.functionName,
                featureFunctionDto.functionType,
                FunctionArgumentDtoAssembler.toFunctionArgumentList(featureFunctionDto.inputArgList, ArgumentType.INPUT_ARGUMENT),
                FunctionArgumentDtoAssembler.toFunctionArgumentList(featureFunctionDto.outputArgList, ArgumentType.OUTPUT_ARGUMENT)
        );
        return featureFunction;
    }

    public static FeatureFunctionDto toDto(FeatureFunction featureFunction) {
        if (featureFunction == null) {
            return null;
        }
        FeatureFunctionDto featureFunctionDto = new FeatureFunctionDto(
                featureFunction.featureFunctionId,
                featureFunction.stubId,
                featureFunction.groupId,
                featureFunction.functionName,
                featureFunction.functionType,
                FunctionArgumentDtoAssembler.toDtoList(featureFunction.inputArgList),
                FunctionArgumentDtoAssembler.toDtoList(featureFunction.outputArgList)
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
