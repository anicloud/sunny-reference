package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;

import java.util.List;

/**
 * Created by wyf on 16-12-30.
 */
public interface StrategyInstanceService {
    void initStrategyInstance(StrategyInstance strategyInstance);
    void saveStrategyInstance(StrategyInstance strategyInstance);
    void operateStrategy(String strategyInstanceId, StrategyAction action);
    StrategyInstance getStrategyInstanceById(String strategyInstanceId);
    List<StrategyInstance> getStrategyInstanceByUser(Long hashUserId);
}
