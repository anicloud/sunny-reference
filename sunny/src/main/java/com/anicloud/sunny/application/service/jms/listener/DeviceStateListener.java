package com.anicloud.sunny.application.service.jms.listener;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.web.websocket.DeviceStrategyInfoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by wyf on 16-7-11.
 */
@Component(value = "deviceStateListener")
public class DeviceStateListener implements MessageListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceStateListener.class);

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try {
            DeviceDto deviceDto = (DeviceDto) objectMessage.getObject();
            LOGGER.info("DeviceStateListener {}.", deviceDto.ownerId);
            Long hashUserId = deviceDto.ownerId;
            DeviceStrategyInfoHandler.sendDeviceMessageToUser(hashUserId, deviceDto);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
