package com.anicloud.sunny.infrastructure.persistence.service;

import com.ani.cel.service.manager.agent.core.share.DeviceState;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceLogicState;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.user.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-14.
 */
@Component
public class DevicePersistenceEventHandler implements DevicePersistenceService {

    @Resource
    private DeviceRepository deviceRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public DeviceDao save(DeviceDao deviceDao) {
        UserDao userDao = userRepository.findByHashUserId(deviceDao.owner.hashUserId);
        deviceDao.owner = userDao;
        return deviceRepository.save(deviceDao);
    }

    @Override
    public DeviceDao modify(DeviceDao deviceDao) {
        DeviceDao orgDeviceDao = deviceRepository.findByIdentificationCode(deviceDao.identificationCode);
        orgDeviceDao.name = deviceDao.name;
        orgDeviceDao.deviceState = deviceDao.deviceState;
        orgDeviceDao.deviceType = deviceDao.deviceType;
        orgDeviceDao.deviceGroup = deviceDao.deviceGroup;

        orgDeviceDao = deviceRepository.save(orgDeviceDao);
        return orgDeviceDao;
    }

    @Override
    public void remove(DeviceDao deviceDao) {
        deviceDao = deviceRepository.findByIdentificationCode(deviceDao.identificationCode);
        if (deviceDao == null) {
            throw new EmptyResultDataAccessException(1);
        }
        deviceRepository.delete(deviceDao);
    }

    @Override
    public void modifyDeviceState(DeviceDao device, DeviceState deviceState) {
        deviceRepository.updateDeviceStateByIdentificationCode(device.identificationCode, deviceState);
    }

    @Override
    public void modifyDeviceLogicState(DeviceDao device, DeviceLogicState logicState) {
        deviceRepository.updateDeviceLogicStateByIdentificationCode(device.identificationCode, logicState);
    }

    @Override
    public DeviceDao getDeviceByIdentificationCode(String identificationCode) {
        return deviceRepository.findByIdentificationCode(identificationCode);
    }

    @Override
    public List<DeviceDao> getDeviceByUser(UserDao user) {
        List<DeviceDao> deviceDaoList = null;
        if (StringUtils.isNotEmpty(user.hashUserId.toString())) {
            deviceDaoList = deviceRepository.findByUserHashId(user.hashUserId);
        }
        else if (StringUtils.isNotEmpty(user.email)) {
            deviceDaoList = deviceRepository.findByUserEmail(user.email);
        }
        return deviceDaoList;
    }

    @Override
    public List<DeviceDao> getDeviceByUserAndGroup(UserDao user, String deviceGroup) {
        return deviceRepository.findByUserAndGroup(user.hashUserId, deviceGroup);
    }

    @Override
    public List<DeviceDao> getDeviceByUserAndType(UserDao userDao, String deviceType) {
        return deviceRepository.findByUserAndType(userDao.hashUserId, deviceType);
    }

    @Override
    public List<DeviceDao> getDeviceByUserAndState(UserDao user, DeviceState deviceState) {
        return deviceRepository.findByUserAndState(user.hashUserId, deviceState);
    }

    @Override
    public List<DeviceDao> getDeviceByUserAndLogicState(UserDao user, DeviceLogicState logicState) {
        return deviceRepository.findByUserAndLogicState(user.hashUserId, logicState);
    }

    @Override
    public List<String> getUserDeviceGroupList(UserDao user) {
        return deviceRepository.findDeviceGroupListByUser(user.hashUserId);
    }
}
