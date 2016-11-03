package com.anicloud.sunny.application.service.agent;

import com.ani.agent.service.core.validate.DomainObjectValidator;
import com.ani.agent.service.service.websocket.ClientInvokable;
import com.ani.bus.service.commons.dto.anistub.AniDataType;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.dto.anistub.Argument;

import com.ani.bus.service.commons.dto.anistub.ArgumentType;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.service.strategy.CurrentFeatureService;
import com.anicloud.sunny.application.service.sunny.stub.SunnyStub;
import com.anicloud.sunny.application.service.sunny.stub.SunnyStubMappings;
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

        String stubGroupId = String.valueOf(stub.getStubId()) + String.valueOf(stub.getGroupId());
        int hashCode = stubGroupId.hashCode();

        SunnyStub sunnyStub = Constants.SUNNY_STUB_MAPPINGS.get(hashCode);
        if(sunnyStub != null)
            return sunnyStub.invokeStub(stub);
        else
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
