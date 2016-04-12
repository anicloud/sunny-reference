package com.anicloud.sunny.application.service.agent;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.dto.anistub.Argument;
import com.ani.octopus.service.agent.service.websocket.AniInvokable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource(name = "agentTemplate")
    private AgentTemplate agentTemplate;

    @Override
    public List<Argument> stubRunSync(AniStub aniStub) throws IOException, EncodeException {
        AniInvokable aniInvokable = agentTemplate.getAniInvokable();
        return aniInvokable.invokeAniObjectSync(aniStub);
    }

    @Override
    public void stubRunAsyn(AniStub aniStub) {
        // TODO
    }
}
