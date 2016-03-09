package com.anicloud.sunny.application.service.app.stub;

import com.anicloud.sunny.domain.model.app.stub.StubGroup;
import com.anicloud.sunny.infrastructure.persistence.service.app.stub.StubGroupPersistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
@Service
@Transactional
public class StubGroupServiceImpl implements StubGroupService {
    private final static Logger LOGGER = LoggerFactory.getLogger(StubGroupServiceImpl.class);

    @Resource
    private StubGroupPersistService stubGroupPersistService;

    @Override
    public List<StubGroup> getAll() {
        // TODO by KaMIli
        return null;
    }

    @Override
    public List<StubGroup> getByGroupIdIn(Collection<Integer> groupIds) {
        // TODO by KaMIli
        return null;
    }

    @Override
    public StubGroup getById(Integer groupId) {
        // TODO by KaMIli
        return null;
    }

    @Override
    public StubGroup getByName(String name) {
        // TODO by KaMIli
        return null;
    }

    @Override
    public StubGroup save(StubGroup stubGroup) {
        // TODO by KaMIli
        return null;
    }

    @Override
    public StubGroup update(StubGroup stubGroup) {
        // TODO by KaMIli
        return null;
    }

    @Override
    public void delete(Integer groupId) {
        // TODO by KaMIli
    }
}
