package com.anicloud.sunny.application.service.agent;

import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.ani.agent.service.service.websocket.ObjectNotify;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.service.device.DeviceService;
import com.anicloud.sunny.infrastructure.jms.DeviceStateQueueService;

import javax.annotation.Resource;

/**
 * Created by wyf on 16-7-11.
 */
public class ObjectNotifyImpl implements ObjectNotify{

    @Resource
    private DeviceService deviceService;
    @Resource
    private DeviceStateQueueService deviceStateQueueService;

    @Override
    public void deviceConectedNotify(Long objectId, String description) {
        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(String.valueOf(objectId));
        if(deviceDto != null){
            deviceService.modifyDeviceState(deviceDto,DeviceState.CONNECTED);
            deviceDto.deviceState = DeviceState.CONNECTED;
            deviceStateQueueService.updateDeviceState(deviceDto);
        }
    }

    @Override
    public void deviceDisconnectedNotify(Long objectId, String description) {
        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(String.valueOf(objectId));
        if(deviceDto != null){
            deviceService.modifyDeviceState(deviceDto,DeviceState.DISCONNECTED);
            deviceDto.deviceState = DeviceState.DISCONNECTED;
            deviceStateQueueService.updateDeviceState(deviceDto);
        }
    }

    @Override
    public void deviceBoundNotify(Long objectId, String description) {
        //TODO
    }

    @Override
    public void deviceUnBoundNotify(Long objectId, String description) {
        //TODO
    }

    @Override
    public void deviceSharedNotify(Long objectId, String description) {
        //TODO
    }

    @Override
    public void deviceUnsharedNotify(Long objectId, String description) {
        //TODO
    }

    @Override
    public void deviceUpdatedNotify(Long objectId, String description) {
        //TODO
    }
}
