package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureFunctionDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface FeatureFunctionRepository extends CrudRepository<FeatureFunctionDao, Long> {
    @Query(value = "select d from FeatureFunctionDao d where d.stubId = ?1 and d.groupId = ?2")
    FeatureFunctionDao findByStubIdAndGroupId(Integer stubId, Long groupId);
}
