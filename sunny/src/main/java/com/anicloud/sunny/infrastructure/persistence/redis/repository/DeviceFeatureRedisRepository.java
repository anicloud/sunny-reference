package com.anicloud.sunny.infrastructure.persistence.redis.repository;

import com.anicloud.sunny.domain.model.device.DeviceFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by MRK on 2016/3/17.
 */
@Component(value = "deviceFeatureRedisRepository")
public class DeviceFeatureRedisRepository implements RedisRepository<DeviceFeature> {
    private final static String SEPARATOR = ":";

    @Resource(name = "sunnyRedisTemplate")
    private RedisTemplate<String, DeviceFeature> redisTemplate;
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
    public Set<DeviceFeature> get(Object key) {
        return redisTemplate.opsForSet().members(generateKey(key));
    }

    @Override
    public Long put(Object key, DeviceFeature obj) {
        return redisTemplate.opsForSet().add(generateKey(key), obj);
    }

    @Override
    public Long put(Object key, List<DeviceFeature> objList) {
        return redisTemplate.opsForSet().add(generateKey(key), toArray(objList));
    }

    @Override
    public Long evict(Object key, DeviceFeature object) {
        return redisTemplate.opsForSet().remove(generateKey(key), object);
    }

    @Override
    public Long evict(Object key, List<DeviceFeature> objectList) {
        return redisTemplate.opsForSet().remove(
                generateKey(key),
                toArray(objectList)
        );
    }

    @Override
    public void clearAll(Object key) {
        redisTemplate.delete(generateKey(key));
    }

    public String generateKey(Object key) {
        List list = Arrays.asList(name, key);
        return StringUtils.join(list, SEPARATOR);
    }

    private DeviceFeature[] toArray(List<DeviceFeature> objectList) {
        if (objectList == null) {
            throw new IllegalArgumentException("OBJECT List IS NULL");
        }
        DeviceFeature[] objectArr = new DeviceFeature[objectList.size()];
        int i = 0;
        for (DeviceFeature object : objectList) {
            objectArr[i++] = object;
        }
        return objectArr;
    }
}
