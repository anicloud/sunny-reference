package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureInfoDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;

import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface DeviceFeatureService {
    /**
     * generate the feature num by UUID
     * @param deviceFeatureDto
     * @return
     */

    public DeviceFeatureDto saveDeviceFeature(DeviceFeatureDto deviceFeatureDto);

    public void batchSaveDeviceFeature(List<DeviceFeatureDto> deviceFeatureDtoList);

    /**
     * just can modify feature name and description
     * @param deviceFeatureDto
     * @return
     */
    public DeviceFeatureDto modifyDeviceFeature(DeviceFeatureDto deviceFeatureDto);
    public void removeDeviceFeature(String deviceFeatureId);
    public DeviceFeatureDto getDeviceFeatureById(String deviceFeatureId);
    public List<DeviceFeatureDto> getAllDeviceFeature();
    public List<DeviceFeatureInfoDto> getAllDeviceFeatureInfo();
}
