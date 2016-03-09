package com.anicloud.sunny.infrastructure.persistence.service.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Component
public class StubPersistServiceImpl implements StubPersistService {
    private final static Logger LOGGER = LoggerFactory.getLogger(StubPersistServiceImpl.class);

    @Resource
    private StubPersistService stubPersistService;

    @Override
    public List<StubDao> findAll() {
        // TODO
        return null;
    }

    @Override
    public StubDao findById(Long stubId) {
        // TODO by KaMIli
        return null;
    }

    @Override
    public StubDao save(StubDao stubDao) {
        // TODO by KaMIli
        //TODO
        return null;
    }

    @Override
    public StubDao delete(StubDao stubDao) {
        // TODO by KaMIli
        // TODO
        return null;
    }

    @Override
    public StubDao update(StubDao stubDao) {
        // TODO by KaMIli
        // TODO
        return null;
    }
}
