package com.anicloud.sunny.infrastructure.jms;

import com.anicloud.sunny.application.dto.device.DeviceDto;

/**
 * Created by wyf on 16-7-11.
 */
public interface DeviceStateQueueService {
    public void updateDeviceState(DeviceDto deviceDto);
}
