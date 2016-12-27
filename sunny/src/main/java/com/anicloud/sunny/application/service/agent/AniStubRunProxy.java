package com.anicloud.sunny.application.service.agent;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-4-12
 * @since JDK 1.7
 */
public interface AniStubRunProxy {
    AniStub stubRunSync(AniStub aniStub) throws IOException, EncodeException;
    void stubRunAsyn(AniStub aniStub);

}
