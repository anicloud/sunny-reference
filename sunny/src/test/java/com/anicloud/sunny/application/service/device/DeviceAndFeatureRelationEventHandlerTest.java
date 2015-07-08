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

    @Ignore
    public void testFindByDeviceIdentificationCode() throws Exception {
        String idCode = "6c584262e3281388810df535c76e62aa|23b8dd0b6b5620c9a4ccdcd593c052a9";
        String identificationCode = "bcedaeca3084961e81de4b77194d6899|005a8802bf0ae58803c581956285b36f";
        DeviceAndFeatureRelationDto relationDto = deviceAndFeatureRelationService.findByDeviceIdentificationCode(identificationCode);
        System.out.println(objectMapper.writeValueAsString(relationDto));
    }
}