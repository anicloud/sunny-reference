package com.anicloud.sunny.application.dto.device;

import com.ani.agent.service.commons.object.enumeration.DataType;
import java.io.Serializable;

/**
 * Created by zhaoyu on 15-7-9.
 */
public class ArgumentDto implements Serializable {

    public String name;
    public DataType dataType;

    public ArgumentDto() {
    }

    public ArgumentDto(DataType dataType, String name) {
        this.dataType = dataType;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ArgumentDto{" +
                "dataType=" + dataType +
                ", name='" + name + '\'' +
                '}';
    }
}
