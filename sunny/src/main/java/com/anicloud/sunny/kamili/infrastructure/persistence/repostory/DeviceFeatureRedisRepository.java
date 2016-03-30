package com.anicloud.sunny.kamili.infrastructure.persistence.repostory;

import com.anicloud.sunny.infrastructure.persistence.repository.redis.RedisRepository;
import com.anicloud.sunny.kamili.infrastructure.persistence.domain.DeviceFeatureDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MRK on 2016/3/17.
 */
@Component(value = "deviceFeatureRedisRepository")
public class DeviceFeatureRedisRepository implements RedisRepository<DeviceFeatureDao> {

    @Resource(name = "sunnyRedisTemplate")
    private RedisTemplate<String, DeviceFeatureDao> redisTemplate;
    private String name = "sunny";

    public DeviceFeatureRedisRepository() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<DeviceFeatureDao> get(Object key) {
        return redisTemplate.opsForList().range(generateKey(key), 0, -1);
    }

    @Override
    public Long put(Object key, DeviceFeatureDao obj) {
        return redisTemplate.opsForList().leftPush(generateKey(key), obj);
    }

    @Override
    public Long put(Object key, List<DeviceFeatureDao> objList) {
        return redisTemplate.opsForList().leftPushAll(generateKey(key), objList);
    }

    @Override
    public Long evict(Object key, DeviceFeatureDao object) {
        return redisTemplate.opsForList().remove(generateKey(key), 0, object);
    }

    @Override
    public Long evict(Object key, List<DeviceFeatureDao> objectList) {
        Long affectedSize = 0L;
        for (DeviceFeatureDao deviceFeature : objectList) {
            affectedSize += evict(key, deviceFeature);
        }
        return affectedSize;
    }

    @Override
    public void clearAll(Object key) {
        redisTemplate.delete(generateKey(key));
    }

    public String generateKey(Object key) {
        List list = Arrays.asList(name, key);
        return StringUtils.join(list, ":");
    }
}
