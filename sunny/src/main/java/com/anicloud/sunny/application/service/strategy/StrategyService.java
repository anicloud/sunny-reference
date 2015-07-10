package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.infrastructure.persistence.domain.share.StrategyState;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-14.
 */
public interface StrategyService {
    public StrategyDto saveStrategy(StrategyDto strategyDto);

    /**
     * just can modify the strategyName, StrategyState, description
     * @param strategyDto
     * @return
     */
    public StrategyDto modifyStrategy(StrategyDto strategyDto);
    public void removeStrategy(String strategyId);

    public void modifyStrategyState(String strategyId, StrategyState state);
    public StrategyDto getStrategyById(String strategyId);
    public List<StrategyDto> getStrategyByUser(String hashUserId);
    public List<StrategyDto> getStrategyByUserAndState(String hashUserId, StrategyState strategyState);
}
