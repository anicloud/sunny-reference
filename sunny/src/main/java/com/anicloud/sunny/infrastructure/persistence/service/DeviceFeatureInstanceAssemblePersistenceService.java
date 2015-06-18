package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.strategy.DeviceFeatureInstanceAssembleDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-17.
 */
@Service
public interface DeviceFeatureInstanceAssemblePersistenceService {
    public DeviceFeatureInstanceAssembleDao save(DeviceFeatureInstanceAssembleDao dao);
    public List<DeviceFeatureInstanceAssembleDao> getFatherInstanceList(String featherInstanceNum);
    public List<DeviceFeatureInstanceAssembleDao> getLeafInstanceList(String featherInstanceNum);
}
