package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.domain.model.device.DeviceFeature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @autor zhaoyu
 * @date 16-4-1
 * @since JDK 1.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/root-context.xml"
})
public class DeviceFeatureServiceTest {

    @Resource(name = "deviceFeatureService")
    private DeviceFeatureService deviceFeatureService;

    @Test
    public void testGetAll() throws Exception {
        Set<DeviceFeature> deviceFeatureList = deviceFeatureService.getAll();
        Assert.assertEquals("size equals", 3, deviceFeatureList.size());
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testClearAll() throws Exception {
        deviceFeatureService.clearAll();
        Set<DeviceFeature> deviceFeatureList = deviceFeatureService.getAll();
        Assert.assertEquals("size equals", 3, deviceFeatureList.size());
    }

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testSaveAll() throws Exception {

    }

    @Test
    public void testGetDeviceFeatureById() {
        int id = 1;
        DeviceFeature deviceFeature = deviceFeatureService.getDeviceFeature(id);
        System.out.println(deviceFeature);
    }

    @Test
    public void testGetDeivceFeatureByName() {
        String name = "Light-powerOn";
        DeviceFeature deviceFeature = deviceFeatureService.getDeviceFeature(name);
        System.out.println(deviceFeature);
    }
}