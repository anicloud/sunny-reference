package com.anicloud.sunny.infrastructure.convert;

import com.ani.cel.service.manager.agent.device.model.DeviceSlaveInfoDto;
import com.ani.cel.service.manager.agent.device.model.FunctionInfoDto;
import com.anicloud.sunny.infrastructure.exception.DeviceTypeCanNotGenerateException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhaoyu on 15-7-1.
 */
@Service
public class DeviceInfoGeneratorServiceImpl extends DeviceInfoGeneratorService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceInfoGeneratorServiceImpl.class);

    @Override
    public void initDeviceTypeGeneratorRule() {
        super.deviceTypeGeneratorRule = new HashMap<>();
        Set<String> funNameSet0 = new HashSet<>();
        funNameSet0.add("powerOn");
        funNameSet0.add("powerOff");
        super.deviceTypeGeneratorRule.put(funNameSet0, "Light");

        Set<String> funNameSet1 = new HashSet<>();
        funNameSet1.add("powerOn");
        funNameSet1.add("powerOff");
        funNameSet1.add("getTemperature");
        funNameSet1.add("getHumidity");
        super.deviceTypeGeneratorRule.put(funNameSet1, "Air-Sensor");

    }

    @Override
    public void initDeviceFeatureGeneratorRule() {
        super.deviceFeatureGeneratorRule = new HashMap<>();

        Set<String> featureFuncSet0 = new HashSet<>();
        featureFuncSet0.add("powerOn");
        super.deviceFeatureGeneratorRule.put(featureFuncSet0, "powerOn");

        Set<String> featureFuncSet1 = new HashSet<>();
        featureFuncSet1.add("powerOff");
        super.deviceFeatureGeneratorRule.put(featureFuncSet1, "powerOff");

        Set<String> featureFuncSet2 = new HashSet<>();
        featureFuncSet2.add("getTemperature");
        super.deviceFeatureGeneratorRule.put(featureFuncSet2, "getTemperature");

        Set<String> featureFuncSet3 = new HashSet<>();
        featureFuncSet3.add("getHumidity");
        super.deviceFeatureGeneratorRule.put(featureFuncSet3, "getHumidity");

    }

    @Override
    public String generatorDeviceType(DeviceSlaveInfoDto slaveInfoDto) {
        String deviceType = "";
        Set<String> funcSet = getDeviceSlaveFunctionSet(slaveInfoDto);
        deviceType = generateTypeFromRule(funcSet);
        if (StringUtils.isEmpty(deviceType)) {
            if (StringUtils.isNotEmpty(slaveInfoDto.name)) {
                deviceType = slaveInfoDto.name;
            }
            else if (slaveInfoDto.functions!= null && slaveInfoDto.functions.get(0) != null) {
                deviceType = slaveInfoDto.functions.get(0).group.name;
            }
        }
        return deviceType;
    }

    @Override
    public Map<String, List<FunctionInfoDto>> generateDeviceFeatureSet(DeviceSlaveInfoDto slaveInfoDto) {
        Map<String, List<FunctionInfoDto>> deviceFeatureMap = new HashMap<>();

        Set<String> deviceFunctionSet = getDeviceSlaveFunctionSet(slaveInfoDto);
        Collection<Set<String>> deviceFeatureKeySet = super.deviceFeatureGeneratorRule.keySet();
        for (Set<String> eachKeySet : deviceFeatureKeySet) {
            if (deviceFunctionSet.containsAll(eachKeySet)) {
                List<FunctionInfoDto> featureFunctionInfoDtoList = getDeviceFeatureFunctionInfoList(slaveInfoDto, eachKeySet);
                deviceFeatureMap.put(super.deviceFeatureGeneratorRule.get(eachKeySet), featureFunctionInfoDtoList);
            }
        }
        return deviceFeatureMap;
    }

    private List<FunctionInfoDto> getDeviceFeatureFunctionInfoList(DeviceSlaveInfoDto slaveInfoDto, Set<String> eachKeySet) {
        List<FunctionInfoDto> infoDtoList = new ArrayList<>();
        for (String functionName : eachKeySet) {
            for (FunctionInfoDto functionInfoDto : slaveInfoDto.functions) {
                if (functionInfoDto.name.equals(functionName)) {
                    infoDtoList.add(functionInfoDto);
                    continue;
                }
            }
        }
        return infoDtoList;
    }

    private String generateTypeFromRule(Set<String> funcSet) {
        String deviceType = null;
        int maxIntersectionSize = 0;
        int indexOfMaxIntersection = -1;
        Set<String> maxIntersectionFuncSet = null;

        Collection<Set<String>> setCollection = super.deviceTypeGeneratorRule.keySet();
        int index = 0;
        for (Set<String> eachFuncSet : setCollection) {
            Collection<String> resultSet = CollectionUtils.intersection(eachFuncSet, funcSet);
            int size = resultSet.size();
            // if equals, it is the type
            if (size == funcSet.size() && eachFuncSet.size() == funcSet.size()) {
                maxIntersectionFuncSet = eachFuncSet;
                break;
            }
            if (size > maxIntersectionSize) {
                maxIntersectionSize = size;
                indexOfMaxIntersection = index;
                maxIntersectionFuncSet = eachFuncSet;
            }
            index++;
        }

        if (maxIntersectionFuncSet == null) {
            return deviceType;
        }

        deviceType = super.deviceTypeGeneratorRule.get(maxIntersectionFuncSet);
        return deviceType;
    }

    private Set<String> getDeviceSlaveFunctionSet(DeviceSlaveInfoDto slaveInfoDto) {
        Set<String> funcSet = new HashSet<>();
        if (slaveInfoDto == null) {
            return funcSet;
        }
        for (FunctionInfoDto functionInfoDto : slaveInfoDto.functions) {
            funcSet.add(functionInfoDto.name);
        }
        return funcSet;
    }
}
