package com.anicloud.sunny.application.service.agent;

import com.ani.agent.service.core.validate.DomainObjectValidator;
import com.ani.agent.service.service.websocket.ClientInvokable;
import com.ani.bus.service.commons.dto.anistub.AniDataType;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.dto.anistub.Argument;

import com.ani.bus.service.commons.dto.anistub.ArgumentType;
import com.anicloud.sunny.application.service.strategy.CurrentFeatureService;
import com.anicloud.sunny.infrastructure.persistence.domain.device.CurrentFeatureInstanceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ValidationException;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
@Component
public class ClientInvokerImpl implements ClientInvokable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientInvokerImpl.class);
    @Resource
    private CurrentFeatureService currentFeatureService;

    @Override
    public List<Argument> invokeAniObjectSync(AniStub stub) throws IOException, EncodeException {
        if (!DomainObjectValidator.isDomainObjectValid(stub)) {
            throw new ValidationException("Invalid AniStub Instance.");
        }
        List<Argument> input = stub.getInputValues();
        if (input != null) {
            LOGGER.info("\tsize:" + input.size());
            for (int i = 0; i < input.size(); i++) {
                Argument argument = input.get(i);
                LOGGER.info("\targument[" + i + "] name:" + argument.getName());
            }
            LOGGER.info("Invocation begins.......");
            List<CurrentFeatureInstanceDao> featureInstanceDaos = currentFeatureService.findAll();
            Object value = input.get(0).getValue();
            int num = (int)value;
            List<Argument> deviceIds = new ArrayList<>();
            Argument argument = new Argument();
            argument.setArgumentType(new ArgumentType(AniDataType.ARRAY));
            List<Long> args = new ArrayList<>();
            for(int i=0; i<num; i++) {
                args.add(Long.valueOf(featureInstanceDaos.get(i).deviceId));
            }
            argument.setValue(args);
            deviceIds.add(argument);
            stub.setOutputValues(deviceIds);
            LOGGER.info("Invocation ends......");
            return deviceIds;
        }
        return null;
    }

    @Override
    public void sessionOnClose(String s, CloseReason closeReason) {
        // TODO
    }

    @Override
    public void sessionOnError(String s, Throwable throwable) {
        // TODO
    }
}
