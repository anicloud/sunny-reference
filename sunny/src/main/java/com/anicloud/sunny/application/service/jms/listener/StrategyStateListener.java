package com.anicloud.sunny.application.service.jms.listener;

import com.anicloud.sunny.application.assemble.StrategyDtoAssembler;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.interfaces.web.websocket.StrategyInfoHandler;
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
            LOGGER.info("StrategyStateListener {}.", strategy.owner);
            String hashUserId = strategy.owner.hashUserId;
            StrategyDto strategyDto = StrategyDtoAssembler.toDto(strategy);
            StrategyInfoHandler.sendMessageToUser(hashUserId, strategyDto);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
