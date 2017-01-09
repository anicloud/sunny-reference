package com.anicloud.sunny.infrastructure.jms;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;

import java.util.List;

/**
 * Created by lihui on 17-1-9.
 */
public interface StateQueueService {
    void updateBoundAndShareState(List<DeviceAndUserRelationDto> relationDtos);
    void updateUnBoundState(List<String> deviceIds);
}
