package com.anicloud.sunny.application.service.init;

import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.infrastructure.utils.DeviceFeatureJsonUtils;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DeviceFeatureInitServiceImpl implements DeviceFeatureInitService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceFeatureInitServiceImpl.class);

    @Resource(name = "deviceFeatureService")
    private DeviceFeatureService deviceFeatureService;
    @Resource(name = "objectMapper")
    private ObjectMapper objectMapper;

    @Override
    @PostConstruct
    public void initDeviceFeature() {
        try {
            List<DeviceFeature> deviceFeatureList = DeviceFeatureJsonUtils.getDeviceFeatureList();
            deviceFeatureService.saveAll(deviceFeatureList);
            LOGGER.debug("initDeviceFeature, saved all. size is {}", deviceFeatureList.size());
        } catch (IOException e) {
            LOGGER.error("init device feature list error, {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
