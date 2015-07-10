package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.assemble.DeviceFeatureDtoAssembler;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.utils.NumGenerate;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceFeaturePersistenceService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
@Service
@Transactional
public class DeviceFeatureEventHandler implements DeviceFeatureService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceFeatureEventHandler.class);

    @Resource
    private DeviceFeaturePersistenceService deviceFeaturePersistenceService;

    @Override
    public DeviceFeatureDto saveDeviceFeature(DeviceFeatureDto deviceFeatureDto) {
        if (StringUtils.isEmpty(deviceFeatureDto.featureId)) {
            deviceFeatureDto.featureId = NumGenerate.generate();
        }

        DeviceFeature deviceFeature = DeviceFeature.save(deviceFeaturePersistenceService,
                DeviceFeatureDtoAssembler.toDeviceFeature(deviceFeatureDto));
        return DeviceFeatureDtoAssembler.toDto(deviceFeature);
    }

    @Override
    public void batchSaveDeviceFeature(List<DeviceFeatureDto> deviceFeatureDtoList) {
        for (DeviceFeatureDto deviceFeatureDto : deviceFeatureDtoList) {
            saveDeviceFeature(deviceFeatureDto);
        }
    }

    @Override
    public DeviceFeatureDto modifyDeviceFeature(DeviceFeatureDto deviceFeatureDto) {
        DeviceFeature deviceFeature = DeviceFeatureDtoAssembler.toDeviceFeature(deviceFeatureDto);
        deviceFeature = DeviceFeature.modify(deviceFeaturePersistenceService, deviceFeature);
        return DeviceFeatureDtoAssembler.toDto(deviceFeature);
    }

    @Override
    public void removeDeviceFeature(String deviceFeatureNum) {
        DeviceFeature.remove(deviceFeaturePersistenceService, deviceFeatureNum);
    }

    @Override
    public DeviceFeatureDto getDeviceFeatureByNum(String deviceFeatureNum) {
        DeviceFeature deviceFeature = DeviceFeature
                .getDeviceFeatureByNum(deviceFeaturePersistenceService, deviceFeatureNum);

        return DeviceFeatureDtoAssembler.toDto(deviceFeature);
    }

    @Override
    public List<DeviceFeatureDto> getAllDeviceFeature() {
        List<DeviceFeature> deviceFeatureList = DeviceFeature.getAll(deviceFeaturePersistenceService);
        return DeviceFeatureDtoAssembler.toDtoList(deviceFeatureList);
    }
}
