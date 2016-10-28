package com.anicloud.sunny.application.service.sunny.stub;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.dto.anistub.Argument;

import java.util.List;

/**
 * Created by lihui on 16-10-28.
 */
public interface SunnyStub {
    List<Argument> invokeStub(AniStub stub);
}
