package com.anicloud.sunny.infrastructure.persistence.domain.share;

import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureFunctionDao;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhaoyu on 15-6-10.
 */
@MappedSuperclass
public abstract class AbstractFunctionValue extends AbstractEntity {
    private static final long serialVersionUID = 3665289944779693813L;

    @Column(name = "function_group", nullable = false)
    public String functionGroup;
    @Column(name = "function_name", nullable = false)
    public String functionName;

    @Column(name = "arg_name", length = 50)
    public String argName;
    @Column(name = "value", length = 255)
    public String value;

    public AbstractFunctionValue() {
    }

    public AbstractFunctionValue(String functionGroup, String functionName,
                                 String argName, String value) {
        this.functionGroup = functionGroup;
        this.functionName = functionName;
        this.argName = argName;
        this.value = value;
    }

    @Override
    public String toString() {
        return "AbstractFunctionValue{" +
                "value='" + value + '\'' +
                ", argName='" + argName + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionGroup='" + functionGroup + '\'' +
                '}';
    }
}
