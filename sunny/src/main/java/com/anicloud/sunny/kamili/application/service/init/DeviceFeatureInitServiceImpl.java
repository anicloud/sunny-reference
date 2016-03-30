package com.anicloud.sunny.kamili.application.service.init;

import com.anicloud.sunny.infrastructure.persistence.repository.redis.RedisRepository;
import com.anicloud.sunny.kamili.domain.model.device.DeviceFeature;
import com.anicloud.sunny.kamili.application.utils.DeviceFeatureJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by MRK on 2016/3/15.
 */
@Service(value = "kaMiliDeviceFeatureInitService")
public class DeviceFeatureInitServiceImpl implements DeviceFeatureInitService {

    @Resource(name = "deviceFeatureRedisRepository")
    private RedisRepository<DeviceFeature> redisRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceFeatureInitServiceImpl.class);
    private final static String OBJECT_KEY = "deviceFeatureList";

    public List<DeviceFeature> initDeviceFeature() throws IOException {
        List<DeviceFeature> deviceFeatureList = DeviceFeatureJsonUtils.getDeviceFeatureList();
        Long affactLine = redisRepository.put(OBJECT_KEY, deviceFeatureList);
        LOGGER.info("affectLine : " + affactLine);
        return deviceFeatureList;
    }

    public List<DeviceFeature> getAll() throws IOException {
        // read from redis
        List<DeviceFeature> deviceFeatureList = redisRepository.get(OBJECT_KEY);
        // if null or affect line equal or less than 0 , read from json file

        if (deviceFeatureList == null || deviceFeatureList.size() <= 0) {
            List<DeviceFeature> deviceFeatures = DeviceFeatureJsonUtils.getDeviceFeatureList();
            LOGGER.info("Redis not find data....");
            return deviceFeatures;
        }
        return deviceFeatureList;
    }

}
