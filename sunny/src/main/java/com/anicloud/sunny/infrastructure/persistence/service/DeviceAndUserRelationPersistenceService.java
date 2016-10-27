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
    List<DeviceAndUserRelationDao> getRelationsByHashUserId(Long hashUserId);
}
