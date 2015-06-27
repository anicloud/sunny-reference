package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureRunLogDao;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceFeatureRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceFeatureRunLogRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.user.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-15.
 */
@Component
public class DeviceFeatureRunLogPersistenceEventHandler implements DeviceFeatureRunLogPersistenceService {

    @Resource
    private DeviceFeatureRunLogRepository deviceFeatureRunLogRepository;
    @Resource
    private DeviceRepository deviceRepository;
    @Resource
    private DeviceFeatureRepository deviceFeatureRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public DeviceFeatureRunLogDao save(DeviceFeatureRunLogDao deviceFeatureRunLogDao) {
        DeviceDao deviceDao = deviceRepository.findByIdentificationCode(deviceFeatureRunLogDao.deviceDao.identificationCode);
        DeviceFeatureDao deviceFeatureDao = deviceFeatureRepository.findByFeatureNum(deviceFeatureRunLogDao.deviceFeatureDao.featureNum);
        UserDao userDao = userRepository.findByHashUserId(deviceFeatureRunLogDao.owner.hashUserId);

        deviceFeatureRunLogDao.deviceDao = deviceDao;
        deviceFeatureRunLogDao.deviceFeatureDao = deviceFeatureDao;
        deviceFeatureRunLogDao.owner = userDao;

        return deviceFeatureRunLogRepository.save(deviceFeatureRunLogDao);
    }

    @Override
    public void remove(String deviceFeatureRunLogNum) {
        DeviceFeatureRunLogDao deviceFeatureRunLogDao = deviceFeatureRunLogRepository
                .findByDeviceFeatureRunLogNum(deviceFeatureRunLogNum);
        if (deviceFeatureRunLogDao != null) {
            deviceFeatureRunLogRepository.delete(deviceFeatureRunLogDao);
        }
    }

    @Override
    public DeviceFeatureRunLogDao getFeatureRunLogByNum(String deviceFeatureRunLogNum) {
        return deviceFeatureRunLogRepository.findByDeviceFeatureRunLogNum(deviceFeatureRunLogNum);
    }

    @Override
    public List<DeviceFeatureRunLogDao> getFeatureRunLogList(String hashUserId) {
        return deviceFeatureRunLogRepository.findListByUserHashId(hashUserId);
    }

    @Override
    public List<DeviceFeatureRunLogDao> getFeatureRunLogByDeviceAndUser(String identificationCode, String hashUserId) {
        return deviceFeatureRunLogRepository.findListByDeviceAndUserHashId(identificationCode, hashUserId);
    }
}
