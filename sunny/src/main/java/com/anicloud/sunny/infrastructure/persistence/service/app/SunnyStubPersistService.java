package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.application.service.sunny.stub.SunnyStubMappings;

/**
 * Created by lihui on 16-10-28.
 */
public interface SunnyStubPersistService {
    SunnyStubMappings fetchSunnyStubMappings() throws Exception;
    void update(SunnyStubMappings sunnyStubMappings) throws Exception;
}
