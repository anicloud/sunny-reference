package com.anicloud.sunny.application.service.app.stub;

import com.anicloud.sunny.application.exception.RedisDataException;
import com.anicloud.sunny.domain.adapter.StubDaoAdapter;
import com.anicloud.sunny.domain.model.app.stub.Stub;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubDao;
import com.anicloud.sunny.infrastructure.persistence.repository.app.stub.StubRedisRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.redis.RedisRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<Stub> get(Object key) {
        if (key == null) {
            return null;
        }
        return StubDaoAdapter.toDomain(stubRedisRepository.get(key));
    }

    @Override
    public void save(Object key, Stub obj) throws RedisDataException {
        if (key == null || obj == null) {
            throw new IllegalArgumentException("OBJECT OR STUB IS NULL!");
        }
        Long affectedSize = stubRedisRepository
                .put(key, StubDaoAdapter.toDao(obj));
        if (affectedSize != 1) {
            throw new RedisDataException("SAVE ERROR, AFFECTED SIZE : " + affectedSize);
        }
    }

    @Override
    public void save(Object key, List<Stub> objList) throws RedisDataException {
        if (key == null || objList == null) {
            throw new IllegalArgumentException("OBJECT OR List<Stub> IS NULL!");
        }
        List<StubDao> stubDao = StubDaoAdapter.toDao(objList);
        stubRedisRepository.put(key, stubDao);
    }

    @Override
    public void remove(Object key, Stub object) throws RedisDataException {
        if (key == null || object == null) {
            throw new IllegalArgumentException("OBJECT OR List<Stub> IS NULL!");
        }
        stubRedisRepository.evict(key, StubDaoAdapter.toDao(object));
    }

    @Override
    public void remove(Object key, List<Stub> objectList) {
        if (key == null || objectList == null) {
            throw new IllegalArgumentException("OBJECT OR List<Stub> IS NULL!");
        }
        List<StubDao> stubDao = StubDaoAdapter.toDao(objectList);
        stubRedisRepository.put(key, stubDao);
    }

    @Override
    public void clearAll(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("OBJECT IS NULL!");
        }
        stubRedisRepository.clearAll(key);
    }
}
