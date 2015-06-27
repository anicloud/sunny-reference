package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureRunLogDto;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface DeviceFeatureRunLogService {
    public DeviceFeatureRunLogDto saveDeviceFeatureRunLog(DeviceFeatureRunLogDto featureRunLogDto);
    public void removeDeviceFeatureRunLog(String deviceFeatureRunLogNum);

    public DeviceFeatureRunLogDto getDeviceFeatureRunLogByNum(String deviceFeatureRunLogNum);
    public List<DeviceFeatureRunLogDto> getDeviceFeatureRunLogByUser(String hashUserId);

    /**
     * fetch the Run Log by user and device
     * @param deviceDto
     * @param userDao
     * @return
     */
    public List<DeviceFeatureRunLogDto> getDeviceFeatureRunLogByDeviceAndUser(String identificationCode, String hashUserId);

}
