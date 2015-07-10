package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureFunctionDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceFeatureRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.FeatureFunctionRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.FunctionArgumentRepository;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-15.
 */
@Component
public class DeviceFeaturePersistenceEventHandler implements DeviceFeaturePersistenceService {

    @Resource
    private DeviceFeatureRepository deviceFeatureRepository;
    @Resource
    private FeatureFunctionRepository featureFunctionRepository;
    @Resource
    private FunctionArgumentRepository functionArgumentRepository;

    @Override
    public DeviceFeatureDao save(DeviceFeatureDao deviceFeatureDao) {
        /*Set<FeatureFunctionDao> deviceFeatureDaoSet = deviceFeatureDao.featureFunctionDaoSet;
        for (FeatureFunctionDao featureFunctionDao : deviceFeatureDaoSet) {
            functionArgumentRepository.save(featureFunctionDao.functionArgumentDaoSet);
            featureFunctionRepository.save(featureFunctionDao);
        }*/
        deviceFeatureDao = deviceFeatureRepository.save(deviceFeatureDao);
        return deviceFeatureDao;
    }

    @Override
    public DeviceFeatureDao modify(DeviceFeatureDao deviceFeatureDao) {
        DeviceFeatureDao orgDeviceFeatureDao =
                deviceFeatureRepository.findByFeatureId(deviceFeatureDao.featureId);
        if (orgDeviceFeatureDao == null) {
            throw new EmptyResultDataAccessException(1);
        }
        orgDeviceFeatureDao.featureName = deviceFeatureDao.featureName;
        orgDeviceFeatureDao.description = deviceFeatureDao.description;
        return deviceFeatureDao;
    }

    @Override
    public void remove(String deviceFeatureId) {
        DeviceFeatureDao deviceFeatureDao = deviceFeatureRepository.findByFeatureId(deviceFeatureId);
        if (deviceFeatureDao == null) {
            throw new EmptyResultDataAccessException(1);
        }
        deviceFeatureRepository.delete(deviceFeatureDao);
    }

    @Override
    public void batchInsert(List<DeviceFeatureDao> deviceFeatureDaoList) {
        deviceFeatureRepository.save(deviceFeatureDaoList);
    }

    @Override
    public DeviceFeatureDao getDeviceFeatureById(String featureId) {
        return deviceFeatureRepository.findByFeatureId(featureId);
    }

    @Override
    public DeviceFeatureDao getDeviceFeatureByName(String deviceFeatureName) {
        return deviceFeatureRepository.findByFeatureName(deviceFeatureName);
    }

    @Override
    public List<DeviceFeatureDao> getAllDeviceFeature() {
        Iterable<DeviceFeatureDao> iterable = deviceFeatureRepository.findAll();
        List<DeviceFeatureDao> deviceFeatureDaoList = IteratorUtils.toList(iterable.iterator());
        return deviceFeatureDaoList;
    }
}
