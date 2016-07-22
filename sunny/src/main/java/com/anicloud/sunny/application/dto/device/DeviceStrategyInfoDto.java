package com.anicloud.sunny.application.dto.device;

/**
 * Created by lihui on 16-7-19.
 */
public class DeviceStrategyInfoDto {
    public int kind;//0:device 1:strategy
    public Object instance;
    public DeviceStrategyInfoDto(int kind, Object instance) {
        this.kind = kind;
        this.instance = instance;
    }
}
