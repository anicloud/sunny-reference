package com.anicloud.sunny.application.service.jms.listener;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by lihui on 17-1-9.
 */
@Component("stateListener")
public class StateListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

    }
}
