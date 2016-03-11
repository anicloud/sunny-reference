package com.anicloud.sunny.infrastructure.persistence.service.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubDao;

import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public interface StubPersistService {
    List<StubDao> findAll();
    StubDao findById(Long stubId);

    StubDao save(StubDao stubDao);
    void delete(Long stubId);
    StubDao update(StubDao stubDao);
}
