package com.anicloud.sunny.application.builder;

import com.ani.cel.service.manager.agent.core.share.FunctionType;
import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-18.
 */
public class FeatureFunctionDtoBuilder {
    private FeatureFunctionDto featureFunctionDto;

    public FeatureFunctionDtoBuilder() {
        this.featureFunctionDto = new FeatureFunctionDto();
    }

    public FeatureFunctionDtoBuilder(FeatureFunctionDto featureFunctionDto) {
        this.featureFunctionDto = featureFunctionDto;
    }

    public FeatureFunctionDtoBuilder setFunctionGroup(String functionGroup) {
        this.featureFunctionDto.functionGroup = functionGroup;
        return this;
    }

    public FeatureFunctionDtoBuilder setFunctionId(String functionId) {
        this.featureFunctionDto.featureFunctionId = functionId;
        return this;
    }

    public FeatureFunctionDtoBuilder setFunctionName(String functionName) {
        this.featureFunctionDto.functionName = functionName;
        return this;
    }

    public FeatureFunctionDtoBuilder setFunctionType(FunctionType functionType) {
        this.featureFunctionDto.functionType = functionType;
        return this;
    }

    public FeatureFunctionDtoBuilder setFunctionArgument(FunctionArgumentDto argumentDto) {
        if (this.featureFunctionDto.functionArgumentDtoSet == null) {
            this.featureFunctionDto.functionArgumentDtoSet = new HashSet<>();
        }
        this.featureFunctionDto.functionArgumentDtoSet.add(argumentDto);
        return this;
    }

    public FeatureFunctionDtoBuilder setFunctionArgument(Set<FunctionArgumentDto> dtoSet) {
        this.featureFunctionDto.functionArgumentDtoSet = dtoSet;
        return this;
    }

    public FeatureFunctionDto instance() {
        return this.featureFunctionDto;
    }
}
