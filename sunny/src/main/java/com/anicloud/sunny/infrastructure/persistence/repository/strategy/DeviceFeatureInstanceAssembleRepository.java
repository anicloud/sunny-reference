package com.anicloud.sunny.infrastructure.persistence.repository.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.strategy.DeviceFeatureInstanceAssembleDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface DeviceFeatureInstanceAssembleRepository extends CrudRepository<DeviceFeatureInstanceAssembleDao, Long> {
    @Query(value = "select d from DeviceFeatureInstanceAssembleDao d where d.fatherFeatureInstanceDao.featureInstanceNum = ?1")
    public List<DeviceFeatureInstanceAssembleDao> getFatherInstanceList(String featureInstanceNum);

    @Query(value = "select d from DeviceFeatureInstanceAssembleDao d where d.assembleFeatureInstanceDao.featureInstanceNum = ?1")
    public List<DeviceFeatureInstanceAssembleDao> getLeafInstanceList(String featureInstanceNum);
}
