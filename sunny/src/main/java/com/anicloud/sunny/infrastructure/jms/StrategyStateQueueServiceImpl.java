package com.anicloud.sunny.infrastructure.jms;

import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.schedule.domain.adapter.DtoAdapter;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import com.anicloud.sunny.schedule.dto.StrategyInstanceDto;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Created by zhaoyu on 15-7-20.
 */
@Component
public class StrategyStateQueueServiceImpl implements StrategyStateQueueService {

    @Resource
    private JmsTemplate strategyJmsTemplate;

    @Override
    public void updateStrategyState(final Strategy strategy) {

        strategyJmsTemplate.convertAndSend(
                strategy,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws JMSException {
                        if (strategy.strategyInstance != null && strategy.owner != null) {
                            message.setStringProperty(
                                    "userEmail",
                                    strategy.owner.email);
                        }
                        return message;
                    }
                });
    }
}
