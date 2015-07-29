package com.anicloud.sunny.interfaces.web.dto;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.share.FeatureArgValueDto;
import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.application.dto.strategy.FeatureTriggerDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/11.
 */
public class DeviceFeatureInstanceFormDto {
    public String featureId;
    public String featureName;
    public DeviceFormDto device;
    public DeviceFeatureFormDto deviceFeature;
    public List<FeatureArgValueDto> featureArgValueDtoList;
    public List<FeatureTriggerDto> triggerDtoList;
    public boolean isScheduleNow;

    public static DeviceFeatureInstanceFormDto convertToDeviceFeatureInstanceForm(DeviceFeatureInstanceDto deviceFeatureInstanceDto){
        DeviceFeatureInstanceFormDto deviceFeatureInstanceFormDto = new DeviceFeatureInstanceFormDto();

        deviceFeatureInstanceFormDto.featureId = deviceFeatureInstanceDto.deviceFeatureDto.featureId;
        deviceFeatureInstanceFormDto.featureName = deviceFeatureInstanceDto.deviceFeatureDto.featureName;
        deviceFeatureInstanceFormDto.device = DeviceFormDto.convertToDeviceForm(deviceFeatureInstanceDto.deviceDto);
        deviceFeatureInstanceFormDto.deviceFeature = DeviceFeatureFormDto.convertToDeviceFeatureForm(deviceFeatureInstanceDto.deviceFeatureDto);

        deviceFeatureInstanceFormDto.featureArgValueDtoList = deviceFeatureInstanceDto.featureArgValueDtoList;
        deviceFeatureInstanceFormDto.triggerDtoList = deviceFeatureInstanceDto.triggerDtoList;
        deviceFeatureInstanceFormDto.isScheduleNow = deviceFeatureInstanceDto.isScheduleNow;
        return deviceFeatureInstanceFormDto;

    }

    public static List<DeviceFeatureInstanceFormDto> convertToDeviceFeatureInstanceForms(List<DeviceFeatureInstanceDto> deviceFeatureInstanceDtos){
        List<DeviceFeatureInstanceFormDto> deviceFeatureInstanceFormDtos = new ArrayList<>();
        for(DeviceFeatureInstanceDto deviceFeatureInstanceDto : deviceFeatureInstanceDtos){
            deviceFeatureInstanceFormDtos.add(convertToDeviceFeatureInstanceForm(deviceFeatureInstanceDto));
        }
        return deviceFeatureInstanceFormDtos;
    }

    public static DeviceFeatureInstanceDto convertToFeatureInstanceDto(DeviceFeatureInstanceFormDto deviceFeatureInstanceFormDto){
        DeviceFeatureInstanceDto deviceFeatureInstanceDto = new DeviceFeatureInstanceDto();
        deviceFeatureInstanceDto.deviceDto = DeviceFormDto.convertToDeviceDto(deviceFeatureInstanceFormDto.device);
        deviceFeatureInstanceDto.deviceFeatureDto = DeviceFeatureFormDto.convertToDeviceFeatureDto(deviceFeatureInstanceFormDto.deviceFeature);
        deviceFeatureInstanceDto.featureArgValueDtoList = deviceFeatureInstanceFormDto.featureArgValueDtoList;
        deviceFeatureInstanceDto.triggerDtoList = deviceFeatureInstanceFormDto.triggerDtoList;
        deviceFeatureInstanceDto.isScheduleNow = deviceFeatureInstanceFormDto.isScheduleNow;
        return deviceFeatureInstanceDto;
    }

    public static List<DeviceFeatureInstanceDto> convertToFeatureInstanceDtos(List<DeviceFeatureInstanceFormDto> deviceFeatureInstanceFormDtos){
        List<DeviceFeatureInstanceDto> deviceFeatureInstanceDtos = new ArrayList<>();
        for(DeviceFeatureInstanceFormDto deviceFeatureInstanceFormDto : deviceFeatureInstanceFormDtos){
            deviceFeatureInstanceDtos.add(DeviceFeatureInstanceFormDto.convertToFeatureInstanceDto(deviceFeatureInstanceFormDto));
        }
        return deviceFeatureInstanceDtos;
    }

}
