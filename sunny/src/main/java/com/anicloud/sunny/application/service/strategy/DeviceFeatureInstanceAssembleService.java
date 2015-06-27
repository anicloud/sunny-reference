package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceAssembleDto;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-18.
 */
public interface DeviceFeatureInstanceAssembleService {
    public DeviceFeatureInstanceAssembleDto saveAssemble(DeviceFeatureInstanceAssembleDto assembleDto);
    public List<DeviceFeatureInstanceAssembleDto> getFatherInstanceList(String featureInstanceNum);
    public List<DeviceFeatureInstanceAssembleDto> getLeafInstanceList(String featureInstanceNum);
}
