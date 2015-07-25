package com.anicloud.sunny.application.service.device;

import com.ani.cel.service.manager.agent.core.share.DataType;
import com.ani.cel.service.manager.agent.core.share.FunctionType;
import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;
import com.anicloud.sunny.application.builder.DeviceFeatureDtoBuilder;
import com.anicloud.sunny.application.builder.FeatureFunctionDtoBuilder;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ArgumentType;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ObjectState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.*;

/**
 * Created by zhaoyu on 15-6-15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/root-context.xml"
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
                .getDeviceFeatureById(deviceFeatureNum);
        System.out.println(objectMapper.writeValueAsString(deviceFeatureDto));
    }

    @Test
    public void testGetAllDeviceFeature() throws JsonProcessingException {
        List<DeviceFeatureDto> deviceFeatureDtoList = deviceFeatureService.getAllDeviceFeature();
        System.out.println(objectMapper.writeValueAsString(deviceFeatureDtoList));
        Map<String, Set<String>> map = getFeatureFunctionMap(deviceFeatureDtoList);
        System.out.println(objectMapper.writeValueAsString(map));
    }

    private Map<String, Set<String>> getFeatureFunctionMap(List<DeviceFeatureDto> deviceFeatureDtoList) {
        Map<String, Set<String>> featureFunctionNameMap = new HashMap<>();
        for (DeviceFeatureDto deviceFeatureDto : deviceFeatureDtoList) {
            Set<String> functionNameArgSet = new HashSet<>();
            List<FeatureFunctionDto> featureFunctionDtoList = deviceFeatureDto.featureFunctionDtoList;
            for (FeatureFunctionDto functionDto : featureFunctionDtoList) {
                if (functionDto.inputArgList != null && functionDto.inputArgList.size() > 0) {
                    for (FunctionArgumentDto functionArgumentDto : functionDto.inputArgList) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(functionDto.functionGroup)
                                .append(":")
                                .append(functionDto.functionName)
                                .append(":")
                                .append(ArgumentType.INPUT_ARGUMENT)
                                .append(":")
                                .append(functionArgumentDto.name);
                        functionNameArgSet.add(stringBuilder.toString());
                    }
                }
                if (functionDto.outputArgList != null && functionDto.outputArgList.size() > 0) {
                    for (FunctionArgumentDto functionArgumentDto : functionDto.outputArgList) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(functionDto.functionGroup)
                                .append(":")
                                .append(functionDto.functionName)
                                .append(":")
                                .append(ArgumentType.OUTPUT_ARGUMENT)
                                .append(":")
                                .append(functionArgumentDto.name);
                        functionNameArgSet.add(stringBuilder.toString());
                    }
                }
                if ((functionDto.inputArgList.size() == 0 && functionDto.outputArgList.size() == 0)
                        || (functionDto.inputArgList == null && functionDto.outputArgList == null)) {
                    functionNameArgSet.add(functionDto.functionGroup + ":" + functionDto.functionName);
                }
            }
            featureFunctionNameMap.put(deviceFeatureDto.featureName, functionNameArgSet);
        }
        return featureFunctionNameMap;
    }

    @After
    public void after() {

    }

    private DeviceFeatureDto createData() {
        FeatureFunctionDtoBuilder dtoBuilder = new FeatureFunctionDtoBuilder();
        FeatureFunctionDto featureFunctionDto = dtoBuilder.setFunctionGroup("deviceGroup")
                .setFunctionName("setTemperature")
                .setFunctionType(FunctionType.SYNC)
                .setInputFunctionArgument(new FunctionArgumentDto(
                        "temperature",
                        DataType.FLOAT
                ))
                .setInputFunctionArgument(new FunctionArgumentDto(
                        "isopen",
                        DataType.BOOLEAN
                ))
                .instance();

        FeatureFunctionDto featureFunctionDto1 = dtoBuilder.setFunctionGroup("deviceGroup")
                .setFunctionName("turnOn")
                .setFunctionType(FunctionType.SYNC)
                .setInputFunctionArgument(new FunctionArgumentDto(
                        "temperature",
                        DataType.FLOAT

                ))
                .instance();

        FeatureFunctionDto featureFunctionDto2 = dtoBuilder.setFunctionGroup("deviceGroup")
                .setFunctionName("turnOff")
                .setFunctionType(FunctionType.SYNC)
                .setInputFunctionArgument(new FunctionArgumentDto(
                        "temperature",
                        DataType.FLOAT
                ))
                .instance();

        List<FeatureFunctionDto> featureFunctionSet = new ArrayList<>();
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