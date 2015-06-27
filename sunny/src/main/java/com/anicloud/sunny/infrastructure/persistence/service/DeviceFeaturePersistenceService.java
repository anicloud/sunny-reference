package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-15.
 */
@Service
public interface DeviceFeaturePersistenceService {
    public DeviceFeatureDao save(DeviceFeatureDao deviceFeatureDao);
    public DeviceFeatureDao modify(DeviceFeatureDao deviceFeatureDao);
    public void remove(String deviceFeatureNum);

    public void batchInsert(List<DeviceFeatureDao> deviceFeatureDaoList);

    public DeviceFeatureDao getDeviceFeatureByNum(String deviceFeatureNum);
    public List<DeviceFeatureDao> getAllDeviceFeature();
}
