package com.anicloud.sunny.application.service.init;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.infrastructure.utils.DeviceFeatureJsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhaoyu on 15-7-10.
 */
@Service(value = "deviceFeatureInitService")
@Transactional
public class DeviceFeatureInitServiceImpl extends DeviceFeatureInitService {
    private final static Logger LOGGER = org.slf4j.LoggerFactory
            .getLogger(DeviceFeatureInitServiceImpl.class);

    @Resource
    private DeviceFeatureService deviceFeatureService;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    @PostConstruct
    public void initDeviceFeature() {
        try {
            List<DeviceFeatureDto> deviceFeatureDtoList = DeviceFeatureJsonUtils.getDeviceFeatureDtoListFromJsonFile();
//            deviceFeatureService.batchSaveDeviceFeature(deviceFeatureDtoList);
            LOGGER.debug("device feature init success.");
        } catch (IOException e) {
            LOGGER.error("device feature init error. {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
