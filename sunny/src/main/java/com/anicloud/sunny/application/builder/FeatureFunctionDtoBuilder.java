package com.anicloud.sunny.application.builder;


import com.ani.agent.service.commons.object.dto.FunctionArgumentDto;
import com.ani.agent.service.commons.object.enumeration.FunctionType;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public FeatureFunctionDtoBuilder setGroupId(Long groupId) {
        this.featureFunctionDto.groupId = groupId;
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

    public FeatureFunctionDtoBuilder setInputFunctionArgument(FunctionArgumentDto argumentDto) {
        if (this.featureFunctionDto.inputArgList == null) {
            this.featureFunctionDto.inputArgList = new ArrayList<>();
        }
        this.featureFunctionDto.inputArgList.add(argumentDto);
        return this;
    }

    public FeatureFunctionDtoBuilder setInputFunctionArgument(List<FunctionArgumentDto> dtoList) {
        this.featureFunctionDto.inputArgList = dtoList;
        return this;
    }

    public FeatureFunctionDtoBuilder setOutputFunctionArgument(FunctionArgumentDto argumentDto) {
        if (this.featureFunctionDto.outputArgList == null) {
            this.featureFunctionDto.outputArgList = new ArrayList<>();
        }
        this.featureFunctionDto.outputArgList.add(argumentDto);
        return this;
    }

    public FeatureFunctionDtoBuilder setOutputFunctionArgument(List<FunctionArgumentDto> dtoList) {
        this.featureFunctionDto.outputArgList = dtoList;
        return this;
    }

    public FeatureFunctionDtoBuilder setStubId(Integer stubId) {
        this.featureFunctionDto.stubId = stubId;
        return this;
    }
    public FeatureFunctionDto instance() {
        return this.featureFunctionDto;
    }
}
