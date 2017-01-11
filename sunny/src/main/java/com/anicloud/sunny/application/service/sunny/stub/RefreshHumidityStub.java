package com.anicloud.sunny.application.service.sunny.stub;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;
import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.service.device.DeviceAndUserRelationServcie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wyf on 17-1-11.
 */
@Service
public class RefreshHumidityStub implements SunnyStub{
    @Resource
    private DeviceAndUserRelationServcie relationService;
    @Resource(name = "paramJmsTemplate")
    private JmsTemplate paramJmsTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public List<StubArgumentDto> invokeStub(AniStub stub) {
        try {
            String fromObjectId = stub.fromObjectId+":"+stub.fromslaveId;
            Long accountId = stub.accountId;
            DeviceAndUserRelationDto relationDto = relationService.getDeviceAndUserRelation(fromObjectId,accountId);
            List<StubArgumentDto> inputValues = stub.inputArguments;
            Map<String,String> params;
            if (relationDto.initParam != null)
                params =  objectMapper.readValue(relationDto.initParam,Map.class);
            else
                params = new HashMap<>();

            for (StubArgumentDto arg : inputValues) {
                params.put("humidity",arg.getValue().toString());
            }
            relationDto.initParam = objectMapper.writeValueAsString(params);
            relationService.modifyRelation(relationDto);
            paramJmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(relationDto);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
