package com.anicloud.sunny.schedule.dto;

import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
public class FunctionInstanceDto {
    public String functionId;
    public Integer stubId;
    public Long groupId;
    public String name;
    public List<ArgumentDto> inputList;
    public List<ArgumentDto> outputList;

    public FunctionInstanceDto() {
    }

    public FunctionInstanceDto(String functionId, Integer stubId, Long groupId,
                               String name, List<ArgumentDto> inputList, List<ArgumentDto> outputList) {
        this.functionId = functionId;
        this.stubId = stubId;
        this.groupId = groupId;
        this.name = name;
        this.inputList = inputList;
        this.outputList = outputList;
    }
}
