package com.anicloud.sunny.application.service.app.stub;

import com.anicloud.sunny.domain.adapter.StubDaoAdapter;
import com.anicloud.sunny.domain.adapter.StubGroupAdapter;
import com.anicloud.sunny.domain.model.app.stub.ArgumentType;
import com.anicloud.sunny.domain.model.app.stub.StubArgument;
import com.anicloud.sunny.domain.model.app.stub.StubGroup;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.*;
import com.anicloud.sunny.infrastructure.persistence.service.app.stub.StubGroupPersistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<StubGroup> groupList = null;
        List<StubGroupDao> groupDaoList = stubGroupPersistService.findAll();
        if (groupDaoList != null) {
            for (StubGroupDao st : groupDaoList) {
                groupList.add(StubGroupAdapter.toDomain(st));
            }
        }
        return groupList;
    }

    @Override
    public List<StubGroup> getByGroupIdIn(Collection<Integer> groupIds) {
        List<StubGroup> groupList = null;
        List<StubGroupDao> groupDaoList = stubGroupPersistService.findByGroupIdIn(groupIds);
        if (groupDaoList != null) {
            for (StubGroupDao st : groupDaoList) {
                groupList.add(StubGroupAdapter.toDomain(st));
            }
        }
        return groupList;
    }

    @Override
    public StubGroup getById(Integer groupId) {
        return StubGroupAdapter.toDomain(stubGroupPersistService.findById(groupId));
    }

    @Override
    public StubGroup getByName(String name) {
        return StubGroupAdapter.toDomain(stubGroupPersistService.findByName(name));
    }

    @Override
    public StubGroup save(StubGroup stubGroup) {
        if (stubGroup != null) {
            return stubGroup.save();
        }
        return null;
    }

    @Override
    public StubGroup update(StubGroup stubGroup) {
        if (stubGroup != null) {
            return stubGroup.update();
        }
        return null;
    }

    @Override
    public void delete(Integer groupId) {
        stubGroupPersistService.delete(groupId);
    }



}


