package com.anicloud.sunny.application.dto.device;



import com.ani.agent.service.commons.object.dto.FunctionArgumentDto;
import com.ani.agent.service.commons.object.enumeration.FunctionType;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class FeatureFunctionDto implements Serializable {
    private static final long serialVersionUID = 3983547580667989004L;

    public String featureFunctionId;

    public Integer stubId;
    public Long groupId;
    public String functionName;
    public FunctionType functionType;
    public List<FunctionArgumentDto> inputArgList;
    public List<FunctionArgumentDto> outputArgList;

    public FeatureFunctionDto() {
    }

    public FeatureFunctionDto(String featureFunctionId, Integer stubId,
                              Long groupId, String functionName,
                              FunctionType functionType,
                              List<FunctionArgumentDto> inputArgList,
                              List<FunctionArgumentDto> outputArgList) {
        this.featureFunctionId = featureFunctionId;
        this.stubId = stubId;
        this.groupId = groupId;
        this.functionName = functionName;
        this.functionType = functionType;
        this.inputArgList = inputArgList;
        this.outputArgList = outputArgList;
    }

    @Override
    public String toString() {
        return "FeatureFunctionDto{" +
                "featureFunctionId='" + featureFunctionId + '\'' +
                ", stubId=" + stubId +
                ", groupId=" + groupId +
                ", functionName='" + functionName + '\'' +
                '}';
    }
}
