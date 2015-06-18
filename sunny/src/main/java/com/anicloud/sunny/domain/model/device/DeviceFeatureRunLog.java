package com.anicloud.sunny.domain.model.device;

import com.anicloud.sunny.domain.model.share.FunctionValue;
import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureRunLogDao;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceFeaturePersistenceService;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceFeatureRunLogPersistenceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeatureRunLog extends AbstractDomain {
    private static final long serialVersionUID = 776442568416501965L;

    public String deviceFeatureRunLogNum;
    public Device device;
    public DeviceFeature deviceFeature;

    public User owner;
    public List<FunctionValue> functionValueList;

    public DeviceFeatureRunLog() {
    }

    public DeviceFeatureRunLog(Device device, DeviceFeature deviceFeature,
                               String deviceFeatureRunLogNum,
                               List<FunctionValue> functionValueList, User owner) {
        this.device = device;
        this.deviceFeature = deviceFeature;
        this.deviceFeatureRunLogNum = deviceFeatureRunLogNum;
        this.functionValueList = functionValueList;
        this.owner = owner;
    }

    public static DeviceFeatureRunLog save(DeviceFeatureRunLogPersistenceService runLogPersistenceService,
                                           DeviceFeatureRunLog deviceFeatureRunLog) {
        DeviceFeatureRunLogDao runLogDao = runLogPersistenceService.save(toDao(deviceFeatureRunLog));

        return toFeatureRunLog(runLogDao);
    }

    public static void remove(DeviceFeatureRunLogPersistenceService runLogPersistenceService,
                              String deviceFeatureRunLogNum) {
        runLogPersistenceService.remove(deviceFeatureRunLogNum);
    }

    public static List<DeviceFeatureRunLog> getDeviceFeatureRunLogByUser(
            DeviceFeatureRunLogPersistenceService runLogPersistenceService, String hashUserId) {
        List<DeviceFeatureRunLogDao> logDaoList = runLogPersistenceService.getFeatureRunLogList(hashUserId);
        return toFeatureRunLogList(logDaoList);
    }

    public static List<DeviceFeatureRunLog> getDeviceFeatureRunLogByDeviceAndUser(
            DeviceFeatureRunLogPersistenceService runLogPersistenceService,
            String identificationCode, String hashUserId) {
        List<DeviceFeatureRunLogDao> logDaoList = runLogPersistenceService
                .getFeatureRunLogByDeviceAndUser(identificationCode, hashUserId);
        return toFeatureRunLogList(logDaoList);
    }

    public static DeviceFeatureRunLog getDeviceFeatureRunLogByNum(DeviceFeatureRunLogPersistenceService runLogPersistenceService,
                                                                  String deviceFeatureRunLogNum) {
        DeviceFeatureRunLogDao runLogDao = runLogPersistenceService.getFeatureRunLogByNum(deviceFeatureRunLogNum);
        return toFeatureRunLog(runLogDao);
    }

    public static DeviceFeatureRunLog toFeatureRunLog(DeviceFeatureRunLogDao runLogDao) {
        if (runLogDao == null) {
            return null;
        }
        DeviceFeatureRunLog featureRunLog = new DeviceFeatureRunLog(
                Device.toDevice(runLogDao.deviceDao),
                DeviceFeature.toDeviceFeature(runLogDao.deviceFeatureDao),
                runLogDao.deviceFeatureRunLogNum,
                FunctionValue.toLogFunctionValueList(runLogDao.functionValueDaoList),
                User.toUser(runLogDao.owner)
        );
        return featureRunLog;
    }

    public static DeviceFeatureRunLogDao toDao(DeviceFeatureRunLog runLog) {
        if (runLog == null) {
            return null;
        }
        DeviceFeatureRunLogDao runLogDao = new DeviceFeatureRunLogDao(
                Device.toDao(runLog.device),
                DeviceFeature.toDao(runLog.deviceFeature),
                runLog.deviceFeatureRunLogNum,
                FunctionValue.toLogFunctionValueDaoList(runLog.functionValueList),
                User.toDao(runLog.owner)
        );
        return runLogDao;
    }

    public static List<DeviceFeatureRunLog> toFeatureRunLogList(List<DeviceFeatureRunLogDao> daoList) {
        List<DeviceFeatureRunLog> featureRunLogList = new ArrayList<>();
        for (DeviceFeatureRunLogDao runLogDao : daoList) {
            featureRunLogList.add(toFeatureRunLog(runLogDao));
        }
        return featureRunLogList;
    }

    public static List<DeviceFeatureRunLogDao> toDaoList(List<DeviceFeatureRunLog> runLogList) {
        List<DeviceFeatureRunLogDao> logDaoList = new ArrayList<>();
        for (DeviceFeatureRunLog featureRunLog : runLogList) {
            logDaoList.add(toDao(featureRunLog));
        }
        return logDaoList;
    }

    @Override
    public String toString() {
        return "DeviceFeatureRunLog{" +
                "deviceFeatureRunLogNum='" + deviceFeatureRunLogNum + '\'' +
                '}';
    }
}
