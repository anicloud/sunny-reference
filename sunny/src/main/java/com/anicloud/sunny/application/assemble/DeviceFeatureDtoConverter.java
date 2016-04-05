package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureInfoDto;
import com.anicloud.sunny.application.dto.device.FeatureArgDto;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.domain.model.device.FeatureArg;
import com.anicloud.sunny.domain.model.device.StubIdentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-15.
 */
public class DeviceFeatureDtoConverter {

    private DeviceFeatureDtoConverter() {
    }

    public static DeviceFeature toDeviceFeature(DeviceFeatureDto deviceFeatureDto) {
        if (deviceFeatureDto == null) {
            return null;
        }
        return null;
        //new DeviceFeature(deviceFeatureDto.featureId, deviceFeatureDto.featureName, deviceFeatureDto.description, toDomain(deviceFeatureDto.argDtoList),);
    }

    public static DeviceFeatureDto toDto(DeviceFeature deviceFeature) {
        if (deviceFeature == null) {
            return null;
        }

        // TODO
        return null;
    }

    public static FeatureArg toDomain(FeatureArgDto featureArgDto) {
        if (featureArgDto == null) {
            return null;
        }
        return new FeatureArg(featureArgDto.name, featureArgDto.dataType, featureArgDto.screenName);
    }

    public static FeatureArgDto toDto(FeatureArg featureArg) {
        if (featureArg == null) {
            return null;
        }
        return new FeatureArgDto(featureArg.dataType, featureArg.name, featureArg.screenName);
    }

    public static List<FeatureArg> toDomain(List<FeatureArgDto> featureArgDtos) {
        if (featureArgDtos == null) {
            return null;
        }
        List<FeatureArg> featureArgList = new ArrayList<>();
        for (FeatureArgDto featureArgDto : featureArgDtos) {
            featureArgList.add(toDomain(featureArgDto));
        }
        return featureArgList;
    }

    public static List<FeatureArgDto> toDto(List<FeatureArg> featureArgs) {
        if (featureArgs == null) {
            return null;
        }
        List<FeatureArgDto> featureArgDtos = new ArrayList<>();
        for (FeatureArg featureArg : featureArgs) {
            featureArgDtos.add(toDto(featureArg));
        }
        return featureArgDtos;
    }

    public static StubIdentity toDomain(){
        return null;
    }

    public static List<DeviceFeature> toDeviceFeatureList(List<DeviceFeatureDto> deviceFeatureDtoList) {
        if (deviceFeatureDtoList == null) {
            return null;
        }
        List<DeviceFeature> deviceFeatureList = new ArrayList<>(deviceFeatureDtoList.size());
        for (DeviceFeatureDto deviceFeatureDto : deviceFeatureDtoList) {
            deviceFeatureList.add(toDeviceFeature(deviceFeatureDto));
        }
        return deviceFeatureList;
    }

    public static List<DeviceFeatureDto> toDtoList(List<DeviceFeature> deviceFeatureList) {
        if (deviceFeatureList == null) {
            return null;
        }
        List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>(deviceFeatureList.size());
        for (DeviceFeature deviceFeature : deviceFeatureList) {
            deviceFeatureDtoList.add(toDto(deviceFeature));
        }
        return deviceFeatureDtoList;
    }

    public static DeviceFeatureInfoDto toDeviceFeatureInfoDto(DeviceFeature deviceFeature) {
        if (deviceFeature == null) return null;
        // TODO
        return null;
    }

    public static List<DeviceFeatureInfoDto> toDeviceFeatureInfoDtoList(List<DeviceFeature> deviceFeatureList) {
        if (deviceFeatureList == null) return null;
        List<DeviceFeatureInfoDto> infoDtoList = new ArrayList<>();
        for (DeviceFeature deviceFeature : deviceFeatureList) {
            infoDtoList.add(toDeviceFeatureInfoDto(deviceFeature));
        }
        return infoDtoList;
    }
}
