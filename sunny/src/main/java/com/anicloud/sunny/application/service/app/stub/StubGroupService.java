package com.anicloud.sunny.application.service.app.stub;

import com.anicloud.sunny.domain.model.app.stub.StubGroup;

import java.util.Collection;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
public interface StubGroupService {
    List<StubGroup> getAll();
    List<StubGroup> getByGroupIdIn(Collection<Integer> groupIds);
    StubGroup getById(Integer groupId);
    StubGroup getByName(String name);

    StubGroup save(StubGroup stubGroup);
    StubGroup update(StubGroup stubGroup);
    void delete(Integer groupId);
}
