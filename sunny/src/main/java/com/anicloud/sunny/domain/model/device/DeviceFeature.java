package com.anicloud.sunny.domain.model.device;

import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureArgFunctionArgRelationDao;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceFeaturePersistenceService;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.*;

/**
 * Created by zhaoyu on 15-6-12.
 */
@Configurable
public class DeviceFeature extends AbstractDomain {
    private static final long serialVersionUID = -1919619805181874248L;

    public Integer featureId;
    public String featureName;
    public String description;

    public List<FeatureArg> featureArgList;
    public List<StubIdentity> stubIdentityList;
    public Map<String, Map<StubIdentity, String>> inputArgMapping;

    public DeviceFeature() {
    }

    public DeviceFeature(Integer featureId, String featureName, String description,
                         List<FeatureArg> featureArgList,
                         List<StubIdentity> stubIdentityList,
                         Map<String, Map<StubIdentity, String>> inputArgMapping) {
        this.featureId = featureId;
        this.featureName = featureName;
        this.description = description;
        this.featureArgList = featureArgList;
        this.stubIdentityList = stubIdentityList;
        this.inputArgMapping = inputArgMapping;
    }

    public static DeviceFeature save(DeviceFeaturePersistenceService featurePersistenceService, DeviceFeature deviceFeature) {
        DeviceFeatureDao featureDao = featurePersistenceService.save(toDao(deviceFeature));
        return toDeviceFeature(featureDao);
    }

    public static DeviceFeature modify(DeviceFeaturePersistenceService featurePersistenceService, DeviceFeature deviceFeature) {
        DeviceFeatureDao featureDao = featurePersistenceService.modify(toDao(deviceFeature));
        return toDeviceFeature(featureDao);
    }

    public static void remove(DeviceFeaturePersistenceService featurePersistenceService, String deviceFeatureNum) {
        featurePersistenceService.remove(deviceFeatureNum);
    }

    public static void batchInsert(DeviceFeaturePersistenceService featurePersistenceService, List<DeviceFeature> deviceFeatureList) {
        featurePersistenceService.batchInsert(toDaoList(deviceFeatureList));
    }

    public static DeviceFeature getDeviceFeatureByNum(DeviceFeaturePersistenceService featurePersistenceService, String deviceFeatureNum) {
        DeviceFeatureDao deviceFeatureDao = featurePersistenceService.getDeviceFeatureById(deviceFeatureNum);
        if (deviceFeatureDao == null) {
            return null;
        }
        return toDeviceFeature(deviceFeatureDao);
    }

    public static List<DeviceFeature> getAll(DeviceFeaturePersistenceService featurePersistenceService) {
        List<DeviceFeatureDao> featureDaoList = featurePersistenceService.getAllDeviceFeature();
        if (featureDaoList == null) {
            return null;
        }
        return toDeviceFeatureList(featureDaoList);
    }

    public static DeviceFeature toDeviceFeature(DeviceFeatureDao featureDao) {
        if (featureDao == null) {
            return null;
        }
        // TODO
        return null;
    }

    public static DeviceFeatureDao toDao(DeviceFeature deviceFeature) {
        if (deviceFeature == null) {
            return null;
        }
        // TODO
        return null;
    }

    public static List<DeviceFeatureDao> toDaoList(List<DeviceFeature> deviceFeatureList) {
        if (deviceFeatureList == null) return null;
        List<DeviceFeatureDao> deviceFeatureDaoList =
                new ArrayList<DeviceFeatureDao>(deviceFeatureList.size());
        for (DeviceFeature deviceFeature : deviceFeatureList) {
            deviceFeatureDaoList.add(toDao(deviceFeature));
        }
        return deviceFeatureDaoList;
    }

    public static List<DeviceFeature> toDeviceFeatureList(List<DeviceFeatureDao> deviceFeatureDaoList) {
        if (deviceFeatureDaoList == null) return null;
        List<DeviceFeature> deviceFeatureList = new ArrayList<>();
        for (DeviceFeatureDao deviceFeatureDao : deviceFeatureDaoList) {
            deviceFeatureList.add(toDeviceFeature(deviceFeatureDao));
        }
        return deviceFeatureList;
    }

    public static List<Map<String, List<String>>> toListMap(List<FeatureArgFunctionArgRelationDao> relationDaoList) {
        if (relationDaoList == null) return null;

        List<Map<String, List<String>>> mapList = new ArrayList<>();
        for (FeatureArgFunctionArgRelationDao dao : relationDaoList) {
            Map<String, List<String>> listMap = new HashMap<>();
            List<String> featureIdArgNameList = new ArrayList<>();
            for (FeatureArgFunctionArgRelationDao relationDao : relationDaoList) {
                if (relationDao.featureArgName.equals(dao.featureArgName)) {
                    String featureIdArgName = relationDao.featureFunctionId + ":" + relationDao.functionArgName;
                    featureIdArgNameList.add(featureIdArgName);
                    listMap.put(dao.featureArgName, featureIdArgNameList);
                }
            }
            mapList.add(listMap);
        }
        return mapList;
    }

    public static List<FeatureArgFunctionArgRelationDao> toRelationDaoList(List<Map<String, List<String>>> relationMap) {
        if (relationMap == null) return null;

        List<FeatureArgFunctionArgRelationDao> daoList = new ArrayList<>();
        for (Map<String, List<String>> map : relationMap) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                List<String> values = map.get(key);
                for (String val : values) {
                    String []args = val.split(":");
                    FeatureArgFunctionArgRelationDao dao = new FeatureArgFunctionArgRelationDao(key, args[0], args[1]);
                    daoList.add(dao);
                }
            }
        }
        return daoList;
    }

    @Override
    public String toString() {
        return "DeviceFeature{" +
                "description='" + description + '\'' +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}
