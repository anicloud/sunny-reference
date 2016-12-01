package com.anicloud.sunny.application.service.sunny.stub;

import com.ani.bus.service.commons.dto.anistub.AniDataType;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.dto.anistub.Argument;
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
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 16-10-28.
 */
@Service
public class RefreshTemperatureStub implements SunnyStub {

    @Resource
    private DeviceAndUserRelationServcie relationService;
    @Resource(name = "paramJmsTemplate")
    private JmsTemplate paramJmsTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public List<Argument> invokeStub(AniStub stub) {
        try {
            String fromObjectId = stub.getFromObjectId()+":"+stub.getFromslaveId();
            Long accountId = stub.getAccountId();
            DeviceAndUserRelationDto relationDto = relationService.getDeviceAndUserRelation(fromObjectId,accountId);
            List<Argument> inputValues = stub.getInputValues();

            Map<String,String> params = objectMapper.readValue(relationDto.initParam,Map.class);
            for (Argument arg : inputValues) {
                if (arg.getArgumentType().getType() != AniDataType.ARRAY){
                    params.put(arg.getName(),arg.getValue().toString());
                }
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
