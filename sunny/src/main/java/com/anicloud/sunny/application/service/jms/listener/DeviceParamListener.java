package com.anicloud.sunny.application.service.jms.listener;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by lihui on 16-10-31.
 */
@Component("deviceParamListener")
public class DeviceParamListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

    }
}
