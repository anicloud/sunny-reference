package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureRunLogDao;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-15.
 */
@Service
public interface DeviceFeatureRunLogPersistenceService {
    public DeviceFeatureRunLogDao save(DeviceFeatureRunLogDao deviceFeatureRunLogDao);
    public void remove(String deviceFeatureRunLogNum);

    public DeviceFeatureRunLogDao getFeatureRunLogByNum(String deviceFeatureRunLogNum);
    public List<DeviceFeatureRunLogDao> getFeatureRunLogList(String hashUserId);
    public List<DeviceFeatureRunLogDao> getFeatureRunLogByDeviceAndUser(String identificationCode, String hashUserId);
}
