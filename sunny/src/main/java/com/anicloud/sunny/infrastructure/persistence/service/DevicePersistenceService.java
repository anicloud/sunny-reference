package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceState;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-14.
 */
@Service
public interface DevicePersistenceService {
    public DeviceDao save(DeviceDao deviceDao);
    public DeviceDao modify(DeviceDao deviceDao);
    public void remove(DeviceDao deviceDao);
    public void modifyDeviceState(DeviceDao device, DeviceState deviceState);

    public DeviceDao getDeviceByIdentificationCode(String identificationCode);
    public List<DeviceDao> getDeviceByUser(UserDao userDao);
    public List<DeviceDao> getDeviceByUserAndGroup(UserDao user, String deviceGroup);
    public List<DeviceDao> getDeviceByUserAndType(UserDao userDao, String deviceType);
    public List<DeviceDao> getDeviceByUserAndState(UserDao user, DeviceState deviceState);
    public List<String> getUserDeviceGroupList(UserDao user);
}
