package com.anicloud.sunny.infrastructure.persistence.repository.redis;

import com.anicloud.sunny.domain.model.app.stub.Stub;

import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-11
 * @since JDK 1.7
 */
public interface RedisRepository<T> {
    String getName();
    List<T> get(Object key);
    Long put(Object key, T obj);
    Long put(Object key, List<T> objList);
    Long evict(Object key, T object);
    Long evict(Object key, List<T> objectList);
    void clearAll(Object key);
}
