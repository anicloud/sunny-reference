package com.anicloud.sunny.domain.model.app.stub;

import com.anicloud.sunny.domain.adapter.StubDaoAdapter;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubArgumentDao;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.Serializable;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Configurable
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
