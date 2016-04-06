package com.anicloud.sunny.infrastructure.convert;

import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.ani.cel.service.manager.agent.device.model.DeviceSlaveInfoDto;
import com.ani.cel.service.manager.agent.device.model.FunctionInfoDto;
import com.anicloud.sunny.application.dto.device.ArgumentDto;
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
    public String generatorDeviceType(DeviceSlaveObjInfoDto slaveInfoDto) {
        return null;
    }
}
