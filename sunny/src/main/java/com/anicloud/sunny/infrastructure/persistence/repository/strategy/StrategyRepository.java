package com.anicloud.sunny.infrastructure.persistence.repository.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.strategy.StrategyDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface StrategyRepository extends JpaRepository<StrategyDao, Long>,JpaSpecificationExecutor {
    @Query(value = "select s from StrategyDao s where s.strategyId = ?1")
    StrategyDao findByStrategyNum(String strategyId);

    @Query(value = "select s from StrategyDao s where s.owner.hashUserId = ?1 ")
    List<StrategyDao> findByUserHashId(Long hashUserId);

    @Query(value = "select count(s) from StrategyDao s where s.owner.hashUserId= ?1")
    int countByHashUserId(Long hashUserId);
}
