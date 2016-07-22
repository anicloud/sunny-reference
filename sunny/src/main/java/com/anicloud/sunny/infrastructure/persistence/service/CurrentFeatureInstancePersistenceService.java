package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.CurrentFeatureInstanceDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihui on 16-7-20.
 */
@Service
public interface CurrentFeatureInstancePersistenceService {
    public CurrentFeatureInstanceDao findCurrentFeatureInstance(String deviceId);
    public void updateCurrentFeatureInstance(Integer deviceNum, String deviceId);
    public CurrentFeatureInstanceDao saveCurrentFeature(CurrentFeatureInstanceDao featureInstanceDao);
    public List<CurrentFeatureInstanceDao> findAll();
}
