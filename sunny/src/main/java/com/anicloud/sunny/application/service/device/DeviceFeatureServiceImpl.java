package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.dto.device.DeviceFeatureInfoDto;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.infrastructure.persistence.redis.repository.DeviceFeatureRedisRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
@Service("deviceFeatureService")
public class DeviceFeatureServiceImpl implements DeviceFeatureService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceFeatureServiceImpl.class);

    private final static String OBJECT_KEY = "deviceFeatureList";
    @Resource(name = "deviceFeatureRedisRepository")
    private DeviceFeatureRedisRepository deviceFeatureRedisRepository;

    @Override
    public Set<DeviceFeature> getAll() {
        return deviceFeatureRedisRepository.get(OBJECT_KEY);
    }

    @Override
    public void delete(DeviceFeature deviceFeature) {
        if (deviceFeature == null) {
            LOGGER.debug("device feature is null.");
            throw new IllegalArgumentException("device feature is null.");
        }
        deviceFeatureRedisRepository.evict(OBJECT_KEY, deviceFeature);
    }

    @Override
    public void clearAll() {
        deviceFeatureRedisRepository.clearAll(OBJECT_KEY);
    }

    @Override
    public void save(DeviceFeature deviceFeature) {
        if (deviceFeature == null) {
            LOGGER.debug("device feature is null.");
            throw new IllegalArgumentException("device feature is null.");
        }
        deviceFeatureRedisRepository.put(OBJECT_KEY, deviceFeature);
    }

    @Override
    public void saveAll(List<DeviceFeature> deviceFeatures) {
        if (deviceFeatures == null || deviceFeatures.size() == 0) {
            LOGGER.debug("device feature list is null.");
            throw new IllegalArgumentException("device feature list is null.");
        }
        deviceFeatureRedisRepository.put(OBJECT_KEY, deviceFeatures);
    }

    @Override
    public DeviceFeature getDeviceFeature(int featureId) {
        Set<DeviceFeature> deviceFeatureList = getAll();
        for (DeviceFeature deviceFeature : deviceFeatureList) {
            if (deviceFeature.featureId == featureId) {
                return deviceFeature;
            }
        }
        return null;
    }

    @Override
    public DeviceFeature getDeviceFeature(String name) {
        if (StringUtils.isNotEmpty(name)) {
            Set<DeviceFeature> deviceFeatureList = getAll();
            for (DeviceFeature deviceFeature : deviceFeatureList) {
                if (deviceFeature.featureName.equals(name)) {
                    return deviceFeature;
                }
            }
        }
        return null;
    }

    @Override
    @Cacheable(value = "deviceFeatureListCache")
    public List<DeviceFeatureInfoDto> getAllDeviceFeatureInfo() {
        // TODO
        return null;
    }
}
