package com.anicloud.sunny.application.dto.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.FunctionType;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class FeatureFunctionDto implements Serializable {
    private static final long serialVersionUID = 3983547580667989004L;

    public String functionGroup;
    public String functionName;
    public FunctionType functionType;
    public Integer sequenceNum;
    public Set<FunctionArgumentDto> functionArgumentDtoSet;

    public FeatureFunctionDto() {
    }

    public FeatureFunctionDto(Set<FunctionArgumentDto> functionArgumentDtoSet,
                              String functionGroup, String functionName,
                              FunctionType functionType, Integer sequenceNum) {
        this.functionArgumentDtoSet = functionArgumentDtoSet;
        this.functionGroup = functionGroup;
        this.functionName = functionName;
        this.functionType = functionType;
        this.sequenceNum = sequenceNum;
    }

    @Override
    public String toString() {
        return "FeatureFunctionDto{" +
                "functionGroup='" + functionGroup + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionType=" + functionType +
                ", sequenceNum=" + sequenceNum +
                '}';
    }
}
