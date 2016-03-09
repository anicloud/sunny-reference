package com.anicloud.sunny.application.service.app.stub;

import com.anicloud.sunny.domain.model.app.stub.Stub;

import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
public interface StubService {
    List<Stub> getAll();
    Stub getById(Long stubId);

    Stub save(Stub stub);
    void delete(Long stubId);
    Stub update(Stub stub);
}
