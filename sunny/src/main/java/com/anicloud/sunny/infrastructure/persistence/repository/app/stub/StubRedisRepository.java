package com.anicloud.sunny.infrastructure.persistence.repository.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubDao;
import com.anicloud.sunny.infrastructure.persistence.repository.redis.RedisRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-12
 * @since JDK 1.7
 */
@Component
public class StubRedisRepository implements RedisRepository<StubDao> {

    @Resource(name = "sunnyRedisTemplate")
    private RedisTemplate<String, StubDao> redisTemplate;

    private String name;

    public StubRedisRepository() {
    }

    public StubRedisRepository(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<StubDao> get(Object key) {
        return null;
    }

    @Override
    public void put(Object key, StubDao obj) {

    }

    @Override
    public void put(Object key, List<StubDao> objList) {

    }
}
