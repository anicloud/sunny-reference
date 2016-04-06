package com.anicloud.sunny.interfaces.web.dto;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureArgDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/11.
 */
public class DeviceFeatureFormDto {

    public Integer featureId;
    public String featureName;
    public String description;

    public List<FeatureArgDto> argDtoList;

    public static DeviceFeatureFormDto convertToDeviceFeatureForm(DeviceFeatureDto deviceFeatureDto){
        DeviceFeatureFormDto deviceFeatureFormDto = new DeviceFeatureFormDto();
        if(deviceFeatureDto != null) {
            deviceFeatureFormDto.featureId = deviceFeatureDto.featureId;
            deviceFeatureFormDto.featureName = deviceFeatureDto.featureName;
            deviceFeatureFormDto.description = deviceFeatureDto.description;
            deviceFeatureFormDto.argDtoList = deviceFeatureDto.argDtoList;
        }
        return deviceFeatureFormDto;
    }

    public static List<DeviceFeatureFormDto> convertToDeviceFeatureForms(List<DeviceFeatureDto> deviceFeatureDtos){
        List<DeviceFeatureFormDto>  deviceFeatureFormDtos = new ArrayList<>();
        if(deviceFeatureDtos != null) {
            for (DeviceFeatureDto deviceFeatureDto : deviceFeatureDtos) {
                deviceFeatureFormDtos.add(convertToDeviceFeatureForm(deviceFeatureDto));
            }
        }
        return deviceFeatureFormDtos;
    }

    public static DeviceFeatureDto convertToDeviceFeatureDto(DeviceFeatureFormDto deviceFeatureFormDto){
        DeviceFeatureDto deviceFeatureDto = new DeviceFeatureDto();
        if(deviceFeatureFormDto != null) {
            deviceFeatureDto.featureId = deviceFeatureFormDto.featureId;
            deviceFeatureDto.featureName = deviceFeatureFormDto.featureName;
            deviceFeatureDto.description = deviceFeatureFormDto.description;
            deviceFeatureDto.argDtoList = deviceFeatureFormDto.argDtoList;
        }
        return deviceFeatureDto;
    }

    public static List<DeviceFeatureDto> convertToDeviceFeatureDtos(List<DeviceFeatureFormDto> deviceFeatureFormDtos){
        List<DeviceFeatureDto>  deviceFeatureDtos = new ArrayList<>();
        if(deviceFeatureFormDtos != null) {
           for (DeviceFeatureFormDto deviceFeatureFormDto : deviceFeatureFormDtos) {
               deviceFeatureDtos.add(convertToDeviceFeatureDto(deviceFeatureFormDto));
           }
        }
        return deviceFeatureDtos;
    }


}
