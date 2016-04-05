package com.anicloud.sunny.application.service.agent;

import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstance;

import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-4-1
 * @since JDK 1.7
 */
public interface DeviceFeatureCallProxy {
    List<Object> deviceFeatureCall(DeviceFeatureInstance deviceFeatureInstance);
}
