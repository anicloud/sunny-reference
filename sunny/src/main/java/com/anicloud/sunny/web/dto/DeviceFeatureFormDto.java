package com.anicloud.sunny.web.dto;

import com.ani.octopus.commons.stub.enumeration.PrivilegeType;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.domain.model.device.FeatureArg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/11.
 */
public class DeviceFeatureFormDto {

    public String featureId;
    public String featureName;
    public String description;
    public PrivilegeType privilegeType;

    public List<FeatureArg> argDtoList;

    public static DeviceFeatureFormDto convertToDeviceFeatureForm(DeviceFeature deviceFeature){
        DeviceFeatureFormDto deviceFeatureFormDto = new DeviceFeatureFormDto();
        if(deviceFeature != null) {
            deviceFeatureFormDto.featureId = deviceFeature.featureId;
            deviceFeatureFormDto.featureName = deviceFeature.featureName;
            deviceFeatureFormDto.description = deviceFeature.description;
            deviceFeatureFormDto.argDtoList = deviceFeature.featureArgList;
            deviceFeatureFormDto.privilegeType = deviceFeature.privilegeType;
        }
        return deviceFeatureFormDto;
    }

    public static List<DeviceFeatureFormDto> convertToDeviceFeatureForms(List<DeviceFeature> deviceFeatures){
        List<DeviceFeatureFormDto>  deviceFeatureFormDtos = new ArrayList<>();
        if(deviceFeatures != null) {
            for (DeviceFeature deviceFeature : deviceFeatures) {
                deviceFeatureFormDtos.add(convertToDeviceFeatureForm(deviceFeature));
            }
        }
        return deviceFeatureFormDtos;
    }

    public static DeviceFeature convertToDeviceFeatureDto(DeviceFeatureFormDto deviceFeatureFormDto){
        DeviceFeature deviceFeature = new DeviceFeature();
        if(deviceFeatureFormDto != null) {
            deviceFeature.featureId = deviceFeatureFormDto.featureId;
            deviceFeature.featureName = deviceFeatureFormDto.featureName;
            deviceFeature.description = deviceFeatureFormDto.description;
            deviceFeature.featureArgList = deviceFeatureFormDto.argDtoList;
            deviceFeature.privilegeType = deviceFeatureFormDto.privilegeType;
        }
        return deviceFeature;
    }

    public static List<DeviceFeature> convertToDeviceFeatureDtos(List<DeviceFeatureFormDto> deviceFeatureFormDtos){
        List<DeviceFeature>  deviceFeatures = new ArrayList<>();
        if(deviceFeatureFormDtos != null) {
            for (DeviceFeatureFormDto deviceFeatureFormDto : deviceFeatureFormDtos) {
                deviceFeatures.add(convertToDeviceFeatureDto(deviceFeatureFormDto));
            }
        }
        return deviceFeatures;
    }


}
