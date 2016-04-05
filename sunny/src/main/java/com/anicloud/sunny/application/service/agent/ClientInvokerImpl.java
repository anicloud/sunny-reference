package com.anicloud.sunny.application.service.agent;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.dto.anistub.Argument;
import com.ani.octopus.service.agent.service.websocket.ClientInvokable;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */

public class ClientInvokerImpl implements ClientInvokable {
    @Override
    public List<Argument> invokeAniObjectSync(AniStub aniStub) throws IOException, EncodeException {
        // TODO
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
