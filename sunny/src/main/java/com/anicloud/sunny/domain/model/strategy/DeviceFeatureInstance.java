package com.anicloud.sunny.domain.model.strategy;

import com.anicloud.sunny.domain.model.device.Device;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.domain.model.share.FeatureArgValue;
import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.DeviceFeatureInstanceDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeatureInstance extends AbstractDomain {
    private static final long serialVersionUID = -6115388508700437219L;

    public String featureInstanceNum;
    public Device device;
    public DeviceFeature deviceFeature;
    public List<FeatureArgValue> featureArgValueList;
    public List<FeatureTrigger> triggerList;

    public DeviceFeatureInstance() {
    }

    public DeviceFeatureInstance(String featureInstanceNum, Device device,
                                 DeviceFeature deviceFeature,
                                 List<FeatureArgValue> featureArgValueList, List<FeatureTrigger> triggerList) {
        this.featureInstanceNum = featureInstanceNum;
        this.device = device;
        this.deviceFeature = deviceFeature;
        this.featureArgValueList = featureArgValueList;
        this.triggerList = triggerList;
    }

    public static DeviceFeatureInstance toInstance(DeviceFeatureInstanceDao instanceDao) {
        DeviceFeatureInstance featureInstance = new DeviceFeatureInstance(
                instanceDao.featureInstanceNum,
                Device.toDevice(instanceDao.deviceDao),
                DeviceFeature.toDeviceFeature(instanceDao.deviceFeatureDao),
                FeatureArgValue.toFunctionValueList(instanceDao.instanceFunctionValueDaoList),
                FeatureTrigger.toFeatureTriggerList(instanceDao.triggerDaoList)
        );
        return featureInstance;
    }

    public static DeviceFeatureInstanceDao toDao(DeviceFeatureInstance featureInstance) {
        DeviceFeatureInstanceDao instanceDao = new DeviceFeatureInstanceDao(
                featureInstance.featureInstanceNum,
                Device.toDao(featureInstance.device),
                DeviceFeature.toDao(featureInstance.deviceFeature),
                FeatureArgValue.toFeatureInstanceFuncValueList(featureInstance.featureArgValueList),
                FeatureTrigger.toDaoList(featureInstance.triggerList)
        );
        return instanceDao;
    }

    public static List<DeviceFeatureInstance> toInstanceList(List<DeviceFeatureInstanceDao> instanceDaoList) {
        List<DeviceFeatureInstance> instanceList = new ArrayList<>();
        for (DeviceFeatureInstanceDao instanceDao : instanceDaoList) {
            instanceList.add(toInstance(instanceDao));
        }
        return instanceList;
    }

    public static List<DeviceFeatureInstanceDao> toDaoList(List<DeviceFeatureInstance> instanceList) {
        List<DeviceFeatureInstanceDao> instanceDaoList = new ArrayList<>();
        for (DeviceFeatureInstance featureInstance : instanceList) {
            instanceDaoList.add(toDao(featureInstance));
        }
        return instanceDaoList;
    }
}
