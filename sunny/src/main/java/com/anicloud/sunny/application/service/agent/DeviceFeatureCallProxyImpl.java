package com.anicloud.sunny.application.service.agent;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.dto.anistub.Argument;
import com.ani.octopus.service.agent.service.websocket.AniInvokable;
import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstance;

import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-4-1
 * @since JDK 1.7
 */
public class DeviceFeatureCallProxyImpl implements DeviceFeatureCallProxy {

    // @Resource()
    private AniInvokable aniInvokable;

    @Override
    public List<Object> deviceFeatureCall(DeviceFeatureInstance deviceFeatureInstance) {
        List<Object> result = null;
        List<AniStub> aniStubList = fetchStubList(deviceFeatureInstance);
        try {
            for (AniStub aniStub : aniStubList) {
                List<Argument> argumentList = aniInvokable.invokeAniObjectSync(aniStub);
            }
        } catch (Exception e) {

        }
        return result;
    }

    private List<AniStub> fetchStubList(DeviceFeatureInstance deviceFeatureInstance) {
        // TODO
        return null;
    }
}
