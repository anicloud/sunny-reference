package com.anicloud.sunny.application.service.jms.listener;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.interfaces.web.websocket.DeviceStrategyInfoHandler;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by lihui on 16-10-31.
 */
@Component("deviceParamListener")
public class DeviceParamListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try {
            DeviceAndUserRelationDto relationDto = (DeviceAndUserRelationDto) objectMessage.getObject();
            Long hashUserId = relationDto.userDto.hashUserId;
            DeviceStrategyInfoHandler.sendDeviceMessageToUser(hashUserId, relationDto);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
