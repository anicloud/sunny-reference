package com.anicloud.sunny.schedule.dto;

import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
public class FunctionInstanceDto {
    public String functionId;
    public String name;
    public String group;
    public List<ArgumentDto> inputList;
    public List<ArgumentDto> outputList;

    public FunctionInstanceDto(String functionId, String name, String group, List<ArgumentDto> inputList, List<ArgumentDto> outputList) {
        this.functionId = functionId;
        this.name = name;
        this.group = group;
        this.inputList = inputList;
        this.outputList = outputList;
    }
}
