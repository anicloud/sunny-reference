package com.anicloud.sunny.application.service.jms.listener;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.service.device.DeviceAndUserRelationServcie;
import com.anicloud.sunny.web.websocket.DeviceStrategyInfoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.List;

/**
 * Created by wyf on 16-7-11.
 */
@Component(value = "deviceStateListener")
public class DeviceStateListener implements MessageListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceStateListener.class);

    @Resource
    private DeviceAndUserRelationServcie deviceAndUserRelationServcie;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try {
            DeviceDto deviceDto = (DeviceDto) objectMessage.getObject();
            LOGGER.info("DeviceStateListener {}.", deviceDto.ownerId);
            List<DeviceAndUserRelationDto> relationDtos = deviceAndUserRelationServcie.getRelationsByDeviceId(deviceDto.identificationCode);
            if (relationDtos != null && relationDtos.size() > 0) {
                for (DeviceAndUserRelationDto relationDto : relationDtos) {
                    DeviceStrategyInfoHandler.sendDeviceMessageToUser(relationDto.userDto.hashUserId, relationDto);
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
