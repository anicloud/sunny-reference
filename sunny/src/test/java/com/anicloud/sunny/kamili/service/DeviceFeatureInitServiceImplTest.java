package com.anicloud.sunny.kamili.service;

import com.anicloud.sunny.kamili.application.service.init.DeviceFeatureInitService;
import com.anicloud.sunny.kamili.domain.model.device.DeviceFeature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by MRK on 2016/3/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/root-context.xml"
})
public class DeviceFeatureInitServiceImplTest {

    @Resource
    private DeviceFeatureInitService deviceFeatureInitService;

    @Test
    public void testInitDeviceFeature() throws Exception {
        List<DeviceFeature> deviceFeatureList = deviceFeatureInitService.initDeviceFeature();
        System.out.println(deviceFeatureList);
    }

    @Test
    public void testGetAll() throws IOException {
        List<DeviceFeature> deviceFeatureList = deviceFeatureInitService.getAll();
        System.out.println(deviceFeatureList);

    }

}