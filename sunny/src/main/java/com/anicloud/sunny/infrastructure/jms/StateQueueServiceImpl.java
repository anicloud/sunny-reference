package com.anicloud.sunny.infrastructure.jms;

import com.anicloud.sunny.application.dto.JmsTypicalMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/** 
 * Created by lihui on 17-1-9.
 */
@Component("stateQueueService")
public class StateQueueServiceImpl implements StateQueueService {

    @Resource
    private JmsTemplate stateJmsTemplate;

    @Override
    public void updateState(JmsTypicalMessage message) {
        stateJmsTemplate.send(session -> session.createObjectMessage(message));
    }

}
