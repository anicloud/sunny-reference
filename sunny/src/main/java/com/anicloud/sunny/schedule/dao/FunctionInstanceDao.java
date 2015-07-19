package com.anicloud.sunny.schedule.dao;

import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
public class FunctionInstanceDao {
    public String functionId;
    public String name;
    public String group;
    public List<ArgumentDao> inputList;
    public List<ArgumentDao> outputList;

    public FunctionInstanceDao(String functionId, String name, String group,
                               List<ArgumentDao> inputList, List<ArgumentDao> outputList) {
        this.functionId = functionId;
        this.name = name;
        this.group = group;
        this.inputList = inputList;
        this.outputList = outputList;
    }
}
