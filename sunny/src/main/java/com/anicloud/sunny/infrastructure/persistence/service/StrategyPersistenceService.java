package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.strategy.StrategyDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-17.
 */
@Service
public interface StrategyPersistenceService {
    public StrategyDao save(StrategyDao strategyDao);

    /**
     * just can modify the strategyName, StrategyState, description
     * @param strategyDao
     * @return
     */
    public StrategyDao modify(StrategyDao strategyDao);
    public void removeById(String strategyId);
    public StrategyDao getStrategyById(String strategyId);
    public List<StrategyDao> getStrategyListByUser(Long hashUserId);

}
