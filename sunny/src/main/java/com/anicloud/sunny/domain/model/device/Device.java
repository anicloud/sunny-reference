package com.anicloud.sunny.domain.model.device;


import com.ani.cel.service.manager.agent.core.share.DeviceState;
import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceLogicState;
import com.anicloud.sunny.infrastructure.persistence.service.DevicePersistenceService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class Device extends AbstractDomain {
    private static final long serialVersionUID = 7775820276864550384L;

    public String identificationCode;       // id of device, consist of masterDeviceId and slaveDeviceId
    public String name;
    public DeviceState deviceState;
    // logic state
    public DeviceLogicState logicState;
    public String deviceType;
    public String deviceGroup;

    public User owner;

    public Device() {
    }

    public Device(String deviceGroup, DeviceState deviceState,
                  String deviceType, String identificationCode,
                  String name, User owner, DeviceLogicState logicState) {
        this.deviceGroup = deviceGroup;
        this.deviceState = deviceState;
        this.deviceType = deviceType;
        this.identificationCode = identificationCode;
        this.name = name;
        this.owner = owner;
        this.logicState = logicState;
    }

    public static Device save(DevicePersistenceService persistenceService, Device device) {
        DeviceDao deviceDao = toDao(device);
        deviceDao = persistenceService.save(deviceDao);
        return toDevice(deviceDao);
    }

    public static Device modify(DevicePersistenceService persistenceService, Device device) {
        DeviceDao deviceDao = persistenceService.modify(toDao(device));
        return toDevice(deviceDao);
    }

    /**
     * remove by identificationCode
     * @param device
     */
    public static void remove(DevicePersistenceService persistenceService, Device device) {
        persistenceService.remove(toDao(device));
    }

    public static void modifyDeviceState(DevicePersistenceService persistenceService, Device device, DeviceState deviceState) {
        persistenceService.modifyDeviceState(toDao(device), deviceState);
    }

    public static void modifyDeviceLogicState(DevicePersistenceService persistenceService, Device device, DeviceLogicState logicState) {
        persistenceService.modifyDeviceLogicState(toDao(device), logicState);
    }

    public static Device getDeviceByIdentificationCode(DevicePersistenceService persistenceService, String identificationCode) {
        DeviceDao deviceDao = persistenceService.getDeviceByIdentificationCode(identificationCode);
        return toDevice(deviceDao);
    }

    /**
     * search by user hashUserId
     * @param user
     * @return
     */
    public static List<Device> getDeviceByUser(DevicePersistenceService persistenceService, User user) {
        List<DeviceDao> deviceDaoList  = persistenceService.getDeviceByUser(User.toDao(user));
        return toDeviceList(deviceDaoList);
    }

    public static List<Device> getDeviceByUserAndGroup(DevicePersistenceService persistenceService, User user, String deviceGroup) {
        List<DeviceDao> deviceDaoList =
                persistenceService.getDeviceByUserAndGroup(User.toDao(user), deviceGroup);
        return toDeviceList(deviceDaoList);
    }

    public static List<Device> getDeviceByUserAndType(DevicePersistenceService persistenceService, User user, String deviceType) {
        List<DeviceDao> deviceDaoList =
                persistenceService.getDeviceByUserAndType(User.toDao(user), deviceType);
        return toDeviceList(deviceDaoList);
    }

    public static List<Device> getDeviceByUserAndState(DevicePersistenceService persistenceService, User user, DeviceState deviceState) {
        List<DeviceDao> deviceDaoList =
                persistenceService.getDeviceByUserAndState(User.toDao(user), deviceState);
        return toDeviceList(deviceDaoList);
    }

    public static List<Device> getDeviceByUserAndLogicState(DevicePersistenceService persistenceService, User user, DeviceLogicState logicState) {
        List<DeviceDao> deviceDaoList =
                persistenceService.getDeviceByUserAndLogicState(User.toDao(user), logicState);
        return toDeviceList(deviceDaoList);
    }

    public static List<String> getUserDeviceGroupList(DevicePersistenceService persistenceService, User user) {
        return persistenceService.getUserDeviceGroupList(User.toDao(user));
    }

    public static Device toDevice(DeviceDao deviceDao) {
        if (deviceDao == null) {
            return null;
        }
        Device device = new Device(
                deviceDao.deviceGroup,
                deviceDao.deviceState,
                deviceDao.deviceType,
                deviceDao.identificationCode,
                deviceDao.name,
                User.toUser(deviceDao.owner),
                deviceDao.logicState
        );
        return device;
    }

    public static DeviceDao toDao(Device device) {
        if (device == null) {
            return null;
        }
        DeviceDao deviceDao = new DeviceDao(
                device.deviceGroup,
                device.deviceState,
                device.deviceType,
                device.identificationCode,
                device.name,
                User.toDao(device.owner),
                device.logicState
        );
        return deviceDao;
    }

    public static List<Device> toDeviceList(List<DeviceDao> deviceDaoList) {
        List<Device> deviceList = new ArrayList<Device>(deviceDaoList.size());
        for (DeviceDao deviceDao : deviceDaoList) {
            deviceList.add(toDevice(deviceDao));
        }
        return deviceList;
    }

    public static List<DeviceDao> toDaoList(List<Device> deviceList) {
        List<DeviceDao> deviceDaoList = new ArrayList<DeviceDao>(deviceList.size());
        for (Device device : deviceList) {
            deviceDaoList.add(toDao(device));
        }
        return deviceDaoList;
    }
}
