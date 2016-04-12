package com.anicloud.sunny.application.service.agent;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.dto.anistub.Argument;
import com.ani.octopus.service.agent.service.websocket.AniInvokable;
import com.ani.octopus.service.agent.service.websocket.AniInvokerImpl;
import com.anicloud.sunny.application.constant.Constants;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-4-12
 * @since JDK 1.7
 */
@Service("aniStubRunProxy")
public class AniStubRunProxyImpl implements AniStubRunProxy {

    @Override
    public List<Argument> stubRunSync(AniStub aniStub) throws IOException, EncodeException {
        AniInvokable aniInvokable = new AniInvokerImpl(Constants.aniServiceSession);
        return aniInvokable.invokeAniObjectSync(aniStub);
    }

    @Override
    public void stubRunAsyn(AniStub aniStub) {
        // TODO
    }
}
