package com.anicloud.sunny.application.service.app.stub;

import com.anicloud.sunny.domain.model.app.stub.Stub;

import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-14
 * @since JDK 1.7
 */
public interface StubRedisService {
    List get(Object key);
    void save(Object key, Stub obj);
    void save(Object key, List<Stub> objList);
    void remove(Object key, Stub object);
    void remove(Object key, List<Stub> objectSet);
    void clearAll(Object key);
}
