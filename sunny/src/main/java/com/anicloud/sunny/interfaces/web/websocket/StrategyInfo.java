package com.anicloud.sunny.interfaces.web.websocket;

import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;
import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;

/**
 * Created by sirhuoshan on 2015/7/25.
 */
public class StrategyInfo {
    public String strategyId;
    public String strategyName;
    public ScheduleState state;
    public Integer stage;
    public StrategyAction action;

    public static StrategyInfo convertSrategyToStrategyInfo(Strategy strategy){
        StrategyInfo strategyInfo = new StrategyInfo();
        strategyInfo.strategyId = strategy.strategyId;
        strategyInfo.strategyName = strategy.strategyName;
        strategyInfo.stage = strategy.strategyInstance.stage;
        strategyInfo.action = strategy.strategyInstance.action;
        return strategyInfo;
    }
}
