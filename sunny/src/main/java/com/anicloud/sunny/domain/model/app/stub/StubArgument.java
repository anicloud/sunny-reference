package com.anicloud.sunny.domain.model.app.stub;

import java.io.Serializable;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public class StubArgument implements Serializable {
    private static final long serialVersionUID = 2380569542561491376L;

    public String name;
    public ArgumentType argumentType;

    public StubArgument() {
    }

    public StubArgument(String name, ArgumentType argumentType) {
        this.name = name;
        this.argumentType = argumentType;
    }

    @Override
    public String toString() {
        return "StubArgument{" +
                "name='" + name + '\'' +
                '}';
    }
}
