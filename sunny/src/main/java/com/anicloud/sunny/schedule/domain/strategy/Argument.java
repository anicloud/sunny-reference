package com.anicloud.sunny.schedule.domain.strategy;

import java.io.Serializable;

/**
 * Created by huangbin on 7/19/15.
 */
public class Argument implements Serializable {
    public String name;
    public String value;

    public Argument() {
    }

    public Argument(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
