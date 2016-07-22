package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.device.CurrentFeatureInstanceDao;

import java.util.List;

/**
 * Created by lihui on 16-7-20.
 */
public interface CurrentFeatureService {
    public CurrentFeatureInstanceDao findCurrentFeatureByDeviceId(String deviceId);
    public void updateCurrentFeatureInstance(Integer deviceNum, String deviceId);
    public CurrentFeatureInstanceDao saveCurrentFeature(CurrentFeatureInstanceDao featureInstanceDao);
    public List<CurrentFeatureInstanceDao> findAll();
}
