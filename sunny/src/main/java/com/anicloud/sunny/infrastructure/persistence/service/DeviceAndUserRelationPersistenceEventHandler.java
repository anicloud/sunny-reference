package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndUserRelationDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceAndUserRelationRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.user.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wyf on 16-10-13.
 */
@Component
public class DeviceAndUserRelationPersistenceEventHandler implements DeviceAndUserRelationPersistenceService{

    @Resource
    private DeviceAndUserRelationRepository deviceAndUserRelationRepository;
    @Resource
    private DeviceRepository deviceRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public DeviceAndUserRelationDao save(DeviceAndUserRelationDao relationDao) {
        if (relationDao == null) return null;
        DeviceDao oldDevice = deviceRepository.findByIdentificationCode(relationDao.device.identificationCode);
        UserDao oldUser = userRepository.findByHashUserId(relationDao.user.hashUserId);
        if (oldDevice != null)
            relationDao.device.id = oldDevice.id;
        if (oldUser != null)
            relationDao.user.id = oldUser.id;
        return deviceAndUserRelationRepository.save(relationDao);
    }

    @Override
    public DeviceAndUserRelationDao modify(DeviceAndUserRelationDao relationDao) {
        DeviceAndUserRelationDao oldDao = deviceAndUserRelationRepository.findUniqueRelationByDeviceIdAndUserId(relationDao.device.identificationCode,relationDao.user.hashUserId);
        if(oldDao.id != null) {
            relationDao.id = oldDao.id;
            relationDao.device.id = oldDao.device.id;
            relationDao.user.id = oldDao.user.id;
        }
        return deviceAndUserRelationRepository.save(relationDao);
    }

    @Override
    public void remove(DeviceAndUserRelationDao relationDao) {
        relationDao = deviceAndUserRelationRepository.findUniqueRelationByDeviceIdAndUserId(relationDao.device.identificationCode,relationDao.user.hashUserId);
        if(relationDao == null)
            throw new EmptyResultDataAccessException(1);
        deviceAndUserRelationRepository.delete(relationDao);
    }

    @Override
    public void removeRelationsWithDeviceId(String identificationCode) {
        deviceAndUserRelationRepository.removeRelationsWithDeviceId(identificationCode);
    }

    @Override
    public List<DeviceAndUserRelationDao> getRelationsByHashUserId(Long hashUserId) {
        return deviceAndUserRelationRepository.findByUserId(hashUserId);
    }

    @Override
    public DeviceAndUserRelationDao getRelationByHashUserIdAndDeviceId(Long hashUserId, String identificationCode) {
        return deviceAndUserRelationRepository.findUniqueRelationByDeviceIdAndUserId(identificationCode,hashUserId);
    }

    @Override
    public List<Long> getHashUserIdByDeviceId(String identifyCode) {
        return deviceAndUserRelationRepository.findUserIdByDeviceId(identifyCode);
    }
}
