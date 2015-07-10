package com.anicloud.sunny.application.service.init;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;

import java.util.List;

/**
 * Created by zhaoyu on 15-7-10.
 */
public abstract class DeviceFeatureInitService {

    protected List<DeviceFeatureDto> deviceFeatureDtoList;

    public DeviceFeatureInitService() {}

    public abstract void initDeviceFeature();
    public abstract List<DeviceFeatureDto> getAll();
}
