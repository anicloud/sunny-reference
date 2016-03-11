package com.anicloud.sunny.infrastructure.persistence.service.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-11
 * @since JDK 1.7
 */
@Component()
public class RedisStubPersistServiceImpl implements StubPersistService {
    @Resource(name = "sunnyRedisTemplate")
    private RedisTemplate<String, StubDao> redisTemplate;

    @Override
    public List<StubDao> findAll() {
        return null;
    }

    @Override
    public StubDao findById(Long stubId) {
        return null;
    }

    @Override
    public StubDao save(StubDao stubDao) {
        return null;
    }

    @Override
    public StubDao delete(StubDao stubDao) {
        return null;
    }

    @Override
    public StubDao update(StubDao stubDao) {
        return null;
    }
}
