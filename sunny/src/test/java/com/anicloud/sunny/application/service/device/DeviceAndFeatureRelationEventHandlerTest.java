package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.dto.device.DeviceAndFeatureRelationDto;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ObjectState;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 15-7-8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/rect-persist.xml",
        "classpath:spring/root-context.xml"
})
public class DeviceAndFeatureRelationEventHandlerTest {

    @Resource
    private DeviceAndFeatureRelationService deviceAndFeatureRelationService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Ignore
    public void testSave() throws Exception {

    }

    @Ignore
    public void testBatchSave() throws Exception {

    }

    @Test
    public void testFindByDeviceIdentificationCode() throws Exception {
        String idCode = "6b4a9f8a32b99651c2f2ae37640457bb|cc86893c7073c459eb64fdcafaaaad1c";
        String identificationCode = "6b4a9f8a32b99651c2f2ae37640457bb|bc5445f8cdbdcccca02e51b1bc34b8b4";
        DeviceAndFeatureRelationDto relationDto = deviceAndFeatureRelationService.findByDeviceIdentificationCode(identificationCode);
        System.out.println(objectMapper.writeValueAsString(relationDto));
    }
}