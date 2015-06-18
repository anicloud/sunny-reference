package com.anicloud.sunny.infrastructure.persistence.repository.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.strategy.DeviceFeatureInstanceDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface DeviceFeatureInstanceRepository extends CrudRepository<DeviceFeatureInstanceDao, Long> {
    @Query(value = "select d from DeviceFeatureInstanceDao d where d.featureInstanceNum = ?1")
    public DeviceFeatureInstanceDao findByFeatureInstanceNum(String featureInstanceNum);
}
