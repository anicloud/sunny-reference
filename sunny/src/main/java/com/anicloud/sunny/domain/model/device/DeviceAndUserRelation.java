package com.anicloud.sunny.domain.model.device;

import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndUserRelationDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 16-10-12.
 */
public class DeviceAndUserRelation extends AbstractDomain {
    private static final long serialVersionUID = 8086119641660334563L;

    public Device device;
    public User user;
    public String initParam;
    public String screenName;
    public String deviceGroup;

    public DeviceAndUserRelation(){}

    public DeviceAndUserRelation(Device device, User user, String initParam, String screenName, String deviceGroup){
        this.device = device;
        this.user = user;
        this.initParam = initParam;
        this.screenName = screenName;
        this.deviceGroup = deviceGroup;
    }

    public static DeviceAndUserRelationDao toDao(DeviceAndUserRelation deviceAndUserRelation){
        if (deviceAndUserRelation == null) return null;
        DeviceAndUserRelationDao deviceAndUserRelationDao = new DeviceAndUserRelationDao(
                Device.toDao(deviceAndUserRelation.device),
                User.toDao(deviceAndUserRelation.user),
                deviceAndUserRelation.initParam,
                deviceAndUserRelation.screenName,
                deviceAndUserRelation.deviceGroup
        );
        return deviceAndUserRelationDao;
    }

    public static DeviceAndUserRelation toDeviceAndUserRelation(DeviceAndUserRelationDao deviceAndUserRelationDao){
        if (deviceAndUserRelationDao == null) return null;
        DeviceAndUserRelation deviceAndUserRelation = new DeviceAndUserRelation(
                Device.toDevice(deviceAndUserRelationDao.device),
                User.toUser(deviceAndUserRelationDao.user),
                deviceAndUserRelationDao.initParam,
                deviceAndUserRelationDao.screenName,
                deviceAndUserRelationDao.deviceGroup
        );
        return deviceAndUserRelation;
    }

    public static List<DeviceAndUserRelation> toRelationList(List<DeviceAndUserRelationDao> daoList){
        if (daoList == null) return null;
        List<DeviceAndUserRelation> relationList = new ArrayList<>();
        for (DeviceAndUserRelationDao deviceAndUserRelationDao : daoList) {
            relationList.add(toDeviceAndUserRelation(deviceAndUserRelationDao));
        }
        return relationList;
    }

    public static List<DeviceAndUserRelationDao> toDaoList(List<DeviceAndUserRelation> relationList){
        if (relationList == null) return null;
        List<DeviceAndUserRelationDao> daoList = new ArrayList<>();
        for (DeviceAndUserRelation deviceAndUserRelation : relationList) {
            daoList.add(toDao(deviceAndUserRelation));
        }
        return daoList;
    }
}
