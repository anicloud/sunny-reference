package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureFunctionDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.FeatureFunctionRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wyf on 16-12-27.
 */
@Component
public class FeatureFunctionPersistenceEventHandler implements FeatureFunctionPersistenceService {
    @Resource
    private FeatureFunctionRepository functionRepository;
    @Override
    public FeatureFunctionDao getFunctionByStubIdAndGroupId(Integer stubId, Long groupId) {
        return functionRepository.findByStubIdAndGroupId(stubId,groupId);
    }
}
