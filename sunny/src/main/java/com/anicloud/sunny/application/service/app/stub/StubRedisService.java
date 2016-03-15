package com.anicloud.sunny.application.service.app.stub;

import com.anicloud.sunny.application.exception.RedisDataException;
import com.anicloud.sunny.domain.model.app.stub.Stub;

import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-14
 * @since JDK 1.7
 */
public interface StubRedisService {
    List<Stub> get(Object key);
    void save(Object key, Stub obj) throws RedisDataException;
    void save(Object key, List<Stub> objList) throws RedisDataException;
    void remove(Object key, Stub object) throws RedisDataException;
    void remove(Object key, List<Stub> objectList) throws RedisDataException;
    void clearAll(Object key);
}
