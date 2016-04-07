package com.anicloud.sunny.application.service.device;

import com.ani.cel.service.manager.agent.core.share.DataType;
import com.ani.cel.service.manager.agent.core.share.FunctionType;
import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;
import com.anicloud.sunny.application.builder.DeviceFeatureDtoBuilder;
import com.anicloud.sunny.application.builder.FeatureFunctionDtoBuilder;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureArgDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.application.utils.NumGenerate;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ArgumentType;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ObjectState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.io.File;
import java.util.*;

/**
 * Created by zhaoyu on 15-6-15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/root-context.xml"
})
public class DeviceFeatureServiceHandlerTest {
    private static String FILE_PATH = "properties/DeviceFeatureConfig.json";
    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private DeviceFeatureService deviceFeatureService;
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
                        stringBuilder.append(functionDto.groupId)
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
                        stringBuilder.append(functionDto.groupId)
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
                    functionNameArgSet.add(functionDto.groupId + ":" + functionDto.functionName);
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
        List<FeatureArgDto> argDtoList = new ArrayList<>();
        argDtoList.add(new FeatureArgDto(DataType.FLOAT, "brightnessLux", "Bright Lux"));

        List<FeatureFunctionDto> featureFunctionDtoList = new ArrayList<>();

        // featureFunction
        List<FunctionArgumentDto> functionArgumentSet = new ArrayList<>();
        FunctionArgumentDto functionArgumentDto = new FunctionArgumentDto("brightnessLux", DataType.FLOAT);
        functionArgumentSet.add(functionArgumentDto);
        FeatureFunctionDto featureFunctionDto = new FeatureFunctionDtoBuilder()
                .setFunctionId(NumGenerate.generate())
                .setGroupId(1L)
                .setFunctionName("powerOn")
                .setFunctionType(FunctionType.SYNC)
                .setInputFunctionArgument(functionArgumentSet)
                .instance();

        featureFunctionDtoList.add(featureFunctionDto);

        List<Map<String, List<String>>> argFunctionArgMapList = new ArrayList<>();
        Map<String, List<String>> argFunctionArgMap = new HashMap<>();
        argFunctionArgMap.put("brightnessLux", Arrays.asList(featureFunctionDto.featureFunctionId + ":" + functionArgumentDto.name));
        argFunctionArgMapList.add(argFunctionArgMap);

        DeviceFeatureDtoBuilder dtoBuilder = new DeviceFeatureDtoBuilder();
        DeviceFeatureDto deviceFeatureDto = dtoBuilder
                .setFeatureId(NumGenerate.generate())
                .setFeatureName("Power On")
                .setDescription("light power on feature.")
                .setFeatureArg(argDtoList)
                .setFeatureFunction(featureFunctionDtoList)
                .setArgFunctionArgMap(argFunctionArgMap)
                .instance();

        return deviceFeatureDto;
    }

    public List<DeviceFeatureDto> createDataList() {
        //------------- one DeviceFeature --------------------------------------------
        List<FeatureArgDto> argDtoList = new ArrayList<>();
        argDtoList.add(new FeatureArgDto(DataType.FLOAT, "brightnessLux", "Bright Lux"));

        List<FeatureFunctionDto> featureFunctionDtoList = new ArrayList<>();

        // featureFunction
        List<FunctionArgumentDto> functionArgumentSet = new ArrayList<>();
        FunctionArgumentDto functionArgumentDto = new FunctionArgumentDto("brightnessLux", DataType.FLOAT);
        functionArgumentSet.add(functionArgumentDto);
        FeatureFunctionDto featureFunctionDto = new FeatureFunctionDtoBuilder()
                .setFunctionId(NumGenerate.generate())
                .setGroupId(1L)
                .setFunctionName("powerOn")
                .setFunctionType(FunctionType.SYNC)
                .setInputFunctionArgument(functionArgumentSet)
                .instance();

        featureFunctionDtoList.add(featureFunctionDto);

        List<Map<String, List<String>>> argFunctionArgMapList = new ArrayList<>();
        Map<String, List<String>> argFunctionArgMap = new HashMap<>();
        argFunctionArgMap.put("brightnessLux", Arrays.asList(featureFunctionDto.featureFunctionId + ":" + functionArgumentDto.name));
        argFunctionArgMapList.add(argFunctionArgMap);

        DeviceFeatureDtoBuilder dtoBuilder = new DeviceFeatureDtoBuilder();
        DeviceFeatureDto deviceFeatureDto = dtoBuilder
                .setFeatureId(NumGenerate.generate())
                .setFeatureName("Power On")
                .setDescription("light power on feature.")
                .setFeatureArg(argDtoList)
                .setFeatureFunction(featureFunctionDtoList)
                .setArgFunctionArgMap(argFunctionArgMap)
                .instance();
        //-----------------------------------------------------------------

        //------------- two DeviceFeature --------------------------------------------
        List<FeatureArgDto> argDtoList1 = new ArrayList<>();
        List<FeatureFunctionDto> featureFunctionDtoList1 = new ArrayList<>();

        // featureFunction
        List<FunctionArgumentDto> functionArgumentSet1 = new ArrayList<>();

        FeatureFunctionDto featureFunctionDto1 = new FeatureFunctionDtoBuilder()
                .setFunctionId(NumGenerate.generate())
                .setGroupId(1L)
                .setFunctionName("powerOff")
                .setFunctionType(FunctionType.SYNC)
                .setInputFunctionArgument(functionArgumentSet1)
                .instance();

        featureFunctionDtoList1.add(featureFunctionDto1);

        List<Map<String, List<String>>> argFunctionArgMapList1 = new ArrayList<>();

        DeviceFeatureDtoBuilder dtoBuilder1 = new DeviceFeatureDtoBuilder();
        DeviceFeatureDto deviceFeatureDto1 = dtoBuilder1
                .setFeatureId(NumGenerate.generate())
                .setFeatureName("Power Off")
                .setDescription("light power off feature.")
                .setFeatureArg(argDtoList1)
                .setFeatureFunction(featureFunctionDtoList1)
                .setArgFunctionArgMap(argFunctionArgMapList1)
                .instance();
        //-----------------------------------------------------------------

        //------------- three DeviceFeature --------------------------------------------
        List<FeatureArgDto> argDtoList2 = new ArrayList<>();
        argDtoList2.add(new FeatureArgDto(DataType.FLOAT, "degreesC", "degrees C"));

        List<FeatureFunctionDto> featureFunctionDtoList2 = new ArrayList<>();

        // featureFunction
        List<FunctionArgumentDto> functionArgumentSet2 = new ArrayList<>();
        FunctionArgumentDto functionArgumentDto2 = new FunctionArgumentDto("degreesC", DataType.FLOAT);
        functionArgumentSet2.add(functionArgumentDto2);
        FeatureFunctionDto featureFunctionDto2 = new FeatureFunctionDtoBuilder()
                .setFunctionId(NumGenerate.generate())
                .setGroupId(1L)
                .setFunctionName("powerOn")
                .setFunctionType(FunctionType.SYNC)
                .setInputFunctionArgument(functionArgumentSet2)
                .instance();

        featureFunctionDtoList2.add(featureFunctionDto2);

        List<Map<String, List<String>>> argFunctionArgMapList2 = new ArrayList<>();
        Map<String, List<String>> argFunctionArgMap2 = new HashMap<>();
        argFunctionArgMap2.put("degreesC", Arrays.asList(featureFunctionDto2.featureFunctionId + ":" + functionArgumentDto2.name));
        argFunctionArgMapList2.add(argFunctionArgMap2);

        DeviceFeatureDtoBuilder dtoBuilder2 = new DeviceFeatureDtoBuilder();
        DeviceFeatureDto deviceFeatureDto2 = dtoBuilder2
                .setFeatureId(NumGenerate.generate())
                .setFeatureName("#Air-Sensor--powerOn")
                .setDescription("Air Sensor power on feature.")
                .setFeatureArg(argDtoList2)
                .setFeatureFunction(featureFunctionDtoList2)
                .setArgFunctionArgMap(argFunctionArgMap2)
                .instance();
        //-----------------------------------------------------------------

        //------------- four DeviceFeature --------------------------------------------
        List<FeatureArgDto> argDtoList3 = new ArrayList<>();

        List<FeatureFunctionDto> featureFunctionDtoList3 = new ArrayList<>();

        // featureFunction
        List<FunctionArgumentDto> functionArgumentSet3 = new ArrayList<>();
        FeatureFunctionDto featureFunctionDto3 = new FeatureFunctionDtoBuilder()
                .setFunctionId(NumGenerate.generate())
                .setGroupId(1L)
                .setFunctionName("powerOff")
                .setFunctionType(FunctionType.SYNC)
                .setInputFunctionArgument(functionArgumentSet3)
                .instance();

        featureFunctionDtoList3.add(featureFunctionDto3);

        List<Map<String, List<String>>> argFunctionArgMapList3 = new ArrayList<>();

        DeviceFeatureDtoBuilder dtoBuilder3 = new DeviceFeatureDtoBuilder();
        DeviceFeatureDto deviceFeatureDto3 = dtoBuilder3
                .setFeatureId(NumGenerate.generate())
                .setFeatureName("Sensor--powerOff")
                .setDescription("Air Sensor power off feature.")
                .setFeatureArg(argDtoList3)
                .setFeatureFunction(featureFunctionDtoList3)
                .setArgFunctionArgMap(argFunctionArgMapList3)
                .instance();
        //-----------------------------------------------------------------

        //------------- five DeviceFeature --------------------------------------------
        List<FeatureArgDto> argDtoList4 = new ArrayList<>();
        argDtoList4.add(new FeatureArgDto(DataType.FLOAT, "degreesC", "degrees C"));

        List<FeatureFunctionDto> featureFunctionDtoList4 = new ArrayList<>();

        // featureFunction
        List<FunctionArgumentDto> functionArgumentSet4 = new ArrayList<>();
        FunctionArgumentDto functionArgumentDto4 = new FunctionArgumentDto("degreesC", DataType.FLOAT);
        functionArgumentSet4.add(functionArgumentDto4);
        FeatureFunctionDto featureFunctionDto4 = new FeatureFunctionDtoBuilder()
                .setFunctionId(NumGenerate.generate())
                .setGroupId(1L)
                .setFunctionName("getTemperature")
                .setFunctionType(FunctionType.SYNC)
                .setOutputFunctionArgument(functionArgumentSet4)
                .instance();

        featureFunctionDtoList4.add(featureFunctionDto4);

        List<Map<String, List<String>>> argFunctionArgMapList4 = new ArrayList<>();
        Map<String, List<String>> argFunctionArgMap4 = new HashMap<>();
        //argFunctionArgMap4.put("degreesC", Arrays.asList(featureFunctionDto4.featureFunctionId + ":" + functionArgumentDto4.name));
        //argFunctionArgMapList4.add(argFunctionArgMap4);

        DeviceFeatureDtoBuilder dtoBuilder4 = new DeviceFeatureDtoBuilder();
        DeviceFeatureDto deviceFeatureDto4 = dtoBuilder4
                .setFeatureId(NumGenerate.generate())
                .setFeatureName("Sensor--getTemperature")
                .setDescription("Air Sensor get Temperature feature.")
                .setFeatureArg(argDtoList4)
                .setFeatureFunction(featureFunctionDtoList4)
                .setArgFunctionArgMap(argFunctionArgMap4)
                .instance();
        //-----------------------------------------------------------------

        //------------- six DeviceFeature --------------------------------------------
        List<FeatureArgDto> argDtoList5 = new ArrayList<>();
        argDtoList5.add(new FeatureArgDto(DataType.FLOAT, "rhp", "rhp"));

        List<FeatureFunctionDto> featureFunctionDtoList5 = new ArrayList<>();
        // featureFunction
        List<FunctionArgumentDto> functionArgumentSet5 = new ArrayList<>();
        FunctionArgumentDto functionArgumentDto5 = new FunctionArgumentDto("rhp", DataType.FLOAT);
        functionArgumentSet5.add(functionArgumentDto5);
        FeatureFunctionDto featureFunctionDto5 = new FeatureFunctionDtoBuilder()
                .setFunctionId(NumGenerate.generate())
                .setGroupId(1L)
                .setFunctionName("getHumidity")
                .setFunctionType(FunctionType.SYNC)
                .setOutputFunctionArgument(functionArgumentSet5)
                .instance();

        featureFunctionDtoList5.add(featureFunctionDto5);

        List<Map<String, List<String>>> argFunctionArgMapList5 = new ArrayList<>();
        Map<String, List<String>> argFunctionArgMap5 = new HashMap<>();
        //argFunctionArgMap5.put("rhp", Arrays.asList(featureFunctionDto5.featureFunctionId + ":" + functionArgumentDto5.name));
        //argFunctionArgMapList5.add(argFunctionArgMap5);

        DeviceFeatureDtoBuilder dtoBuilder5 = new DeviceFeatureDtoBuilder();
        DeviceFeatureDto deviceFeatureDto5 = dtoBuilder5
                .setFeatureId(NumGenerate.generate())
                .setFeatureName("Sensor--getHumidity")
                .setDescription("Air Sensor get Humidity feature.")
                .setFeatureArg(argDtoList5)
                .setFeatureFunction(featureFunctionDtoList5)
                .setArgFunctionArgMap(argFunctionArgMap5)
                .instance();
        //-----------------------------------------------------------------

        ObjectMapper objectMapper = new ObjectMapper();
        List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>();
        deviceFeatureDtoList.add(deviceFeatureDto);
        deviceFeatureDtoList.add(deviceFeatureDto1);
        deviceFeatureDtoList.add(deviceFeatureDto2);
        deviceFeatureDtoList.add(deviceFeatureDto3);
        deviceFeatureDtoList.add(deviceFeatureDto4);
        deviceFeatureDtoList.add(deviceFeatureDto5);
        return deviceFeatureDtoList;
    }

    @Test
    public void testDeviceFeatureJson() {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        org.springframework.core.io.Resource resource = resourceLoader.getResource(FILE_PATH);
        try {
            File targetFile = resource.getFile();
            DeviceFeatureDto deviceFeatureDto = createData();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
            objectMapper.writeValue(targetFile, createDataList());
        } catch (Exception e) {

        }

    }
}