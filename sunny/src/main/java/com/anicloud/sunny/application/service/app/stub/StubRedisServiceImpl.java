package com.anicloud.sunny.application.service.app.stub;

import com.anicloud.sunny.domain.model.app.stub.Stub;
import com.anicloud.sunny.infrastructure.persistence.repository.app.stub.StubRedisRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.redis.RedisRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-14
 * @since JDK 1.7
 */
@Component(value = "stubRedisService")
public class StubRedisServiceImpl implements StubRedisService {

    @Resource
    private StubRedisRepository stubRedisRepository;

    @Override
    public List get(Object key) {
        // TODO
        return null;
    }

    @Override
    public void save(Object key, Stub obj) {
        // TODO
    }

    @Override
    public void save(Object key, List<Stub> objList) {
        // TODO
    }

    @Override
    public void remove(Object key, Stub object) {
        // TODO
    }

    @Override
    public void remove(Object key, List<Stub> objectSet) {
        // TODO
    }

    @Override
    public void clearAll(Object key) {
        // TODO
    }
}
