package com.anicloud.sunny.application.service.sunny.stub;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.dto.anistub.Argument;
import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.service.device.DeviceAndUserRelationServcie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

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
public class RefreshTemperatureStub implements SunnyStub {

    @Resource
    private DeviceAndUserRelationServcie relationService;
    @Resource
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

            Map<String,String> params =  objectMapper.readValue(relationDto.initParam,Map.class);
            for (Argument arg : inputValues) {
                switch (arg.getArgumentType().getType()) {
                    case FLOAT:
                        Float aFloat = (Float)arg.getValue();
                        params.put(arg.getName(),String.valueOf(aFloat));
                        break;
                    case INTEGER:
                        Integer aInteger = (Integer)arg.getValue();
                        params.put(arg.getName(),String.valueOf(aInteger));
                        break;
                    case STRING:
                        String str = (String)arg.getValue();
                        params.put(arg.getName(),str);
                        break;
                    case CHAR:
                        Character c = (Character)arg.getValue();
                        params.put(arg.getName(),String.valueOf(c));
                        break;
                    case BOOLEAN:
                        Boolean bool = (Boolean) arg.getValue();
                        params.put(arg.getName(),String.valueOf(bool));
                        break;
                    case BYTE:
                        Byte aByte = (Byte) arg.getValue();
                        params.put(arg.getName(),String.valueOf(aByte));
                        break;
                    case LONG:
                        Long aLong = (Long) arg.getValue();
                        params.put(arg.getName(),String.valueOf(aLong));
                        break;
                    case SHORT:
                        Short aShort = (Short) arg.getValue();
                        params.put(arg.getName(),String.valueOf(aShort));
                        break;
                    default:break;
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
