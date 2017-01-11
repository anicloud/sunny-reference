package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndUserRelationDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wyf on 16-10-13.
 */
public interface DeviceAndUserRelationRepository extends JpaRepository<DeviceAndUserRelationDao, Long> {
    @Query(value = "select d from DeviceAndUserRelationDao d where d.user.hashUserId = ?1")
    List<DeviceAndUserRelationDao> findByUserId(Long hashUserId);
    @Query(value = "select d from DeviceAndUserRelationDao d where d.device.identificationCode = ?1 and d.user.hashUserId = ?2")
    DeviceAndUserRelationDao findUniqueRelationByDeviceIdAndUserId(String identificationCode, Long hashUserId);
    @Query(value = "select d.device.identificationCode from DeviceAndUserRelationDao d where d.user.hashUserId = ?1")
    List<String> findDeviceIdByUserId(Long hashUserId);
    @Modifying
    @Query(value = "DELETE FROM t_device_user_relation  WHERE device_id=(SELECT id FROM t_device t WHERE t.identification_code= ?1)",nativeQuery=true)
    int removeRelationsWithDeviceId(String identificationCode);
    @Query(value = "select d.user.hashUserId from DeviceAndUserRelationDao d where d.device.identificationCode = ?1")
    List<Long> findUserIdByDeviceId(String deviceId);
    @Query(value = "select d from DeviceAndUserRelationDao d where d.device.identificationCode = ?1")
    List<DeviceAndUserRelationDao> findByDeviceId(String deviceId);
}
