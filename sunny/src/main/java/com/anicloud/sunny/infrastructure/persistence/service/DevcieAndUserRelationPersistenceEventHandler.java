package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndUserRelationDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceAndUserRelationRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.user.UserRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wyf on 16-10-13.
 */
public class DevcieAndUserRelationPersistenceEventHandler implements DeviceAndUserRelationPersistenceService{

    @Resource
    private DeviceAndUserRelationRepository deviceAndUserRelationRepository;
    @Resource
    private DeviceRepository deviceRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public DeviceAndUserRelationDao save(DeviceAndUserRelationDao relationDao) {
        return null;
    }

    @Override
    public DeviceAndUserRelationDao modify(DeviceAndUserRelationDao relationDao) {
        return null;
    }

    @Override
    public void remove(DeviceAndUserRelationDao relationDao) {

    }

    @Override
    public List<DeviceAndUserRelationDao> getRelationsByHashUserId(Long hashUserId) {
        return null;
    }
}
