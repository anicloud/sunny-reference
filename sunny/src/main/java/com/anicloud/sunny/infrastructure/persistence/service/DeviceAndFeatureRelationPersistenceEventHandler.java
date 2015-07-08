package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndFeatureRelationDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceFeatureRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceAndFeatureRelationRepository;
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

    @Override
    public DeviceAndFeatureRelationDao saveRelation(DeviceAndFeatureRelationDao relationDao) {
        DeviceDao deviceDao = deviceRepository.findByIdentificationCode(relationDao.deviceDao.identificationCode);
        List<DeviceFeatureDao> deviceFeatureDaoList = new ArrayList<>();
        for (DeviceFeatureDao deviceFeatureDao : relationDao.deviceFeatureDaoList) {
            DeviceFeatureDao deviceFeature = deviceFeatureRepository.findByFeatureName(deviceFeatureDao.featureName);
            deviceFeatureDaoList.add(deviceFeature);
        }
        relationDao.deviceDao = deviceDao;
        relationDao.deviceFeatureDaoList = deviceFeatureDaoList;
        return relationOfDeviceAndFeatureRepository.save(relationDao);
    }

    @Override
    public DeviceAndFeatureRelationDao getRelationByDeviceIdentificationCode(String identificationCode) {
        return relationOfDeviceAndFeatureRepository.findByDeviceIdentificationCode(identificationCode);
    }
}
