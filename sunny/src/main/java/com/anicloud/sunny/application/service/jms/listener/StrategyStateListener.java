package com.anicloud.sunny.application.service.jms.listener;

import com.anicloud.sunny.domain.model.strategy.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by zhaoyu on 15-7-22.
 */
@Component(value = "consumerMessageListener")
public class StrategyStateListener implements MessageListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(StrategyStateListener.class);

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            Strategy strategy = (Strategy) objectMessage.getObject();
            // TODO
            // to implement the web socket notice here
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
