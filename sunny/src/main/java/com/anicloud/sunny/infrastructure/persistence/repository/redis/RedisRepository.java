package com.anicloud.sunny.infrastructure.persistence.repository.redis;

import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-11
 * @since JDK 1.7
 */
public interface RedisRepository<T> {
    String getName();
    List<T> get(Object key);
    void put(Object key, T obj);
    void put(Object key, List<T> objList);
}
