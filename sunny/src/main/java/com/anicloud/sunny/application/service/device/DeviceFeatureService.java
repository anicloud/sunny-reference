package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.dto.device.DeviceFeatureInfoDto;
import com.anicloud.sunny.domain.model.device.DeviceFeature;

import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface DeviceFeatureService {
    Set<DeviceFeature> getAll();
    void delete(DeviceFeature deviceFeature);
    void clearAll();
    void save(DeviceFeature deviceFeature);
    void saveAll(List<DeviceFeature> deviceFeatures);

    DeviceFeature getDeviceFeature(int featureId);
    DeviceFeature getDeviceFeature(String name);

    List<DeviceFeatureInfoDto> getAllDeviceFeatureInfo();
}
