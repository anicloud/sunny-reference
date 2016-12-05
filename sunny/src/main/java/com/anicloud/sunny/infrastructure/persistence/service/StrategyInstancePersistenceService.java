package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.schedule.persistence.dao.StrategyInstanceDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaoyu on 15-7-20.
 */
@Service
public interface StrategyInstancePersistenceService  {
    public StrategyInstanceDao save(StrategyInstanceDao strategyInstanceDao);
    public StrategyInstanceDao update(StrategyInstanceDao strategyInstanceDao);
    public StrategyInstanceDao getByStrategyId(String strategyId);
    public void remove(String strategyId);
    List<StrategyInstanceDao> findRunningStragegy(Long hashUserId);
}
