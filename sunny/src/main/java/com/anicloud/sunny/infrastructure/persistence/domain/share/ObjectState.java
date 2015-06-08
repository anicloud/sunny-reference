package com.anicloud.sunny.infrastructure.persistence.domain.share;

/**
 * Created by zhaoyu on 15-6-8.
 */
public enum ObjectState {
    ACTIVE("ACTIVE"),
    DISABLE("DISABLE"),
    REMOVED("REMOVED");

    private String state;
    private ObjectState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
