package com.anicloud.sunny.application.dto.device;

import com.ani.cel.service.manager.agent.core.share.FunctionType;
import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class FeatureFunctionDto implements Serializable {
    private static final long serialVersionUID = 3983547580667989004L;

    public String featureFunctionId;

    public String functionGroup;
    public String functionName;
    public FunctionType functionType;
    public Set<FunctionArgumentDto> functionArgumentDtoSet;

    public FeatureFunctionDto() {
    }

    public FeatureFunctionDto(String featureFunctionId,
                              Set<FunctionArgumentDto> functionArgumentDtoSet,
                              String functionGroup, String functionName,
                              FunctionType functionType) {
        this.featureFunctionId = featureFunctionId;
        this.functionArgumentDtoSet = functionArgumentDtoSet;
        this.functionGroup = functionGroup;
        this.functionName = functionName;
        this.functionType = functionType;
    }

    @Override
    public String toString() {
        return "FeatureFunctionDto{" +
                "featureFunctionId='" + featureFunctionId + '\'' +
                ", functionGroup='" + functionGroup + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionType=" + functionType +
                '}';
    }
}
