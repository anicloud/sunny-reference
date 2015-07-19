package com.anicloud.sunny.schedule.domain.strategy;

import java.util.List;

/**
 * Created by huangbin on 7/18/15.
 */
public class FunctionInstance {
    public String functionId;
    public String name;
    public String group;
    public List<Argument> inputList;
    public List<Argument> outputList;

    public boolean execute() {
        return true;
    }

    public FunctionInstance(String functionId, String name, String group, List<Argument> inputList, List<Argument> outputList) {
        this.functionId = functionId;
        this.name = name;
        this.group = group;
        this.inputList = inputList;
        this.outputList = outputList;
    }
}
