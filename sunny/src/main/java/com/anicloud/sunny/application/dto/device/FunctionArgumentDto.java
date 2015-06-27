package com.anicloud.sunny.application.dto.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.DataType;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ObjectState;

import java.io.Serializable;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class FunctionArgumentDto implements Serializable {
    private static final long serialVersionUID = 7559936802952374282L;

    public String name;
    public DataType dataType;
    public ObjectState objectState;

    public FunctionArgumentDto() {
    }

    public FunctionArgumentDto(DataType dataType, String name,
                               ObjectState objectState) {
        this.dataType = dataType;
        this.name = name;
        this.objectState = objectState;
    }

    @Override
    public String toString() {
        return "FunctionArgumentDto{" +
                "dataType=" + dataType +
                ", name='" + name + '\'' +
                ", objectState=" + objectState +
                '}';
    }
}
