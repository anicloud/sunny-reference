package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.assemble.FeatureFunctionDtoAssembler;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.domain.model.device.FeatureFunction;
import com.anicloud.sunny.infrastructure.persistence.service.FeatureFunctionPersistenceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wyf on 16-12-27.
 */
@Service
public class FeatureFunctionServiceEventHandler implements FeatureFunctionService {
    @Resource
    private FeatureFunctionPersistenceService functionPersistenceService;
    @Override
    public FeatureFunctionDto getFeatureFunctionByStubIdAndGroupId(Integer stubId, Long groupId) {
        FeatureFunction featureFunction = FeatureFunction.getFeatureFunctionByStubIdAndGroupId(functionPersistenceService,stubId,groupId);
        return FeatureFunctionDtoAssembler.toDto(featureFunction);
    }
}
