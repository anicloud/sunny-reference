package com.anicloud.sunny.infrastructure.persistence.repository.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.strategy.FeatureInstanceFunctionValueDao;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface FeatureInstanceFunctionValueRepository extends CrudRepository<FeatureInstanceFunctionValueDao, Long> {
}
