package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndFeatureRelationDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
@Service
public interface DeviceAndFeatureRelationPersistenceService {
    public DeviceAndFeatureRelationDao saveRelation(DeviceAndFeatureRelationDao relationDao);
    public DeviceAndFeatureRelationDao getRelationByDeviceIdentificationCode(String identificationCode);
    public List<DeviceAndFeatureRelationDao> getAll();
    List<DeviceAndFeatureRelationDao> findByHashUserId(Long hashUserId);
}
