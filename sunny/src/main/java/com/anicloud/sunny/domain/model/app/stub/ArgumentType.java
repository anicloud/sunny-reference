package com.anicloud.sunny.domain.model.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.DataType;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Configurable
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

    @Override
    public String toString() {
        return "ArgumentType{" +
                "type=" + type +
                ", componentType=" + componentType +
                '}';
    }
}
