package com.anicloud.sunny.application.service.agent;

import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.ani.agent.service.service.websocket.ObjectNotify;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.service.device.DeviceService;
import com.anicloud.sunny.application.service.init.ApplicationInitService;
import com.anicloud.sunny.domain.model.device.Device;
import com.anicloud.sunny.infrastructure.jms.DeviceStateQueueService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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

    @Override
    public void deviceConectedNotify(Long objectId, String description) {
        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(objectId,-1));
        if(deviceDto != null){
            deviceService.modifyDeviceState(deviceDto,DeviceState.CONNECTED);
            deviceDto.deviceState = DeviceState.CONNECTED;
            deviceStateQueueService.updateDeviceState(deviceDto);
        }
    }

    @Override
    public void deviceDisconnectedNotify(Long objectId, String description) {
        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(Device.buildIdentificationCode(objectId,-1));
        if(deviceDto != null){
            deviceService.modifyDeviceState(deviceDto,DeviceState.DISCONNECTED);
            deviceDto.deviceState = DeviceState.DISCONNECTED;
            deviceStateQueueService.updateDeviceState(deviceDto);
        }
    }

    @Override
    public void deviceBoundNotify(DeviceMasterObjInfoDto deviceMasterObjInfoDto, String description) {
        //todo
    }

    @Override
    public void deviceUnBoundNotify(Long objectId, String description) {
        //todo
    }

    @Override
    public void deviceSharedNotify(DeviceMasterObjInfoDto deviceMasterObjInfoDto, Long hashUserId, String description) {
        //todo
    }

    @Override
    public void deviceUnsharedNotify(Long objectId, Long hashUserId, String description) {
        //todo
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
