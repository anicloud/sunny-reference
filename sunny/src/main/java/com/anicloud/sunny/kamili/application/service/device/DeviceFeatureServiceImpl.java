package com.anicloud.sunny.kamili.application.service.device;

import com.anicloud.sunny.infrastructure.persistence.repository.redis.RedisRepository;
import com.anicloud.sunny.kamili.domain.Adapter.DeviceFeatureDaoAdapter;
import com.anicloud.sunny.kamili.domain.model.device.DeviceFeature;
import com.anicloud.sunny.kamili.infrastructure.persistence.domain.DeviceFeatureDao;
import com.anicloud.sunny.kamili.infrastructure.persistence.repostory.DeviceFeatureRedisRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MRK on 2016/3/18.
 */
@Service
public class DeviceFeatureServiceImpl implements DeviceFeatureService {

    @Resource(name = "deviceFeatureRedisRepository")
    private DeviceFeatureRedisRepository deviceFeatureRedisRepository;
    private final static String OBJECT_KEY = "deviceFeatureList";

    @Override
    public List<DeviceFeature> getAll() {
        return DeviceFeatureDaoAdapter.toDomainByList(deviceFeatureRedisRepository.get(OBJECT_KEY));
    }

    @Override
    public void delete(DeviceFeature deviceFeature) {
        if (deviceFeature != null) {
            deviceFeatureRedisRepository.evict(OBJECT_KEY,
                    DeviceFeatureDaoAdapter.toDao(deviceFeature)
            );
        }
    }

    @Override
    public void clearAll() {
        deviceFeatureRedisRepository.clearAll(OBJECT_KEY);
    }

    @Override
    public void save(DeviceFeature deviceFeature) {
        if (deviceFeature != null) {
            deviceFeatureRedisRepository.put(OBJECT_KEY,
                    DeviceFeatureDaoAdapter.toDao(deviceFeature)
            );
        }
    }

    @Override
    public void saveAll(List<DeviceFeature> deviceFeatures) {
        if (deviceFeatures != null && deviceFeatures.size() > 0) {
            deviceFeatureRedisRepository.put(OBJECT_KEY,
                    DeviceFeatureDaoAdapter.toDaoByList(deviceFeatures)
            );
        }
    }
}
