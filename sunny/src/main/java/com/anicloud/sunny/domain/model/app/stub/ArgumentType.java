package com.anicloud.sunny.domain.model.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.DataType;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public class ArgumentType {
    public DataType type;
    public ArgumentType componentType;

    public ArgumentType() {
    }

    public ArgumentType(DataType type) {
        this.type = type;
        this.componentType = null;
    }

    public ArgumentType(DataType type, ArgumentType componentType) {
        this.type = type;
        this.componentType = componentType;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public ArgumentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ArgumentType componentType) {
        this.componentType = componentType;
    }
}
