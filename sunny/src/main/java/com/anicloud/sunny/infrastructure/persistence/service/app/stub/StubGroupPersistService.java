package com.anicloud.sunny.infrastructure.persistence.service.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubGroupDao;

import java.util.Collection;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
public interface StubGroupPersistService {
    StubGroupDao save(StubGroupDao groupDao);
    StubGroupDao update(StubGroupDao groupDao);
    void delete(Integer groupId);

    List<StubGroupDao> findAll();
    StubGroupDao findById(Integer groupId);
    StubGroupDao findByName(String name);
    /**
     * get the Group List which is in the Collection
     * @param groupIds group is collection
     * @return list of the group
     */
    List<StubGroupDao> findByGroupIdIn(Collection<Integer> groupIds);
}