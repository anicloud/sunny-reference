package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface DeviceFeatureRepository extends CrudRepository<DeviceFeatureDao, Long> {

    @Query(value = "select d from DeviceFeatureDao d where d.featureId = ?1")
    public DeviceFeatureDao findByFeatureId(String featureId);

    @Query(value = "select d from DeviceFeatureDao d where d.featureName = ?1")
    public DeviceFeatureDao findByFeatureName(String featureName);
}
