package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndUserRelationDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndFeatureRelationDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceAndUserRelationRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceFeatureRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceAndFeatureRelationRepository;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
@Component
public class DeviceAndFeatureRelationPersistenceEventHandler implements DeviceAndFeatureRelationPersistenceService {

    @Resource
    private DeviceAndFeatureRelationRepository relationOfDeviceAndFeatureRepository;
    @Resource
    private DeviceRepository deviceRepository;
    @Resource
    private DeviceFeatureRepository deviceFeatureRepository;
    @Resource
    private DeviceAndUserRelationRepository deviceAndUserRelationRepository;

    @Override
    public DeviceAndFeatureRelationDao saveRelation(DeviceAndFeatureRelationDao relationDao) {
        DeviceAndUserRelationDao deviceAndUserRelationDao = deviceAndUserRelationRepository.findUniqueRelationByDeviceIdAndUserId(relationDao.deviceAndUserRelationDao.device.identificationCode,relationDao.deviceAndUserRelationDao.user.hashUserId);
        List<DeviceFeatureDao> deviceFeatureDaoList = new ArrayList<>();
        for (DeviceFeatureDao deviceFeatureDao : relationDao.deviceFeatureDaoList) {
            DeviceFeatureDao deviceFeature = deviceFeatureRepository.findByFeatureName(deviceFeatureDao.featureName);
            deviceFeatureDaoList.add(deviceFeature);
        }
        relationDao.deviceAndUserRelationDao = deviceAndUserRelationDao;
        relationDao.deviceFeatureDaoList = deviceFeatureDaoList;
        return relationOfDeviceAndFeatureRepository.save(relationDao);
    }

    @Override
    public DeviceAndFeatureRelationDao getRelationByDeviceIdentificationCode(String identificationCode) {
        return relationOfDeviceAndFeatureRepository.findByDeviceIdentificationCode(identificationCode);
    }

    @Override
    public List<DeviceAndFeatureRelationDao> getAll(Long hashUserId) {
        return relationOfDeviceAndFeatureRepository.findByUserId(hashUserId);
    }
}
