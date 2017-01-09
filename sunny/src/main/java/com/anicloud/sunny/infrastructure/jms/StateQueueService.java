package com.anicloud.sunny.infrastructure.jms;

import com.anicloud.sunny.application.dto.JmsTypicalMessage;
import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;

import java.util.List;

/**
 * Created by lihui on 17-1-9.
 */
public interface StateQueueService {
    void updateState(JmsTypicalMessage message);

}
