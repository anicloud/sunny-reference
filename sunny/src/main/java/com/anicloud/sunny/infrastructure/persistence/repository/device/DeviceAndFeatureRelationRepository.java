package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndFeatureRelationDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
public interface DeviceAndFeatureRelationRepository extends
        CrudRepository<DeviceAndFeatureRelationDao, Long> {

    @Query(value = "select d from DeviceAndFeatureRelationDao d where d.deviceDao.identificationCode = ?1")
    public DeviceAndFeatureRelationDao findByDeviceIdentificationCode(String identificationCode);

    @Query(value = "select d from DeviceAndFeatureRelationDao d")
    public List<DeviceAndFeatureRelationDao> findAllRelations();
}
