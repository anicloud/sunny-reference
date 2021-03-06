package com.anicloud.sunny.application.service.agent;

import com.ani.agent.service.service.AgentTemplate;
import com.ani.agent.service.service.websocket.AniInvokable;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;
import com.anicloud.sunny.application.constant.Constants;
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
    public AniStub stubRunSync(AniStub aniStub) throws IOException, EncodeException {
        AniInvokable aniInvokable = agentTemplate.getAniInvokable(Constants.aniServiceSession);
        return aniInvokable.invokeAniObjectSync(aniStub);
    }

    @Override
    public void stubRunAsyn(AniStub aniStub) {
        // TODO
    }
}
