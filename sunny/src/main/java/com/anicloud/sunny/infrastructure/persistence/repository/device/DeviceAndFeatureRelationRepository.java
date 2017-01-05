package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndFeatureRelationDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
public interface DeviceAndFeatureRelationRepository extends
        JpaRepository<DeviceAndFeatureRelationDao, Long>,JpaSpecificationExecutor {

    @Query(value = "select d from DeviceAndFeatureRelationDao d where d.deviceDao.identificationCode = ?1")
    DeviceAndFeatureRelationDao findByDeviceIdentificationCode(String identificationCode);
    @Query(value = "select d from DeviceAndFeatureRelationDao d where d.deviceDao.identificationCode in ?1")
    List<DeviceAndFeatureRelationDao> findByDeviceIds(List<Long> deviceIds);
}
