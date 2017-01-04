package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.dto.device.DeviceAndFeatureRelationDto;
import com.anicloud.sunny.domain.model.device.DeviceAndFeatureRelation;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoyu on 15-7-8.
 */
public interface DeviceAndFeatureRelationService {
    public DeviceAndFeatureRelationDto save(DeviceAndFeatureRelationDto deviceAndFeatureRelationDto);
    public void batchSave(List<DeviceAndFeatureRelationDto> relationDtoList);
    public void batchModify(List<DeviceAndFeatureRelationDto> relationDtoList);
    public DeviceAndFeatureRelationDto findByDeviceIdentificationCode(String identificationCode);
    public List<DeviceAndFeatureRelationDto> findAll();
    List<DeviceAndFeatureRelationDto> findAll(Long hashUserId);
}
