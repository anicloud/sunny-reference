package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;

/**
 * Created by wyf on 16-12-27.
 */
public interface FeatureFunctionService {
    FeatureFunctionDto getFeatureFunctionByStubIdAndGroupId(Integer stubId, Long groupId);
}
