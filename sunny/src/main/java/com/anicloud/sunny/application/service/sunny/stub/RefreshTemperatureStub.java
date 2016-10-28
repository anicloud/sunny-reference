package com.anicloud.sunny.application.service.sunny.stub;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.bus.service.commons.dto.anistub.Argument;
import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.service.device.DeviceAndUserRelationServcie;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihui on 16-10-28.
 */
public class RefreshTemperatureStub implements SunnyStub {

    @Resource
    private DeviceAndUserRelationServcie relationService;

    @Override
    public List<Argument> invokeStub(AniStub stub) {
        Long targetObjectId = stub.getTargetObjectId();
        Long objectId = stub.getAccountId();
        List<Argument> inputValues = stub.getInputValues();
//        DeviceAndUserRelationDto relation = relationService.getDeviceAndUserRelation(stub.getKeyId())
        for (Argument arg : inputValues) {

        }
        return null;
    }
}
