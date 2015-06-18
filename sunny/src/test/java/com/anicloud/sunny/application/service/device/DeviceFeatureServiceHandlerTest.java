package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.builder.DeviceFeatureDtoBuilder;
import com.anicloud.sunny.application.builder.FeatureFunctionDtoBuilder;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.application.dto.device.FunctionArgumentDto;
import com.anicloud.sunny.domain.model.device.FeatureFunction;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DataType;
import com.anicloud.sunny.infrastructure.persistence.domain.share.FunctionType;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ObjectState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 15-6-15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/rect-persist.xml",
        "classpath:spring/root-context.xml"
})
public class DeviceFeatureServiceHandlerTest {

    @Resource
    private DeviceFeatureService deviceFeatureService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private DeviceFeatureDto deviceFeatureDto;

    @Before
    public void before() throws JsonProcessingException {
        deviceFeatureDto = createData();
        System.out.println(objectMapper.writeValueAsString(deviceFeatureDto));
    }

    @Ignore
    public void testSaveDeviceFeature() throws Exception {
        deviceFeatureDto = deviceFeatureService.saveDeviceFeature(deviceFeatureDto);
        System.out.println(objectMapper.writeValueAsString(deviceFeatureDto));
    }

    @Ignore
    public void testModifyDeviceFeature() throws Exception {

    }

    @Ignore
    public void testRemoveDeviceFeature() throws Exception {
        String deviceFeatureNum = "3aa1831f0325477e988973e50dd74a2d";
        deviceFeatureService.removeDeviceFeature(deviceFeatureNum);
    }

    @Ignore
    public void testBatchInsertDeviceFeature() throws Exception {

    }

    @Ignore
    public void testGetDeviceFeatureByNum() throws JsonProcessingException {
        String deviceFeatureNum = "3aa1831f0325477e988973e50dd74a2d";
        DeviceFeatureDto deviceFeatureDto = deviceFeatureService
                .getDeviceFeatureByNum(deviceFeatureNum);
        System.out.println(objectMapper.writeValueAsString(deviceFeatureDto));
    }

    @Ignore
    public void testGetAllDeviceFeature() {
        List<DeviceFeatureDto> deviceFeatureDtoList = deviceFeatureService.getAllDeviceFeature();
        Assert.assertEquals(1, deviceFeatureDtoList.size());
    }

    @After
    public void after() {

    }

    private DeviceFeatureDto createData() {
        FeatureFunctionDtoBuilder dtoBuilder = new FeatureFunctionDtoBuilder();
        FeatureFunctionDto featureFunctionDto = dtoBuilder.setFunctionGroup("deviceGroup")
                .setFunctionName("setTemperature")
                .setFunctionType(FunctionType.SYNC)
                .setFunctionArgument(new FunctionArgumentDto(
                        DataType.FLOAT,
                        "temperature",
                        ObjectState.ACTIVE
                ))
                .setFunctionArgument(new FunctionArgumentDto(
                        DataType.BOOLEAN,
                        "isopen",
                        ObjectState.ACTIVE
                ))
                .instance();

        FeatureFunctionDto featureFunctionDto1 = dtoBuilder.setFunctionGroup("deviceGroup")
                .setFunctionName("turnOn")
                .setFunctionType(FunctionType.SYNC)
                .setFunctionArgument(new FunctionArgumentDto(
                        DataType.FLOAT,
                        "temperature",
                        ObjectState.ACTIVE
                ))
                .instance();

        FeatureFunctionDto featureFunctionDto2 = dtoBuilder.setFunctionGroup("deviceGroup")
                .setFunctionName("turnOff")
                .setFunctionType(FunctionType.SYNC)
                .setFunctionArgument(new FunctionArgumentDto(
                        DataType.FLOAT,
                        "temperature",
                        ObjectState.ACTIVE
                ))
                .instance();

        Set<FeatureFunctionDto> featureFunctionSet = new HashSet<>();
        featureFunctionSet.add(featureFunctionDto);
        featureFunctionSet.add(featureFunctionDto1);
        featureFunctionSet.add(featureFunctionDto2);

        DeviceFeatureDtoBuilder deviceFeatureDtoBuilder = new DeviceFeatureDtoBuilder();
        DeviceFeatureDto deviceFeatureDto = deviceFeatureDtoBuilder
                .setFeatureName("setTemperatureTo")
                .setDescription("set temperature to")
                .setFeatureFunction(featureFunctionSet)
                .instance();

        return deviceFeatureDto;
    }
}