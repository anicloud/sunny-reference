package com.anicloud.sunny.infrastructure.jms;

import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihui on 17-1-9.
 */
@Component("stateQueueService")
public class StateQueueServiceImpl implements StateQueueService {

    @Resource
    private JmsTemplate stateJmsTemplate;

    @Override
    public void updateBoundAndShareState(final List<DeviceAndUserRelationDto> relationDtos) {
        for(DeviceAndUserRelationDto relationDto : relationDtos) {
            stateJmsTemplate.send(session -> session.createObjectMessage(relationDto));
        }
    }

    @Override
    public void updateUnBoundState(List<String> deviceIds) {
        for(String deviceId : deviceIds) {
            stateJmsTemplate.send(session -> session.createObjectMessage(deviceId));
        }
    }
}
