package com.anicloud.sunny.infrastructure.jms;

import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import org.springframework.stereotype.Service;

/**
 * Created by zhaoyu on 15-7-20.
 */
@Service
public interface StrategyStateQueueService {
    public void updateStrategyState(Strategy strategy);
}
