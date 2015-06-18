package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.assemble.DeviceFeatureInstanceDtoAssembler;
import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceAssembleDto;
import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstanceAssemble;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceFeatureInstanceAssemblePersistenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-18.
 */
@Service
@Transactional
public class DeviceFeatureInstanceAssembleServiceHandler implements DeviceFeatureInstanceAssembleService {

    @Resource
    private DeviceFeatureInstanceAssemblePersistenceService deviceFeatureInstanceAssemblePersistenceService;

    @Override
    public DeviceFeatureInstanceAssembleDto saveAssemble(DeviceFeatureInstanceAssembleDto assembleDto) {
        DeviceFeatureInstanceAssemble instanceAssemble = DeviceFeatureInstanceDtoAssembler.toAssemble(assembleDto);
        instanceAssemble = DeviceFeatureInstanceAssemble.save(deviceFeatureInstanceAssemblePersistenceService, instanceAssemble);
        return DeviceFeatureInstanceDtoAssembler.toAssembleDto(instanceAssemble);
    }

    @Override
    public List<DeviceFeatureInstanceAssembleDto> getFatherInstanceList(String featureInstanceNum) {
        List<DeviceFeatureInstanceAssemble> assembleList = DeviceFeatureInstanceAssemble
                .getFatherInstanceList(deviceFeatureInstanceAssemblePersistenceService, featureInstanceNum);
        return DeviceFeatureInstanceDtoAssembler.toAssembleDtoList(assembleList);
    }

    @Override
    public List<DeviceFeatureInstanceAssembleDto> getLeafInstanceList(String featureInstanceNum) {
        List<DeviceFeatureInstanceAssemble> assembleList = DeviceFeatureInstanceAssemble
                .getLeafInstanceList(deviceFeatureInstanceAssemblePersistenceService, featureInstanceNum);
        return DeviceFeatureInstanceDtoAssembler.toAssembleDtoList(assembleList);
    }
}
