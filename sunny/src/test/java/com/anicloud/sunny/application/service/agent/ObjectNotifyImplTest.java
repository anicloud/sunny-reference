package com.anicloud.sunny.application.service.agent;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.infrastructure.jms.DeviceStateQueueService;
import com.anicloud.sunny.infrastructure.jms.DeviceStateQueueServiceImpl;
import com.anicloud.sunny.infrastructure.jms.StrategyStateQueueService;
import com.anicloud.sunny.infrastructure.jms.StrategyStateQueueServiceImpl;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by wyf on 16-7-11.
 */
public class ObjectNotifyImplTest {

    @Test
    public void deviceConectedNotify() throws Exception {
        Strategy strategy = new Strategy();
        StrategyStateQueueService strategyStateQueueService = new StrategyStateQueueServiceImpl();
        strategyStateQueueService.updateStrategyState(strategy);
        DeviceDto deviceDto = new DeviceDto("123");
        DeviceStateQueueService deviceStateQueueService = new DeviceStateQueueServiceImpl();
        deviceStateQueueService.updateDeviceState(deviceDto);
    }

}