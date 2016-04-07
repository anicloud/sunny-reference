package com.anicloud.sunny.application.service.init;

import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.ani.octopus.commons.object.dto.object.ObjectSlaveInfoDto;
import com.ani.octopus.commons.stub.dto.StubDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhaoyu on 15-7-1.
 */
@Service
public class DeviceInfoGeneratorServiceImpl extends DeviceInfoGeneratorService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeviceInfoGeneratorServiceImpl.class);
    private final static String FILE_PATH = "properties/DeviceTypeRule.json";

    @Resource
    private ObjectMapper objectMapper;

    @Override
    @PostConstruct
    public void initDeviceTypeGeneratorRule() {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        org.springframework.core.io.Resource resource = resourceLoader.getResource(FILE_PATH);
        try {
            File targetFile = resource.getFile();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
            Map<String, Set<StubIdentity>> typeRule = objectMapper.readValue(targetFile, Map.class);
            super.deviceTypeRule = typeRule;
        } catch (IOException e) {
            LOGGER.error("read device type generate rule error. {}", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String generatorDeviceType(ObjectSlaveInfoDto slaveInfoDto) {
        Set<StubIdentity> identitySet = fetchDeviceStubSet(slaveInfoDto);
        Set<String> keySet = super.deviceTypeRule.keySet();
        String deviceType = "";
        double percentage = 0.0;
        for (String key : keySet) {
            Set<StubIdentity> valueSet = super.deviceTypeRule.get(key);
            Collection<StubIdentity> intersectionSet = CollectionUtils.intersection(keySet, valueSet);
            double tempPercentage = identitySet.size() / Double.valueOf(valueSet.size());
            // when percentage is 1.0
            if (tempPercentage == 1.0) {
                return key;
            } else {
                if (tempPercentage > percentage) {
                    percentage = tempPercentage;
                    deviceType = key;
                }
            }
        }
        return deviceType;
    }

    private Set<StubIdentity> fetchDeviceStubSet(ObjectSlaveInfoDto slaveObjInfoDto) {
        if (slaveObjInfoDto == null) {
            throw new IllegalArgumentException("slaveObjInfoDto is null.");
        }
        Set<StubIdentity> stubIdentitySet = new HashSet<>();
        for (StubDto stubDto : slaveObjInfoDto.stubs) {
            StubIdentity stubIdentity = new StubIdentity(
                    stubDto.stubId,
                    stubDto.stubGroupId
            );
            stubIdentitySet.add(stubIdentity);
        }
        return stubIdentitySet;
    }
}
