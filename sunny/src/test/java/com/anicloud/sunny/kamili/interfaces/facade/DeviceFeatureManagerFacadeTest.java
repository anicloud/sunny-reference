package com.anicloud.sunny.kamili.interfaces.facade;

import com.ani.bus.service.commons.dto.anidevice.stub.DataType;
import com.anicloud.sunny.kamili.interfaces.facade.dto.DeviceFeatureDto;
import com.anicloud.sunny.kamili.interfaces.facade.dto.FeatureArgDto;
import com.anicloud.sunny.kamili.interfaces.facade.dto.StubIdentityDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.*;

/**
 * Created by MRK on 2016/3/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/root-context.xml"
})
public class DeviceFeatureManagerFacadeTest {

    @Resource
    private DeviceFeatureServiceFacade deviceFeatureManagerFacade;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceFeatureServiceFacade.class);

    @Test
    public void testGetAll() throws Exception {
        List<DeviceFeatureDto> deviceFeatureDtos = deviceFeatureManagerFacade.getAll();
        LOGGER.info("获取到的DeviceFeature列表："+deviceFeatureDtos);
        LOGGER.info("获取到的信息数量："+deviceFeatureDtos.size());
    }

    @Test
    public void testDelete() throws Exception {
        DeviceFeatureDto deviceFeatureDto = new DeviceFeatureDto(1,"Light-PowerOn","power on the light",null,null,null);
        deviceFeatureManagerFacade.delete(deviceFeatureDto);
        LOGGER.info("---删除成功！---");
    }

    @Test
    public void testClearAll() throws Exception {
        deviceFeatureManagerFacade.clearAll();
        LOGGER.info("---删除所以成功---");
    }

    @Test
    public void testSave() throws Exception {
        DeviceFeatureDto deviceFeatureDto = new DeviceFeatureDto(1,"Light-PowerOn","power on the light",null,null,null);
        deviceFeatureManagerFacade.save(deviceFeatureDto);
        LOGGER.info("---添加成功---");
    }

    @Test
    public void testSaveAll() throws Exception {
        DeviceFeatureDto deviceFeatureDto1 = new DeviceFeatureDto(1,"Light-PowerOn","power on the light",null,null,null);
        DeviceFeatureDto deviceFeatureDto2 = new DeviceFeatureDto(2,"Light-Poweroff","power off the light",null,null,null);
        FeatureArgDto featureArgDto = new FeatureArgDto(1,"brightness", DataType.INTEGER,"Adjust Brightness");
        Set<FeatureArgDto> inputArg = new HashSet<>();
        inputArg.add(featureArgDto);
        StubIdentityDto stubIdentityDto1 = new StubIdentityDto(1L,1);
        StubIdentityDto stubIdentityDto2 = new StubIdentityDto(1L,3);
        List<StubIdentityDto> stubIdentityDtos = new ArrayList<>();
        stubIdentityDtos.add(stubIdentityDto1);
        stubIdentityDtos.add(stubIdentityDto2);
        Map<String,Map<StubIdentityDto,String>> inputArgMapping = new HashMap<>();
        Map<StubIdentityDto,String> stubIdentityDtoStringMap = new HashMap<>();
        stubIdentityDtoStringMap.put(new StubIdentityDto(1L,3),"brightness");
        inputArgMapping.put("brightness",stubIdentityDtoStringMap);
        DeviceFeatureDto deviceFeatureDto3 = new DeviceFeatureDto(3,"Light-setBrightness",
                "set the brightness of the light",inputArg,stubIdentityDtos,inputArgMapping
        );
        List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>();
        deviceFeatureDtoList.add(deviceFeatureDto1);
        deviceFeatureDtoList.add(deviceFeatureDto2);
        deviceFeatureDtoList.add(deviceFeatureDto3);
        deviceFeatureManagerFacade.saveAll(deviceFeatureDtoList);
        LOGGER.info("---批量保存成功！---");

    }
}