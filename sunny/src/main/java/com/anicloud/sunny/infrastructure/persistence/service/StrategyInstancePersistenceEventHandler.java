package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.schedule.persistence.dao.StrategyInstanceDao;
import com.anicloud.sunny.infrastructure.persistence.repository.schedule.StrategyInstanceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 15-7-20.
 */
@Component
public class StrategyInstancePersistenceEventHandler implements StrategyInstancePersistenceService {
    @Resource
    private StrategyInstanceRepository strategyInstanceRepository;

    @Override
    public StrategyInstanceDao save(StrategyInstanceDao strategyInstanceDao) {
        return strategyInstanceRepository.save(strategyInstanceDao);
    }

    @Override
    public StrategyInstanceDao update(StrategyInstanceDao strategyInstanceDao) {
        StrategyInstanceDao orgInstance = strategyInstanceRepository.findByStrategyId(strategyInstanceDao.strategyId);
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
