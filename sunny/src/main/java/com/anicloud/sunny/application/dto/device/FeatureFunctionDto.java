package com.anicloud.sunny.application.dto.device;

import com.ani.cel.service.manager.agent.core.share.FunctionType;
import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;

import java.io.Serializable;
import java.util.List;
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
    public List<FunctionArgumentDto> inputArgList;
    public List<FunctionArgumentDto> outputArgList;

    public FeatureFunctionDto() {
    }

    public FeatureFunctionDto(String featureFunctionId, String functionGroup, String functionName,
                              FunctionType functionType, List<FunctionArgumentDto> inputArgList,
                              List<FunctionArgumentDto> outputArgList) {
        this.featureFunctionId = featureFunctionId;
        this.functionGroup = functionGroup;
        this.functionName = functionName;
        this.functionType = functionType;
        this.inputArgList = inputArgList;
        this.outputArgList = outputArgList;
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
