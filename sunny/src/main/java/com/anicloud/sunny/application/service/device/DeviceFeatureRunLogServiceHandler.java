package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.assemble.DeviceFeatureDtoAssembler;
import com.anicloud.sunny.application.assemble.DeviceFeatureRunLogDtoAssembler;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureRunLogDto;
import com.anicloud.sunny.application.utils.NumGenerate;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.domain.model.device.DeviceFeatureRunLog;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceFeatureRunLogPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
@Service
@Transactional
public class DeviceFeatureRunLogServiceHandler implements DeviceFeatureRunLogService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceFeatureRunLogServiceHandler.class);

    @Resource
    private DeviceFeatureRunLogPersistenceService deviceFeatureRunLogPersistenceService;

    @Override
    public DeviceFeatureRunLogDto saveDeviceFeatureRunLog(DeviceFeatureRunLogDto featureRunLogDto) {
        // generate the run log number
        featureRunLogDto.deviceFeatureRunLogNum = NumGenerate.generate();
        DeviceFeatureRunLog deviceFeatureRunLog = DeviceFeatureRunLog.save(deviceFeatureRunLogPersistenceService,
                DeviceFeatureRunLogDtoAssembler.toFeatureRunLog(featureRunLogDto));
        return DeviceFeatureRunLogDtoAssembler.toDto(deviceFeatureRunLog);
    }

    @Override
    public void removeDeviceFeatureRunLog(String deviceFeatureRunLogNum) {
        DeviceFeatureRunLog.remove(deviceFeatureRunLogPersistenceService, deviceFeatureRunLogNum);
    }

    @Override
    public DeviceFeatureRunLogDto getDeviceFeatureRunLogByNum(String deviceFeatureRunLogNum) {
        DeviceFeatureRunLog featureRunLog = DeviceFeatureRunLog
                .getDeviceFeatureRunLogByNum(deviceFeatureRunLogPersistenceService, deviceFeatureRunLogNum);
        return DeviceFeatureRunLogDtoAssembler.toDto(featureRunLog);
    }

    @Override
    public List<DeviceFeatureRunLogDto> getDeviceFeatureRunLogByUser(String hashUserId) {
        List<DeviceFeatureRunLog> logDtoList = DeviceFeatureRunLog
                .getDeviceFeatureRunLogByUser(deviceFeatureRunLogPersistenceService, hashUserId);
        return DeviceFeatureRunLogDtoAssembler.toDtoList(logDtoList);
    }

    @Override
    public List<DeviceFeatureRunLogDto> getDeviceFeatureRunLogByDeviceAndUser(String identificationCode, String hashUserId) {
        List<DeviceFeatureRunLog> featureRunLogList = DeviceFeatureRunLog
                .getDeviceFeatureRunLogByDeviceAndUser(deviceFeatureRunLogPersistenceService, identificationCode, hashUserId);
        return DeviceFeatureRunLogDtoAssembler.toDtoList(featureRunLogList);
    }
}
