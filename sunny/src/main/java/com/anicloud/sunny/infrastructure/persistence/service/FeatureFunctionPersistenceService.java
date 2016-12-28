package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureFunctionDao;
import org.springframework.stereotype.Service;

/**
 * Created by wyf on 16-12-27.
 */
@Service
public interface FeatureFunctionPersistenceService {
    public FeatureFunctionDao getFunctionByStubIdAndGroupId(Integer stubId, Long groupId);
}
