package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.assemble.DeviceAndFeatureRelationDtoAssembler;
import com.anicloud.sunny.application.builder.DeviceAndFeatureRelationDtoBuilder;
import com.anicloud.sunny.application.dto.device.DeviceAndFeatureRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.domain.model.device.DeviceAndFeatureRelation;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceAndFeatureRelationPersistenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoyu on 15-7-8.
 */
@Service
@Transactional
public class DeviceAndFeatureRelationEventHandler implements DeviceAndFeatureRelationService {
    @Resource
    private DeviceAndFeatureRelationPersistenceService deviceAndFeatureRelationPersistenceService;

    @Override
    public DeviceAndFeatureRelationDto save(DeviceAndFeatureRelationDto deviceAndFeatureRelationDto) {
        DeviceAndFeatureRelation relation = DeviceAndFeatureRelationDtoAssembler
                .toRelation(deviceAndFeatureRelationDto);
        DeviceAndFeatureRelation returnRelation = DeviceAndFeatureRelation.save(
                deviceAndFeatureRelationPersistenceService, relation);
        return DeviceAndFeatureRelationDtoAssembler.toDto(returnRelation);
    }

    @Override
    public void batchSave(List<DeviceAndFeatureRelationDto> relationDtoList) {
        for (DeviceAndFeatureRelationDto relationDto : relationDtoList) {
            save(relationDto);
        }
    }

    @Override
    public DeviceAndFeatureRelationDto findByDeviceIdentificationCode(String identificationCode) {
        DeviceAndFeatureRelation relation = DeviceAndFeatureRelation.findByDeviceIdentificationCode(
                deviceAndFeatureRelationPersistenceService, identificationCode);
        return DeviceAndFeatureRelationDtoAssembler.toDto(relation);
    }

    @Override
    public List<DeviceAndFeatureRelationDto> findAll() {
        List<DeviceAndFeatureRelation> relationList = DeviceAndFeatureRelation
                .findAll(deviceAndFeatureRelationPersistenceService);
        return DeviceAndFeatureRelationDtoAssembler.toDtoList(relationList);
    }
}
