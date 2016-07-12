package com.anicloud.sunny.infrastructure.jms;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by wyf on 16-7-11.
 */
@Component
public class DeviceStateQueueServiceImpl implements DeviceStateQueueService{
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceStateQueueServiceImpl.class);

    @Resource(name = "deviceJmsTemplate")
    private JmsTemplate deviceJmsTemplate;

    @Override
    public void updateDeviceState(DeviceDto deviceDto) {
        LOGGER.info("updateStrategyState strategy id : {}", deviceDto.identificationCode);

        deviceJmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(deviceDto);
            }
        });
    }
}
