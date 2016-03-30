package com.anicloud.sunny.kamili.interfaces.facade.adapter;

import com.anicloud.sunny.kamili.domain.model.device.DeviceFeature;
import com.anicloud.sunny.kamili.domain.model.device.FeatureArg;
import com.anicloud.sunny.kamili.domain.model.device.StubIdentity;
import com.anicloud.sunny.kamili.interfaces.facade.dto.DeviceFeatureDto;
import com.anicloud.sunny.kamili.interfaces.facade.dto.FeatureArgDto;
import com.anicloud.sunny.kamili.interfaces.facade.dto.StubIdentityDto;

import java.util.*;

/**
 * Created by MRK on 2016/3/18.
 */
public class DeviceFeatureDtoAdapter {
    public DeviceFeatureDtoAdapter() {
    }

    public static DeviceFeature toDomain(DeviceFeatureDto deviceFeatureDto) {
        if (deviceFeatureDto == null) {
            return null;
        }
        return new DeviceFeature(deviceFeatureDto.id, deviceFeatureDto.name,
                deviceFeatureDto.desc, toDomain(deviceFeatureDto.inputArgs),
                toDomain(deviceFeatureDto.stubIdentityList),
                toDomain(deviceFeatureDto.inputArgMapping)
        );
    }

    public static DeviceFeatureDto toDto(DeviceFeature deviceFeature) {
        if (deviceFeature == null) {
            return null;
        }
        return new DeviceFeatureDto(deviceFeature.id, deviceFeature.name,
                deviceFeature.desc, toDto(deviceFeature.inputArgs),
                toDto(deviceFeature.stubIdentityList),
                toDto(deviceFeature.inputArgMapping)
        );
    }

    public static List<DeviceFeature> listToDomain(List<DeviceFeatureDto> deviceFeatureDtoList) {
        if (deviceFeatureDtoList == null) {
            return null;
        }
        List<DeviceFeature> deviceFeatureList = new ArrayList<>();
        for (DeviceFeatureDto deviceFeatureDto : deviceFeatureDtoList) {
            deviceFeatureList.add(toDomain(deviceFeatureDto));
        }
        return deviceFeatureList;
    }

    public static List<DeviceFeatureDto> listToDto(List<DeviceFeature> deviceFeatureList) {
        if (deviceFeatureList == null) {
            return null;
        }
        List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>();
        for (DeviceFeature deviceFeature : deviceFeatureList) {
            deviceFeatureDtoList.add(toDto(deviceFeature));
        }
        return deviceFeatureDtoList;
    }

    public static Set<FeatureArg> toDomain(Set<FeatureArgDto> featureArgDtos) {
        if (featureArgDtos == null) {
            return null;
        }
        Set<FeatureArg> featureArgSet = new HashSet<>();
        for (FeatureArgDto featureArgDto : featureArgDtos) {
            FeatureArg featureArg = new FeatureArg(featureArgDto.id, featureArgDto.name,
                    featureArgDto.dataType, featureArgDto.screenName
            );
            featureArgSet.add(featureArg);
        }
        return featureArgSet;
    }

    public static Set<FeatureArgDto> toDto(Set<FeatureArg> featureArgs) {
        if (featureArgs == null) {
            return null;
        }
        Set<FeatureArgDto> featureArgDtoSet = new HashSet<>();
        for (FeatureArg featureArg : featureArgs) {
            FeatureArgDto featureArgDto = new FeatureArgDto(featureArg.id, featureArg.name,
                    featureArg.dataType, featureArg.screenName
            );
            featureArgDtoSet.add(featureArgDto);
        }
        return featureArgDtoSet;
    }

    public static StubIdentity toDomain(StubIdentityDto stubIdentityDto) {
        if (stubIdentityDto == null) {
            return null;
        }
        return new StubIdentity(stubIdentityDto.groupId, stubIdentityDto.stubId);
    }

    public static StubIdentityDto toDto(StubIdentity stubIdentity) {
        if (stubIdentity == null) {
            return null;
        }
        return new StubIdentityDto(stubIdentity.groupId, stubIdentity.stubId);
    }

    public static List<StubIdentity> toDomain(List<StubIdentityDto> stubIdentityDtoList) {
        if (stubIdentityDtoList == null) {
            return null;
        }
        List<StubIdentity> stubIdentityList = new ArrayList<>();
        for (StubIdentityDto stubIdentityDto : stubIdentityDtoList) {
            stubIdentityList.add(toDomain(stubIdentityDto));
        }
        return stubIdentityList;
    }

    public static List<StubIdentityDto> toDto(List<StubIdentity> stubIdentityList) {
        if (stubIdentityList == null) {
            return null;
        }
        List<StubIdentityDto> stubIdentityDtoList = new ArrayList<>();
        for (StubIdentity stubIdentity : stubIdentityList) {
            stubIdentityDtoList.add(toDto(stubIdentity));
        }
        return stubIdentityDtoList;
    }

    public static Map<String, Map<StubIdentity, String>> toDomain(Map<String, Map<StubIdentityDto, String>> inputArgDtoMapping) {
        if (inputArgDtoMapping == null) {
            return null;
        }
        Map<String, Map<StubIdentity, String>> inputArgMapping = new HashMap<>();
        Iterator<Map.Entry<String, Map<StubIdentityDto, String>>> iterator = inputArgDtoMapping.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<StubIdentityDto, String>> entry = iterator.next();
            inputArgMapping.put(entry.getKey(), StubIdentityMapToDomain(entry.getValue()));
        }
        return inputArgMapping;
    }

    public static Map<String, Map<StubIdentityDto, String>> toDto(Map<String, Map<StubIdentity, String>> inputArgMapping) {
        if (inputArgMapping == null) {
            return null;
        }
        Map<String, Map<StubIdentityDto, String>> inputArgDtoMapping = new HashMap<>();
        Iterator<Map.Entry<String, Map<StubIdentity, String>>> iterator = inputArgMapping.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<StubIdentity, String>> entry = iterator.next();
            inputArgDtoMapping.put(entry.getKey(), StubIdentityDtoMapToDto(entry.getValue()));
        }
        return inputArgDtoMapping;
    }

    public static Map<StubIdentity, String> StubIdentityMapToDomain(Map<StubIdentityDto, String> stubIdentityDtoMap) {
        if (stubIdentityDtoMap == null) {
            return null;
        }
        Map<StubIdentity, String> stubIdentityMap = new HashMap<>();
        Iterator<Map.Entry<StubIdentityDto, String>> iterator = stubIdentityDtoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<StubIdentityDto, String> entry = iterator.next();
            stubIdentityMap.put(toDomain(entry.getKey()), entry.getValue());
        }
        return stubIdentityMap;
    }

    public static Map<StubIdentityDto, String> StubIdentityDtoMapToDto(Map<StubIdentity, String> stubIdentityMap) {
        if (stubIdentityMap == null) {
            return null;
        }
        Map<StubIdentityDto, String> stubIdentityDtoMap = new HashMap<>();
        Iterator<Map.Entry<StubIdentity, String>> iterator = stubIdentityMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<StubIdentity, String> entry = iterator.next();
            stubIdentityDtoMap.put(toDto(entry.getKey()), entry.getValue());
        }
        return stubIdentityDtoMap;
    }
}
