package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureFunctionDao;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface FeatureFunctionRepository extends CrudRepository<FeatureFunctionDao, Long> {
}
