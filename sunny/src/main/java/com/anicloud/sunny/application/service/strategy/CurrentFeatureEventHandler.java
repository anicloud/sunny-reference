package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.device.CurrentFeatureInstanceDao;
import com.anicloud.sunny.infrastructure.persistence.service.CurrentFeatureInstancePersistenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihui on 16-7-20.
 */
@Service("currentFeatureEventHandler")
@Transactional
public class CurrentFeatureEventHandler implements CurrentFeatureService {
    @Resource
    private CurrentFeatureInstancePersistenceService currentFeatureInstancePersistenceService;
    @Override
    public CurrentFeatureInstanceDao findCurrentFeatureByDeviceId(String deviceId) {
        return currentFeatureInstancePersistenceService.findCurrentFeatureInstance(deviceId);
    }

    @Override
    public void updateCurrentFeatureInstance(Integer deviceNum, String deviceId) {
        currentFeatureInstancePersistenceService.updateCurrentFeatureInstance(deviceNum, deviceId);
    }

    @Override
    public CurrentFeatureInstanceDao saveCurrentFeature(CurrentFeatureInstanceDao featureInstanceDao) {
        CurrentFeatureInstanceDao dao = findCurrentFeatureByDeviceId(featureInstanceDao.deviceId);
        if(dao == null) {
            return currentFeatureInstancePersistenceService.saveCurrentFeature(featureInstanceDao);
        } else {
            featureInstanceDao.deviceNum = featureInstanceDao.deviceNum+1;
            return currentFeatureInstancePersistenceService.saveCurrentFeature(featureInstanceDao);
        }
    }

    @Override
    public List<CurrentFeatureInstanceDao> findAll() {
        return currentFeatureInstancePersistenceService.findAll();
    }
}
