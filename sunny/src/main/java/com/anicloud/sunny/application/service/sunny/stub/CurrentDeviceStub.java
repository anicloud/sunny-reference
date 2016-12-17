package com.anicloud.sunny.application.service.sunny.stub;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;
import com.ani.octopus.commons.stub.type.DataCollectionType;
import com.ani.octopus.commons.stub.type.DataCollectionTypes;
import com.ani.octopus.commons.stub.type.DataPrimitiveType;
import com.ani.octopus.commons.stub.type.DataPrimitiveTypes;
import com.anicloud.sunny.application.service.strategy.CurrentFeatureService;
import com.anicloud.sunny.infrastructure.persistence.domain.device.CurrentFeatureInstanceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihui on 16-10-28.
 */
public class CurrentDeviceStub implements SunnyStub {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentDeviceStub.class);
    @Resource
    private CurrentFeatureService currentFeatureService;
    @Override
    public List<StubArgumentDto> invokeStub(AniStub stub) {
        List<StubArgumentDto> input = stub.inputArguments;
        if (input != null) {
            LOGGER.info("\tsize:" + input.size());
            for (int i = 0; i < input.size(); i++) {
                StubArgumentDto argument = input.get(i);
                LOGGER.info("\targument[" + i + "] value:" + argument.getValue());
            }
            LOGGER.info("Invocation begins.......");
            List<CurrentFeatureInstanceDao> featureInstanceDaos = currentFeatureService.findAll();
            Object value = input.get(0).getValue();
            int num = (int) value;
            List<StubArgumentDto> deviceIds = new ArrayList<>();
            StubArgumentDto argument = new StubArgumentDto();
//            argument.setArgumentType(new DataCollectionType(new DataPrimitiveType(DataPrimitiveTypes.LONG), DataCollectionTypes.LIST));
            List<Long> args = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                args.add(Long.valueOf(featureInstanceDaos.get(i).deviceId));
            }
            argument.setValue(args);
            deviceIds.add(argument);
            stub.outputArguments = deviceIds;
            LOGGER.info("Invocation ends......");
            return deviceIds;
        } else {
            return null;
        }
    }
}
