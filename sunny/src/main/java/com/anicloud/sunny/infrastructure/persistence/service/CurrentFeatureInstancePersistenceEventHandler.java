package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.CurrentFeatureInstanceDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.CurrentFeatureInstanceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihui on 16-7-20.
 */
@Component
public class CurrentFeatureInstancePersistenceEventHandler implements CurrentFeatureInstancePersistenceService {
    @Resource
    private CurrentFeatureInstanceRepository repository;

    @Override
    public CurrentFeatureInstanceDao findCurrentFeatureInstance(String deviceId) {
        List<CurrentFeatureInstanceDao> list = repository.selectDeviceById(deviceId);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void updateCurrentFeatureInstance(Integer deviceNum, String deviceId) {
        repository.updateCurrentFeature(deviceNum, deviceId);
    }

    @Override
    public CurrentFeatureInstanceDao saveCurrentFeature(CurrentFeatureInstanceDao featureInstanceDao) {
        return repository.save(featureInstanceDao);
    }

    @Override
    public List<CurrentFeatureInstanceDao> findAll() {
        return repository.findAll();
    }

}
