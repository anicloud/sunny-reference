package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureRunLogDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface DeviceFeatureRunLogRepository extends CrudRepository<DeviceFeatureRunLogDao, Long> {
    @Query(value = "select d from DeviceFeatureRunLogDao d where d.deviceFeatureRunLogNum = ?1")
    public DeviceFeatureRunLogDao findByDeviceFeatureRunLogNum(String deviceFeatureRunLogNum);

    @Query(value = "select d from DeviceFeatureRunLogDao d where d.owner.hashUserId = ?1")
    public List<DeviceFeatureRunLogDao> findListByUserHashId(String hashUserId);

    @Query(value = "select d from DeviceFeatureRunLogDao d where d.deviceDao.identificationCode = ?1 and d.owner.hashUserId = ?2")
    public List<DeviceFeatureRunLogDao> findListByDeviceAndUserHashId(String identificationCode, String hashUserId);
}
