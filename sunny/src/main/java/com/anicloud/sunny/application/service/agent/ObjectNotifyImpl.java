package com.anicloud.sunny.application.service.agent;

import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.ani.agent.service.service.websocket.ObjectNotify;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.JmsTypicalMessage;
import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.device.DeviceAndFeatureRelationService;
import com.anicloud.sunny.application.service.device.DeviceAndUserRelationServcie;
import com.anicloud.sunny.application.service.device.DeviceService;
import com.anicloud.sunny.application.service.init.ApplicationInitService;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.domain.model.device.Device;
import com.anicloud.sunny.infrastructure.jms.DeviceStateQueueService;
import com.anicloud.sunny.infrastructure.jms.StateQueueService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 16-7-11.
 */
@Component("objectNotify")
public class ObjectNotifyImpl implements ObjectNotify{

    @Resource
    private DeviceService deviceService;
    @Resource
    private DeviceStateQueueService deviceStateQueueService;
    @Resource
    ApplicationInitService applicationInitService;
    @Resource
    private UserService userService;
    @Resource
    private DeviceAndUserRelationServcie deviceAndUserRelationServcie;
    @Resource
    private DeviceAndFeatureRelationService deviceAndFeatureRelationService;
    @Resource
    private StateQueueService stateQueueService;

    @Override
    public void deviceConectedNotify(Long objectId, String description) {
        updateObjectState(objectId, DeviceState.CONNECTED);
    }

    @Override
    public void deviceDisconnectedNotify(Long objectId, String description) {
        updateObjectState(objectId, DeviceState.DISCONNECTED);
    }

    private void updateObjectState(Long objectId, DeviceState state) {
        List<Integer> slaves = Constants.DEVICE_ID_RELATION_MAP.get(objectId);
        if(slaves != null) {
            for (Integer slaveId : slaves) {
                DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(objectId, slaveId));
                if (deviceDto != null) {
                    deviceService.modifyDeviceState(deviceDto, state);
                    deviceDto.deviceState = state;
                    deviceStateQueueService.updateDeviceState(deviceDto);
                }
            }
        }
        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(objectId,-1));
        if(deviceDto != null){
            deviceService.modifyDeviceState(deviceDto,state);
            deviceDto.deviceState = state;
            deviceStateQueueService.updateDeviceState(deviceDto);
        }
    }

    @Override
    public void deviceBoundNotify(DeviceMasterObjInfoDto deviceMasterObjInfoDto, String description) {
        UserDto userDto = userService.getUserByHashUserId(deviceMasterObjInfoDto.owner.accountId);
        if(userDto == null) return;
        applicationInitService.updateUserDeviceAndDeviceFeatureRelation(deviceMasterObjInfoDto);
        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(deviceMasterObjInfoDto.objectId, -1));
        DeviceAndUserRelationDto relationDto = new DeviceAndUserRelationDto(deviceDto,userDto,"{}",deviceDto.name,"default");
        List<DeviceAndUserRelationDto> relationDtos = new ArrayList<>();
        relationDtos.add(relationDto);
        if (deviceMasterObjInfoDto.slaves != null && deviceMasterObjInfoDto.slaves.size() > 0) {
            List<Integer> slaveIds =  new ArrayList<>();
            for (DeviceSlaveObjInfoDto slaveObjInfoDto : deviceMasterObjInfoDto.slaves) {
                DeviceDto slaveDeviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(deviceMasterObjInfoDto.objectId, slaveObjInfoDto.objectSlaveId));
                DeviceAndUserRelationDto slaveRelationDto = new DeviceAndUserRelationDto(slaveDeviceDto, userDto, "{}", slaveDeviceDto.name, "default");
                relationDtos.add(slaveRelationDto);
                slaveIds.add(slaveObjInfoDto.objectSlaveId);
            }
            Constants.DEVICE_ID_RELATION_MAP.put(deviceMasterObjInfoDto.objectId,slaveIds);
        }
        deviceAndUserRelationServcie.batchSave(relationDtos);
        //todo: notify UI
        JmsTypicalMessage message = new JmsTypicalMessage(relationDtos,Constants.DEVICE_BOUND_MESSAGE,deviceMasterObjInfoDto.owner.accountId);
        stateQueueService.updateState(message);
    }

    @Override
    public void deviceUnBoundNotify(Long objectId, String description) {
        List<Integer> slaves = Constants.DEVICE_ID_RELATION_MAP.get(objectId);
        List<String> deviceIds = new ArrayList<>();
        deviceIds.add(Device.buildIdentificationCode(objectId,-1));
        List<Long> hashUserIds = deviceAndUserRelationServcie.findUserIdByDeviceId(Device.buildIdentificationCode(objectId,-1));
        if(slaves != null) {
            for (Integer slaveId : slaves) {
                DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(objectId, slaveId));
                if (deviceDto != null) {
                    deviceAndUserRelationServcie.removeRelationsWithDeviceId(deviceDto.identificationCode);
                    deviceAndFeatureRelationService.removeByDeviceId(deviceDto.identificationCode);
                    deviceIds.add(deviceDto.identificationCode);
                }
            }
        }
        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(objectId,-1));
        deviceAndUserRelationServcie.removeRelationsWithDeviceId(deviceDto.identificationCode);
        deviceAndFeatureRelationService.removeByDeviceId(deviceDto.identificationCode);
        //todo: notify UI
        if(hashUserIds != null) {
            for(Long userId : hashUserIds) {
                JmsTypicalMessage message = new JmsTypicalMessage(deviceIds,Constants.DEVICE_UNBOUND_MESSAGE,userId);
                stateQueueService.updateState(message);
            }
        }
    }

    @Override
    public void deviceSharedNotify(DeviceMasterObjInfoDto deviceMasterObjInfoDto, Long hashUserId, String description) {
        UserDto userDto = userService.getUserByHashUserId(hashUserId);
        if(userDto == null) return;
        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(deviceMasterObjInfoDto.objectId, -1));
        if (deviceDto == null) {
            applicationInitService.updateUserDeviceAndDeviceFeatureRelation(deviceMasterObjInfoDto);
        }
        deviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(deviceMasterObjInfoDto.objectId, -1));
        DeviceAndUserRelationDto relationDto = new DeviceAndUserRelationDto(deviceDto,userDto,"{}",deviceDto.name,"default");
        List<DeviceAndUserRelationDto> relationDtos = new ArrayList<>();
        relationDtos.add(relationDto);

        if (deviceMasterObjInfoDto.slaves != null && deviceMasterObjInfoDto.slaves.size() > 0) {
            List<Integer> slaveIds =  new ArrayList<>();
            for (DeviceSlaveObjInfoDto slaveObjInfoDto : deviceMasterObjInfoDto.slaves) {
                DeviceDto slaveDeviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(deviceMasterObjInfoDto.objectId, slaveObjInfoDto.objectSlaveId));
                DeviceAndUserRelationDto slaveRelationDto = new DeviceAndUserRelationDto(slaveDeviceDto, userDto, "{}", slaveDeviceDto.name, "default");
                relationDtos.add(slaveRelationDto);
                slaveIds.add(slaveObjInfoDto.objectSlaveId);
            }
            Constants.DEVICE_ID_RELATION_MAP.put(deviceMasterObjInfoDto.objectId,slaveIds);
        }
        deviceAndUserRelationServcie.batchSave(relationDtos);
        //todo: notify UI
        JmsTypicalMessage message = new JmsTypicalMessage(relationDtos,Constants.DEVICE_SHARE_MESSAGE,hashUserId);
        stateQueueService.updateState(message);
    }

    @Override
    public void deviceUnsharedNotify(Long objectId, Long hashUserId, String description) {
        List<Integer> slaves = Constants.DEVICE_ID_RELATION_MAP.get(objectId);
        List<DeviceAndUserRelationDto> relationDtos = new ArrayList<>();
        List<String> deviceIds = new ArrayList<>();
        if(slaves != null) {
            for (Integer slaveId : slaves) {
                DeviceAndUserRelationDto relationDto = deviceAndUserRelationServcie.getDeviceAndUserRelation(
                        Device.buildIdentificationCode(objectId, slaveId),
                        hashUserId
                );
                relationDtos.add(relationDto);
                deviceIds.add(Device.buildIdentificationCode(objectId, slaveId));
            }
        }
        DeviceAndUserRelationDto relationDto = deviceAndUserRelationServcie.getDeviceAndUserRelation(
                Device.buildIdentificationCode(objectId, -1),
                hashUserId
        );
        relationDtos.add(relationDto);
        deviceIds.add(Device.buildIdentificationCode(objectId, -1));
        deviceAndUserRelationServcie.batchRemove(relationDtos);
        //todo: notify UI
        JmsTypicalMessage message = new JmsTypicalMessage(deviceIds,Constants.DEVICE_UNSHARE_MESSAGE,hashUserId);
        stateQueueService.updateState(message);
    }

    @Override
    public void deviceUpdatedNotify(DeviceMasterObjInfoDto deviceMasterObjInfoDto) {
        applicationInitService.updateUserDeviceAndDeviceFeatureRelation(deviceMasterObjInfoDto);
        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(deviceMasterObjInfoDto.objectId,-1));
        if(deviceDto != null) {
            deviceService.modifyDeviceState(deviceDto, DeviceState.CONNECTED);
            deviceDto.deviceState = DeviceState.CONNECTED;
            deviceStateQueueService.updateDeviceState(deviceDto);
        }
    }


}
