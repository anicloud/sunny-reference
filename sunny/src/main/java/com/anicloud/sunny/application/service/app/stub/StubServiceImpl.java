package com.anicloud.sunny.application.service.app.stub;

import com.anicloud.sunny.domain.adapter.StubDaoAdapter;
import com.anicloud.sunny.domain.adapter.StubGroupAdapter;
import com.anicloud.sunny.domain.model.app.stub.Stub;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.*;
import com.anicloud.sunny.infrastructure.persistence.service.app.stub.StubGroupPersistService;
import com.anicloud.sunny.infrastructure.persistence.service.app.stub.StubPersistService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
@Service
@Transactional
public class StubServiceImpl implements StubService {

    @Resource
    private StubPersistService stubPersistService;
    @Resource
    private StubGroupPersistService stubGroupPersistService;

    @Override
    public List<Stub> getAll() {
        List<Stub> stubList = null;
        List<StubDao> stubDaoList = stubPersistService.findAll();
        if (stubDaoList != null) {
            for (StubDao st : stubDaoList) {
                stubList.add(StubDaoAdapter.toDomain(st));
            }
        }
        return stubList;
    }

    @Override
    public Stub getById(Long stubId) {
        return StubDaoAdapter.toDomain(stubPersistService.findById(stubId));
    }

    @Override
    public Stub save(Stub stub) {
        if (stub != null) {
            return stub.save();
        }
        return null;
    }

    @Override
    public void delete(Long stubId) {
        stubPersistService.delete(stubId);
    }

    @Override
    public Stub update(Stub stub) {
        if (stub != null) {
            return stub.update();
        }
        return null;
    }
}