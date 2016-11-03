package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.anicloud.sunny.application.service.sunny.stub.SunnyStub;
import com.anicloud.sunny.application.service.sunny.stub.SunnyStubMappings;

import java.util.Map;

/**
 * Created by lihui on 16-10-28.
 */
public interface SunnyStubPersistService {
    Map<Integer,SunnyStub> fetchSunnyStubMappings() throws Exception;
    void update(AniStub aniStub,SunnyStub sunnyStub) throws Exception;
}
