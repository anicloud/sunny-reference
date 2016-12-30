package com.anicloud.sunny.web.dto;

import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.domain.model.device.FeatureArgValue;
import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstance;
import com.anicloud.sunny.domain.model.strategy.FeatureTrigger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/11.
 */
public class DeviceFeatureInstanceFormDto {
    public String featureInstanceId;
    public DeviceFormDto device;
    public DeviceFeatureFormDto deviceFeature;
    public List<FeatureArgValue> featureArgValueDtoList;
    public List<FeatureTrigger> triggerDtoList;
    public boolean isScheduleNow;
    public Long intervalTime;

    public static DeviceFeatureInstanceFormDto convertToDeviceFeatureInstanceForm(DeviceFeatureInstance deviceFeatureInstance){
        DeviceFeatureInstanceFormDto deviceFeatureInstanceFormDto = new DeviceFeatureInstanceFormDto();
        if(deviceFeatureInstance != null) {
            deviceFeatureInstanceFormDto.featureInstanceId = deviceFeatureInstance.featureInstanceId;
            deviceFeatureInstanceFormDto.device = DeviceFormDto.convertToDeviceForm(deviceFeatureInstance.device);
            deviceFeatureInstanceFormDto.deviceFeature = DeviceFeatureFormDto.convertToDeviceFeatureForm(deviceFeatureInstance.deviceFeature);

            deviceFeatureInstanceFormDto.featureArgValueDtoList = deviceFeatureInstance.featureArgValueList;
            deviceFeatureInstanceFormDto.triggerDtoList = deviceFeatureInstance.triggerList;
            deviceFeatureInstanceFormDto.isScheduleNow = deviceFeatureInstance.isScheduleNow;
            deviceFeatureInstanceFormDto.intervalTime = deviceFeatureInstance.intervalTime;
        }
        return deviceFeatureInstanceFormDto;
    }

    public static List<DeviceFeatureInstanceFormDto> convertToDeviceFeatureInstanceForms(List<DeviceFeatureInstance> deviceFeatureInstances){
        List<DeviceFeatureInstanceFormDto> deviceFeatureInstanceFormDtos = new ArrayList<>();
        if(deviceFeatureInstances != null) {
            for (DeviceFeatureInstance deviceFeatureInstance : deviceFeatureInstances) {
                deviceFeatureInstanceFormDtos.add(convertToDeviceFeatureInstanceForm(deviceFeatureInstance));
            }
        }
        return deviceFeatureInstanceFormDtos;
    }

    public static DeviceFeatureInstance convertToFeatureInstanceDto(DeviceFeatureInstanceFormDto deviceFeatureInstanceFormDto){
        DeviceFeatureInstance deviceFeatureInstance = new DeviceFeatureInstance();
        if(deviceFeatureInstanceFormDto != null) {
            deviceFeatureInstance.featureInstanceId = deviceFeatureInstanceFormDto.featureInstanceId;
            deviceFeatureInstance.device = DeviceFormDto.convertToDeviceDto(deviceFeatureInstanceFormDto.device);
            deviceFeatureInstance.deviceFeature = DeviceFeatureFormDto.convertToDeviceFeatureDto(deviceFeatureInstanceFormDto.deviceFeature);
            deviceFeatureInstance.featureArgValueList = deviceFeatureInstanceFormDto.featureArgValueDtoList;
            deviceFeatureInstance.triggerList = deviceFeatureInstanceFormDto.triggerDtoList;
            deviceFeatureInstance.isScheduleNow = deviceFeatureInstanceFormDto.isScheduleNow;
            deviceFeatureInstance.intervalTime = deviceFeatureInstanceFormDto.intervalTime;
        }
        return deviceFeatureInstance;
    }

    public static List<DeviceFeatureInstance> convertToFeatureInstanceDtos(List<DeviceFeatureInstanceFormDto> deviceFeatureInstanceFormDtos){
        List<DeviceFeatureInstance> deviceFeatureInstances = new ArrayList<>();
        if(deviceFeatureInstanceFormDtos != null) {
            for (DeviceFeatureInstanceFormDto deviceFeatureInstanceFormDto : deviceFeatureInstanceFormDtos) {
                deviceFeatureInstances.add(DeviceFeatureInstanceFormDto.convertToFeatureInstanceDto(deviceFeatureInstanceFormDto));
            }
        }
        return deviceFeatureInstances;
    }

}
