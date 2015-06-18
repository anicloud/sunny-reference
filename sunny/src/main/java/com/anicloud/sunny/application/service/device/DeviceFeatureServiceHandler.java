package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.assemble.DeviceFeatureDtoAssembler;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.utils.NumGenerate;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceFeaturePersistenceService;
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
public class DeviceFeatureServiceHandler implements DeviceFeatureService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceFeatureServiceHandler.class);

    @Resource
    private DeviceFeaturePersistenceService deviceFeaturePersistenceService;

    @Override
    public DeviceFeatureDto saveDeviceFeature(DeviceFeatureDto deviceFeatureDto) {
        deviceFeatureDto.featureNum = NumGenerate.generate();
        DeviceFeature deviceFeature = DeviceFeature.save(deviceFeaturePersistenceService,
                DeviceFeatureDtoAssembler.toDeviceFeature(deviceFeatureDto));
        return DeviceFeatureDtoAssembler.toDto(deviceFeature);
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
    public void batchInsertDeviceFeature(List<DeviceFeatureDto> featureDtoList) {
        List<DeviceFeature> deviceFeatureList = DeviceFeatureDtoAssembler
                .toDeviceFeatureList(featureDtoList);
        DeviceFeature.batchInsert(deviceFeaturePersistenceService, deviceFeatureList);
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
