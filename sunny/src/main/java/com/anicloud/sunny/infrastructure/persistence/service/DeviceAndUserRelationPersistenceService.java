package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndUserRelationDao;

import java.util.List;

/**
 * Created by wyf on 16-10-13.
 */
public interface DeviceAndUserRelationPersistenceService {
    DeviceAndUserRelationDao save(DeviceAndUserRelationDao relationDao);
    DeviceAndUserRelationDao modify(DeviceAndUserRelationDao relationDao);
    void remove(DeviceAndUserRelationDao relationDao);
    void removeRelationsWithDeviceId(String identificationCode);
    List<DeviceAndUserRelationDao> getRelationsByHashUserId(Long hashUserId);
    DeviceAndUserRelationDao getRelationByHashUserIdAndDeviceId(Long hashUserId, String identificationCode);
    List<Long> getHashUserIdByDeviceId(String identifyCode);
    List<DeviceAndUserRelationDao> getRelationsByDeviceId(String identificationCode);
}
