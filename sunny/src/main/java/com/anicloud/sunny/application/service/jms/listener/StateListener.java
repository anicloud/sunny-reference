package com.anicloud.sunny.application.service.jms.listener;

import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.JmsTypicalMessage;
import com.anicloud.sunny.web.websocket.DeviceStrategyInfoHandler;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by lihui on 17-1-9.
 */
@Component("stateListener")
public class StateListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try {
            JmsTypicalMessage jmsMessage = (JmsTypicalMessage) objectMessage.getObject();
            DeviceStrategyInfoHandler.sendDeviceStateToUser(jmsMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
