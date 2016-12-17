package com.anicloud.sunny.schedule.domain.strategy;


import com.ani.octopus.commons.stub.type.DataType;

import java.io.Serializable;

/**
 * Created by huangbin on 7/19/15.
 */
public class Argument implements Serializable {
    public String name;
    public String value;
    public DataType argumentType;
    public Argument() {
    }

    public Argument(String name, String value) {
        this.name = name;
        this.value = value;
    }
    public Argument(String name, String value,DataType argumentType) {
        this.name = name;
        this.value = value;
        this.argumentType = argumentType;
    }
}
