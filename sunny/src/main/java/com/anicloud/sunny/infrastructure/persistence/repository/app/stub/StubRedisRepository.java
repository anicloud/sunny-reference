package com.anicloud.sunny.infrastructure.persistence.repository.app.stub;

import com.anicloud.sunny.domain.model.app.stub.Stub;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubDao;
import com.anicloud.sunny.infrastructure.persistence.repository.redis.RedisRepository;
import com.anicloud.sunny.schedule.domain.strategy.Argument;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @autor zhaoyu
 * @date 16-3-12
 * @since JDK 1.7
 */
@Component
public class StubRedisRepository implements RedisRepository<StubDao> {

    private final static Logger LOGGER = LoggerFactory.getLogger(StubRedisRepository.class);

    @Resource(name = "sunnyRedisTemplate")
    private RedisTemplate<String, StubDao> redisTemplate;

    private String name = "sunnyStubList";

    public StubRedisRepository() {
    }

    public StubRedisRepository(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public List<StubDao> get(Object key) {
        return redisTemplate.opsForList().range(generateKey(key), 0, -1);
    }

    @Override
    public Long put(Object key, StubDao obj) {
        return redisTemplate.opsForList().leftPush(generateKey(key), obj);

    }

    @Override
    public Long put(Object key, List<StubDao> objList) {
        return redisTemplate.opsForList().leftPushAll(generateKey(key), objList);
    }

    @Override
    public Long evict(Object key, StubDao object) {
        return redisTemplate.opsForList().remove(generateKey(key), 0, object);
    }

    @Override
    public Long evict(Object key, List<StubDao> objectList) {
        Long affectedSize = 0L;
        for (StubDao stubDao : objectList) {
            affectedSize += evict(key, stubDao);
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

    private StubDao[] toArray(List<StubDao> objectList) {
        if (objectList == null) {
            throw new IllegalArgumentException("OBJECT SET IS NULL");
        }
        StubDao[] objectArr = new StubDao[objectList.size()];
        int i = 0;
        for (StubDao object : objectList) {
            objectArr[i++] = object;
        }
        return objectArr;
    }
}