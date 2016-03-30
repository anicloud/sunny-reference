package com.anicloud.sunny.kamili.domain.Adapter;

import com.anicloud.sunny.kamili.domain.model.device.DeviceFeature;
import com.anicloud.sunny.kamili.domain.model.device.FeatureArg;
import com.anicloud.sunny.kamili.domain.model.device.StubIdentity;
import com.anicloud.sunny.kamili.infrastructure.persistence.domain.DeviceFeatureDao;
import com.anicloud.sunny.kamili.infrastructure.persistence.domain.FeatureArgDao;
import com.anicloud.sunny.kamili.infrastructure.persistence.domain.StubIdentityDao;

import java.util.*;

/**
 * Created by MRK on 2016/3/23.
 */
public class DeviceFeatureDaoAdapter {

    public static DeviceFeature toDoamin(DeviceFeatureDao deviceFeatureDao) {
        if (deviceFeatureDao == null) {
            return null;
        }
        return new DeviceFeature(deviceFeatureDao.id, deviceFeatureDao.name,
                deviceFeatureDao.desc, toDomainSet(deviceFeatureDao.inputArgs),
                toDomainList(deviceFeatureDao.stubIdentityList),
                toDomain(deviceFeatureDao.inputArgMapping)
        );
    }

    public static DeviceFeatureDao toDao(DeviceFeature deviceFeature) {
        if (deviceFeature == null) {
            return null;
        }
        return new DeviceFeatureDao(deviceFeature.id, deviceFeature.name,
                deviceFeature.desc, toDaoSet(deviceFeature.inputArgs),
                toDaoList(deviceFeature.stubIdentityList),
                toDao(deviceFeature.inputArgMapping)
        );
    }

    public static List<DeviceFeature> toDomainByList(List<DeviceFeatureDao> deviceFeatureDaoList) {
        if (deviceFeatureDaoList == null || deviceFeatureDaoList.size() <= 0) {
            return null;
        }
        List<DeviceFeature> deviceFeatureList = new ArrayList<>();
        for (DeviceFeatureDao deviceFeatureDao : deviceFeatureDaoList) {
            deviceFeatureList.add(toDoamin(deviceFeatureDao));
        }
        return deviceFeatureList;
    }

    public static List<DeviceFeatureDao> toDaoByList(List<DeviceFeature> deviceFeatureList) {
        if (deviceFeatureList == null || deviceFeatureList.size() <= 0) {
            return null;
        }
        List<DeviceFeatureDao> deviceFeatureDaoList = new ArrayList<>();
        for (DeviceFeature deviceFeature : deviceFeatureList) {
            deviceFeatureDaoList.add(toDao(deviceFeature));
        }
        return deviceFeatureDaoList;
    }

    public static FeatureArg toDomain(FeatureArgDao featureArgDao) {
        if (featureArgDao == null) {
            return null;
        }
        return new FeatureArg(featureArgDao.id, featureArgDao.name,
                featureArgDao.dataType, featureArgDao.screenName
        );
    }

    public static FeatureArgDao toDao(FeatureArg featureArg) {
        if (featureArg == null) {
            return null;
        }
        return new FeatureArgDao(featureArg.id, featureArg.name,
                featureArg.dataType, featureArg.screenName
        );
    }

    public static Set<FeatureArg> toDomainSet(Set<FeatureArgDao> featureArgDaos) {
        if (featureArgDaos == null || featureArgDaos.size() <= 0) {
            return null;
        }
        Set<FeatureArg> featureArgSet = new HashSet<>();
        for (FeatureArgDao featureArgDao : featureArgDaos) {
            featureArgSet.add(toDomain(featureArgDao));
        }
        return featureArgSet;
    }

    public static Set<FeatureArgDao> toDaoSet(Set<FeatureArg> featureArgs) {
        if (featureArgs == null || featureArgs.size() <= 0) {
            return null;
        }
        Set<FeatureArgDao> featureArgDaoSet = new HashSet<>();
        for (FeatureArg featureArg : featureArgs) {
            featureArgDaoSet.add(toDao(featureArg));
        }
        return featureArgDaoSet;
    }

    public static StubIdentity toDomain(StubIdentityDao stubIdentityDao) {
        if (stubIdentityDao == null) {
            return null;
        }
        return new StubIdentity(stubIdentityDao.groupId, stubIdentityDao.stubId);
    }

    public static StubIdentityDao toDao(StubIdentity stubIdentity) {
        if (stubIdentity == null) {
            return null;
        }
        return new StubIdentityDao(stubIdentity.groupId, stubIdentity.stubId);
    }

    public static List<StubIdentity> toDomainList(List<StubIdentityDao> stubIdentityDaoList) {
        if (stubIdentityDaoList == null || stubIdentityDaoList.size() <= 0) {
            return null;
        }
        List<StubIdentity> stubIdentitys = new ArrayList<>();
        for (StubIdentityDao stubIdentityDao : stubIdentityDaoList) {
            stubIdentitys.add(toDomain(stubIdentityDao));
        }
        return stubIdentitys;
    }

    public static List<StubIdentityDao> toDaoList(List<StubIdentity> stubIdentityList) {
        if (stubIdentityList == null || stubIdentityList.size() <= 0) {
            return null;
        }
        List<StubIdentityDao> stubIdentitys = new ArrayList<>();
        for (StubIdentity stubIdentity : stubIdentityList) {
            stubIdentitys.add(toDao(stubIdentity));
        }
        return stubIdentitys;
    }

    public static Map<String, Map<StubIdentity, String>> toDomain(Map<String, Map<StubIdentityDao, String>> inputArgDaoMapping) {
        if (inputArgDaoMapping == null) {
            return null;
        }
        Map<String, Map<StubIdentity, String>> inputArgMapping = new HashMap<>();
        Iterator<Map.Entry<String, Map<StubIdentityDao, String>>> iterator = inputArgDaoMapping.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<StubIdentityDao, String>> entry = iterator.next();
            inputArgMapping.put(entry.getKey(), StubIdentityMapToDomain(entry.getValue()));
        }
        return inputArgMapping;
    }

    public static Map<String, Map<StubIdentityDao, String>> toDao(Map<String, Map<StubIdentity, String>> inputArgMapping) {
        if (inputArgMapping == null) {
            return null;
        }
        Map<String, Map<StubIdentityDao, String>> inputArgDtoMapping = new HashMap<>();
        Iterator<Map.Entry<String, Map<StubIdentity, String>>> iterator = inputArgMapping.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<StubIdentity, String>> entry = iterator.next();
            inputArgDtoMapping.put(entry.getKey(), StubIdentityDaoMapToDao(entry.getValue()));
        }
        return inputArgDtoMapping;
    }

    public static Map<StubIdentity, String> StubIdentityMapToDomain(Map<StubIdentityDao, String> stubIdentityDtoMap) {
        if (stubIdentityDtoMap == null) {
            return null;
        }
        Map<StubIdentity, String> stubIdentityMap = new HashMap<>();
        Iterator<Map.Entry<StubIdentityDao, String>> iterator = stubIdentityDtoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<StubIdentityDao, String> entry = iterator.next();
            stubIdentityMap.put(toDomain(entry.getKey()), entry.getValue());
        }
        return stubIdentityMap;
    }

    public static Map<StubIdentityDao, String> StubIdentityDaoMapToDao(Map<StubIdentity, String> stubIdentityMap) {
        if (stubIdentityMap == null) {
            return null;
        }
        Map<StubIdentityDao, String> stubIdentityDaoMap = new HashMap<>();
        Iterator<Map.Entry<StubIdentity, String>> iterator = stubIdentityMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<StubIdentity, String> entry = iterator.next();
            stubIdentityDaoMap.put(toDao(entry.getKey()), entry.getValue());
        }
        return stubIdentityDaoMap;
    }
}
