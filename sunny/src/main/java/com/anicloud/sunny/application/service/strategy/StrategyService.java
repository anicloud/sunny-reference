package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import com.anicloud.sunny.schedule.domain.strategy.StrategyState;
import com.anicloud.sunny.schedule.dto.StrategyInstanceDto;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-14.
 */
public interface StrategyService {
    /**
     * save the strategyDto and send the strategyInstanceDto into Schedule
     * @param strategyDto
     * @return
     */
    public StrategyDto saveStrategy(StrategyDto strategyDto);

    /**
     * for schedule to save the running info of the strategy
     * @param strategy
     */
    public void saveStrategy(Strategy strategy);
    public void operateStrategy(String strategyId, StrategyAction action);
    /**
     * just can modify the strategyName, description
     * @param strategyDto
     * @return
     */
    public StrategyDto modifyStrategy(StrategyDto strategyDto);
    public void removeStrategy(String strategyId);

    public StrategyDto getStrategyDtoById(String strategyId);
    public Strategy getStrategyById(String strategyId);
    public List<StrategyDto> getStrategyByUser(String hashUserId);
}
