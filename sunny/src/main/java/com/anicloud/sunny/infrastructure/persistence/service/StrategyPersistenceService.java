package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.share.StrategyState;
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
    public void modifyStrategyState(String strategyNum, StrategyState state);
    public void removeByNum(String strategyNum);
    public StrategyDao getStrategyByNum(String strategyNum);
    public List<StrategyDao> getStrategyListByUser(String hashUserId);
    public List<StrategyDao> getStrategyByUserAndState(String hashUserId, StrategyState strategyState);
}
