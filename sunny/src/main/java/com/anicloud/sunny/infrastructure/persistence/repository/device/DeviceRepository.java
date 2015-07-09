package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.ani.cel.service.manager.agent.core.share.DeviceState;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceLogicState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface DeviceRepository extends JpaRepository<DeviceDao, Long> {
    @Query(value = "select d from DeviceDao d where d.identificationCode = ?1")
    public DeviceDao findByIdentificationCode(String identificationCode);

    @Query(value = "select d from DeviceDao d where d.owner.hashUserId = ?1")
    public List<DeviceDao> findByUserHashId(String hashUserId);

    @Query(value = "select d from DeviceDao d where d.owner.email = ?1")
    public List<DeviceDao> findByUserEmail(String email);

    @Query(value = "select d from DeviceDao d where d.owner.hashUserId = ?1 and d.deviceGroup = ?2")
    public List<DeviceDao> findByUserAndGroup(String hashUserId, String deviceGroup);

    @Query(value = "select d from DeviceDao d where d.owner.hashUserId = ?1 and d.deviceType = ?2")
    public List<DeviceDao> findByUserAndType(String hashUserId, String deviceType);

    @Query(value = "select d from DeviceDao d where d.owner.hashUserId = ?1 and d.deviceState = ?2")
    public List<DeviceDao> findByUserAndState(String hashUserId, DeviceState deviceState);

    @Query(value = "select d from DeviceDao d where d.owner.hashUserId = ?1 and d.logicState = ?2")
    public List<DeviceDao> findByUserAndLogicState(String hashUserId, DeviceLogicState logicState);

    @Query(value = "select distinct d.deviceGroup from DeviceDao d where d.owner.hashUserId = ?1")
    public List<String> findDeviceGroupListByUser(String hashUserId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update DeviceDao d set d.deviceState = ?2 where d.identificationCode = ?1")
    public void updateDeviceStateByIdentificationCode(String identificationCode, DeviceState deviceState);

    @Modifying(clearAutomatically = true)
    @Query(value = "update DeviceDao d set d.logicState = ?2 where d.identificationCode = ?1")
    public void updateDeviceLogicStateByIdentificationCode(String identificationCode, DeviceLogicState deviceLogicState);
}
