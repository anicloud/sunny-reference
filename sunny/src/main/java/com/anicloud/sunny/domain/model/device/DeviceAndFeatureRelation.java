package com.anicloud.sunny.domain.model.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndFeatureRelationDao;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceAndFeatureRelationPersistenceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
public class DeviceAndFeatureRelation {

    public Device device;
    public List<DeviceFeature> deviceFeatureList;

    public DeviceAndFeatureRelation() {
    }

    public DeviceAndFeatureRelation(Device device, List<DeviceFeature> deviceFeatureList) {
        this.device = device;
        this.deviceFeatureList = deviceFeatureList;
    }

    public static DeviceAndFeatureRelation save(DeviceAndFeatureRelationPersistenceService relationService, DeviceAndFeatureRelation relation) {
        DeviceAndFeatureRelationDao relationDao = relationService.saveRelation(toDao(relation));
        return toRelation(relationDao);
    }

    public static DeviceAndFeatureRelation findByDeviceIdentificationCode(
            DeviceAndFeatureRelationPersistenceService relationService, String identificationCode) {
        DeviceAndFeatureRelationDao relationDao = relationService.getRelationByDeviceIdentificationCode(identificationCode);
        return toRelation(relationDao);
    }

    public static List<DeviceAndFeatureRelation> findAll(DeviceAndFeatureRelationPersistenceService relationService) {
        List<DeviceAndFeatureRelationDao> daoList = relationService.getAll();
        return toRelationList(daoList);
    }

    public static DeviceAndFeatureRelationDao toDao(DeviceAndFeatureRelation relation) {
        if (relation == null) return null;
        DeviceAndFeatureRelationDao relationDao = new DeviceAndFeatureRelationDao(
                Device.toDao(relation.device),
                DeviceFeature.toDaoList(relation.deviceFeatureList)
        );
        return relationDao;
    }

    public static DeviceAndFeatureRelation toRelation(DeviceAndFeatureRelationDao relationDao) {
        if (relationDao == null) return null;
        DeviceAndFeatureRelation featureRelation = new DeviceAndFeatureRelation(
                Device.toDevice(relationDao.deviceDao),
                DeviceFeature.toDeviceFeatureList(relationDao.deviceFeatureDaoList)
        );
        return featureRelation;
    }

    public static List<DeviceAndFeatureRelation> toRelationList(List<DeviceAndFeatureRelationDao> daoList) {
        if (daoList == null) return null;
        List<DeviceAndFeatureRelation> relationList = new ArrayList<>();
        for (DeviceAndFeatureRelationDao deviceAndFeatureRelationDao : daoList) {
            relationList.add(toRelation(deviceAndFeatureRelationDao));
        }
        return relationList;
    }
}
