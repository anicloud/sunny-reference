package com.anicloud.sunny.infrastructure.persistence.repository.schedule;

import com.anicloud.sunny.schedule.persistence.dao.StrategyInstanceDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaoyu on 15-7-20.
 */
public interface StrategyInstanceRepository extends CrudRepository<StrategyInstanceDao, Long> {
    @Query(value = "select s from StrategyInstanceDao s where s.strategyId = ?1")
    public StrategyInstanceDao findByStrategyId(String strategyId);
}
