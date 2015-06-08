package com.anicloud.sunny.infrastructure.persistence.domain.share;

/**
 * Created by zhaoyu on 15-6-8.
 */
public enum  FunctionType {
    SYNC("SYNC"), ASYNC("ASYNC");

    private String functionType;
    private FunctionType(String functionType) {
        this.functionType = functionType;
    }

    public String getFunctionType() {
        return this.functionType;
    }
}
