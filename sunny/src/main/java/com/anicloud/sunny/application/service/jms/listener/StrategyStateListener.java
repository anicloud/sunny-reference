package com.anicloud.sunny.application.service.jms.listener;

import com.anicloud.sunny.application.assemble.StrategyDtoAssembler;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.web.websocket.DeviceStrategyInfoHandler;
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
            LOGGER.info("Strategy StateListener {}.", strategy.owner);
            Long hashUserId = strategy.owner.hashUserId;
            StrategyDto strategyDto = StrategyDtoAssembler.toDto(strategy);
            DeviceStrategyInfoHandler.sendMessageToUser(hashUserId, strategyDto);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
