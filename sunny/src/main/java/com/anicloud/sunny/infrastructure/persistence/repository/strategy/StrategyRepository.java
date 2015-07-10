package com.anicloud.sunny.infrastructure.persistence.repository.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.share.StrategyState;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.StrategyDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface StrategyRepository extends CrudRepository<StrategyDao, Long> {
    @Query(value = "select s from StrategyDao s where s.strategyId = ?1")
    public StrategyDao findByStrategyNum(String strategyId);

    @Query(value = "select s from StrategyDao s where s.owner.hashUserId = ?1 ")
    public List<StrategyDao> findByUserHashId(String hashUserId);

    @Query(value = "select s from StrategyDao s where s.owner.hashUserId = ?1 and s.state = ?2")
    public List<StrategyDao> findByUserHashIdAndState(String hashUserId, StrategyState state);

    @Modifying(clearAutomatically = true)
    @Query(value = "update StrategyDao s set s.state = ?2 where s.strategyId = ?1")
    public void updateStrategyStateByStrategyId(String strategyId, StrategyState state);
}
