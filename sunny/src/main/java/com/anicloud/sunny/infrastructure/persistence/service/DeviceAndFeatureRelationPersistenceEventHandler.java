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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
@Component
public class DeviceAndFeatureRelationPersistenceEventHandler implements DeviceAndFeatureRelationPersistenceService {

    @Resource
    private DeviceAndFeatureRelationRepository deviceAndFeatureRelationRepository;
    @Resource
    private DeviceRepository deviceRepository;
    @Resource
    private DeviceFeatureRepository deviceFeatureRepository;
    @Resource
    private DeviceAndUserRelationRepository deviceAndUserRelationRepository;

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
        return deviceAndFeatureRelationRepository.save(relationDao);
    }

    @Override
    public DeviceAndFeatureRelationDao getRelationByDeviceIdentificationCode(String identificationCode) {
        return deviceAndFeatureRelationRepository.findByDeviceIdentificationCode(identificationCode);
    }

    @Override
    public List<DeviceAndFeatureRelationDao> getAll() {
        Iterable<DeviceAndFeatureRelationDao> iterable = deviceAndFeatureRelationRepository.findAll();
        List<DeviceAndFeatureRelationDao> deviceAndFeatureRelationDaoList = IteratorUtils.toList(iterable.iterator());
        return deviceAndFeatureRelationDaoList;
    }

    @Override
    public List<DeviceAndFeatureRelationDao> findByHashUserId(Long hashUserId) {
        List<Long> deviceIds = deviceAndUserRelationRepository.findDeviceIdByUserId(hashUserId);
        return deviceAndFeatureRelationRepository.findByDeviceIds(deviceIds);
    }

}
