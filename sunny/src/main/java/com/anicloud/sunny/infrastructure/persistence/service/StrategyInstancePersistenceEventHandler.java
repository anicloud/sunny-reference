package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import com.anicloud.sunny.schedule.persistence.dao.FeatureInstanceDao;
import com.anicloud.sunny.schedule.persistence.dao.StrategyInstanceDao;
import com.anicloud.sunny.infrastructure.persistence.repository.schedule.StrategyInstanceRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-20.
 */
@Component
public class StrategyInstancePersistenceEventHandler implements StrategyInstancePersistenceService {
    @Resource
    private StrategyInstanceRepository strategyInstanceRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public StrategyInstanceDao save(StrategyInstanceDao strategyInstanceDao) {
        return strategyInstanceRepository.save(strategyInstanceDao);
    }

    @Override
    public StrategyInstanceDao update(StrategyInstanceDao strategyInstanceDao) {
        StrategyInstanceDao orgInstance = strategyInstanceRepository.findByStrategyId(strategyInstanceDao.strategyInstanceId);
        strategyInstanceDao.id = orgInstance.id;
        return strategyInstanceRepository.save(strategyInstanceDao);
    }

    @Override
    public StrategyInstanceDao getByStrategyId(String strategyId) {
        return strategyInstanceRepository.findByStrategyId(strategyId);
    }

    @Override
    public void remove(String strategyId) {
        StrategyInstanceDao instanceDao = strategyInstanceRepository.findByStrategyId(strategyId);
        if (instanceDao != null) {
            strategyInstanceRepository.delete(instanceDao);
        }
    }

}
