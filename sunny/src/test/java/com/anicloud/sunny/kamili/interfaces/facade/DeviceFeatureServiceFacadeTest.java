package com.anicloud.sunny.kamili.interfaces.facade;

import com.anicloud.sunny.kamili.interfaces.facade.dto.DeviceFeatureDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MRK on 2016/3/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/root-context.xml"
})
public class DeviceFeatureServiceFacadeTest {

    @Resource
    private DeviceFeatureServiceFacade deviceFeatureServiceFacade;

    @Test
    public void testGetAll() throws Exception {
        List<DeviceFeatureDto> deviceFeatureDtos = deviceFeatureServiceFacade.getAll();
        System.out.println(deviceFeatureDtos.size());
    }
}