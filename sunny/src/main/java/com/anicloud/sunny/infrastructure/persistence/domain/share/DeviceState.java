package com.anicloud.sunny.infrastructure.persistence.domain.share;

/**
 * Created by zhaoyu on 15-6-8.
 */
public enum DeviceState {
    CONNECTED("CONNECTED"), DISCONNECTED("DISCONNECTED");

    private String state;

    private DeviceState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
