package com.anicloud.sunny.domain.model.device;

import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureFunctionDao;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceFeaturePersistenceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeature extends AbstractDomain {
    private static final long serialVersionUID = -1919619805181874248L;

    public String featureNum;
    public String featureName;
    public String description;

    public Set<FeatureFunction> featureFunctionSet;

    public DeviceFeature() {
    }

    public DeviceFeature(String description, Set<FeatureFunction> featureFunctionSet,
                         String featureName, String featureNum) {
        this.description = description;
        this.featureFunctionSet = featureFunctionSet;
        this.featureName = featureName;
        this.featureNum = featureNum;
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
        DeviceFeatureDao deviceFeatureDao = featurePersistenceService.getDeviceFeatureByNum(deviceFeatureNum);
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
        DeviceFeature deviceFeature = new DeviceFeature(
                featureDao.description,
                FeatureFunction.toFeatureFunctionSet(featureDao.featureFunctionDaoSet),
                featureDao.featureName,
                featureDao.featureNum
        );
        return deviceFeature;
    }

    public static DeviceFeatureDao toDao(DeviceFeature deviceFeature) {
        if (deviceFeature == null) {
            return null;
        }
        DeviceFeatureDao featureDao = new DeviceFeatureDao(
                deviceFeature.description,
                FeatureFunction.toDaoSet(deviceFeature.featureFunctionSet),
                deviceFeature.featureName,
                deviceFeature.featureNum
        );
        return featureDao;
    }

    public static List<DeviceFeatureDao> toDaoList(List<DeviceFeature> deviceFeatureList) {
        List<DeviceFeatureDao> deviceFeatureDaoList =
                new ArrayList<DeviceFeatureDao>(deviceFeatureList.size());
        for (DeviceFeature deviceFeature : deviceFeatureList) {
            deviceFeatureDaoList.add(toDao(deviceFeature));
        }
        return deviceFeatureDaoList;
    }

    public static List<DeviceFeature> toDeviceFeatureList(List<DeviceFeatureDao> deviceFeatureDaoList) {
        List<DeviceFeature> deviceFeatureList = new ArrayList<>();
        for (DeviceFeatureDao deviceFeatureDao : deviceFeatureDaoList) {
            deviceFeatureList.add(toDeviceFeature(deviceFeatureDao));
        }
        return deviceFeatureList;
    }

    @Override
    public String toString() {
        return "DeviceFeature{" +
                "description='" + description + '\'' +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}
