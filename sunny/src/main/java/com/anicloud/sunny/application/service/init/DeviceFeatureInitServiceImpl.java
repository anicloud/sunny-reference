package com.anicloud.sunny.application.service.init;

import com.ani.cel.service.manager.agent.core.share.DataType;
import com.ani.cel.service.manager.agent.core.share.FunctionType;
import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;
import com.anicloud.sunny.application.builder.DeviceFeatureDtoBuilder;
import com.anicloud.sunny.application.builder.FeatureFunctionDtoBuilder;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureArgDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.utils.NumGenerate;
import com.anicloud.sunny.domain.model.device.FunctionArgument;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhaoyu on 15-7-10.
 */
@Service
@Transactional
public class DeviceFeatureInitServiceImpl extends DeviceFeatureInitService {

    @Resource
    private DeviceFeatureService deviceFeatureService;

    @Override
    public void initDeviceFeature() {

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
                .setFunctionGroup("Light")
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
                .setFeatureName("#Bathroom-Light--powerOn")
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
                .setFunctionGroup("Light")
                .setFunctionName("powerOff")
                .setFunctionType(FunctionType.SYNC)
                .setInputFunctionArgument(functionArgumentSet1)
                .instance();

        featureFunctionDtoList1.add(featureFunctionDto1);

        List<Map<String, List<String>>> argFunctionArgMapList1 = new ArrayList<>();

        DeviceFeatureDtoBuilder dtoBuilder1 = new DeviceFeatureDtoBuilder();
        DeviceFeatureDto deviceFeatureDto1 = dtoBuilder1
                .setFeatureId(NumGenerate.generate())
                .setFeatureName("#Bathroom-Light--powerOff")
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
                .setFunctionGroup("Air")
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
                .setFunctionGroup("Air")
                .setFunctionName("powerOff")
                .setFunctionType(FunctionType.SYNC)
                .setInputFunctionArgument(functionArgumentSet3)
                .instance();

        featureFunctionDtoList3.add(featureFunctionDto3);

        List<Map<String, List<String>>> argFunctionArgMapList3 = new ArrayList<>();

        DeviceFeatureDtoBuilder dtoBuilder3 = new DeviceFeatureDtoBuilder();
        DeviceFeatureDto deviceFeatureDto3 = dtoBuilder3
                .setFeatureId(NumGenerate.generate())
                .setFeatureName("#Air-Sensor--powerOff")
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
                .setFunctionGroup("Air")
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
                .setFeatureName("#Air-Sensor--getTemperature")
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
                .setFunctionGroup("Air")
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
                .setFeatureName("#Air-Sensor--getHumidity")
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
        try {
            System.out.println(objectMapper.writeValueAsString(deviceFeatureDtoList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        super.deviceFeatureDtoList = deviceFeatureDtoList;
        deviceFeatureService.batchSaveDeviceFeature(deviceFeatureDtoList);
    }

    @Override
    public List<DeviceFeatureDto> getAll() {
        return super.deviceFeatureDtoList;
    }


}
