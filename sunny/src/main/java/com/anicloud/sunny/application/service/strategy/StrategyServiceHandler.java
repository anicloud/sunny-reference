package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.assemble.StrategyDtoAssembler;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.utils.NumGenerate;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.infrastructure.persistence.domain.share.StrategyState;
import com.anicloud.sunny.infrastructure.persistence.service.StrategyPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-14.
 */
@Service
@Transactional
public class StrategyServiceHandler implements StrategyService {
    private final static Logger LOGGER = LoggerFactory.getLogger(StrategyServiceHandler.class);

    @Resource
    private StrategyPersistenceService strategyPersistenceService;

    @Override
    public StrategyDto saveStrategy(StrategyDto strategyDto) {
        // generate the strategy number
        strategyDto.strategyId = NumGenerate.generate();
        Strategy strategy = StrategyDtoAssembler.toStrategy(strategyDto);
        strategy = Strategy.save(strategyPersistenceService, strategy);
        return StrategyDtoAssembler.toDto(strategy);
    }

    @Override
    public StrategyDto modifyStrategy(StrategyDto strategyDto) {
        Strategy strategy = StrategyDtoAssembler.toStrategy(strategyDto);
        strategy = Strategy.modify(strategyPersistenceService, strategy);
        return StrategyDtoAssembler.toDto(strategy);
    }

    @Override
    public void removeStrategy(String strategyId) {
        Strategy.remove(strategyPersistenceService, strategyId);
    }

    @Override
    public void modifyStrategyState(String strategyId, StrategyState state) {
        Strategy.modifyStrategyState(strategyPersistenceService, strategyId, state);
    }

    @Override
    public StrategyDto getStrategyById(String strategyId) {
        Strategy strategy = Strategy.getStrategyById(strategyPersistenceService, strategyId);
        return StrategyDtoAssembler.toDto(strategy);
    }

    @Override
    public List<StrategyDto> getStrategyByUser(String hashUserId) {
        List<Strategy> strategyList = Strategy.getStrategyListByUser(strategyPersistenceService, hashUserId);
        return StrategyDtoAssembler.toDtoList(strategyList);
    }

    @Override
    public List<StrategyDto> getStrategyByUserAndState(String hashUserId, StrategyState strategyState) {
        List<Strategy> strategyList = Strategy.getStrategyByUserAndState(strategyPersistenceService, hashUserId, strategyState);
        return StrategyDtoAssembler.toDtoList(strategyList);
    }
}
