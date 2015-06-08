package com.anicloud.sunny.infrastructure.persistence.domain.share;

/**
 * Created by zhaoyu on 15-6-8.
 */
public enum DataType {
    INTEGER("INTEGER"),
    PERCENTAGE("PERCENTAGE"),
    FLOAT("FLOAT"),
    STRING("STRING"),
    BOOLEAN("BOOLEAN"),
    OBJECT("OBJECT");

    private String type;
    private DataType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
