package com.anicloud.sunny.infrastructure.persistence.service.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubGroupDao;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
@Component
public class StubGroupPersistServiceImpl implements StubGroupPersistService {
    @Override
    public StubGroupDao save(StubGroupDao groupDao) {
        // TODO by KaMIli
        return null;
    }

    @Override
    public StubGroupDao update(StubGroupDao groupDao) {
        // TODO by KaMIli
        return null;
    }

    @Override
    public void delete(Integer groupId) {
        // TODO by KaMIli
    }

    @Override
    public List<StubGroupDao> findAll() {
        // TODO by KaMIli
        return null;
    }

    @Override
    public StubGroupDao findById(Integer groupId) {
        // TODO by KaMIli
        return null;
    }

    @Override
    public StubGroupDao findByName(String name) {
        // TODO by KaMIli
        return null;
    }

    @Override
    public List<StubGroupDao> findByGroupIdIn(Collection<Integer> groupIds) {
        // TODO by KaMIli
        return null;
    }
}
