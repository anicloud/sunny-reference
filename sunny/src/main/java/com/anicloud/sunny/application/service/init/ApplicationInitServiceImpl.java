package com.anicloud.sunny.application.service.init;

import com.ani.cel.service.manager.agent.core.AnicelServiceConfig;
import com.ani.cel.service.manager.agent.device.model.DeviceMasterInfoDto;
import com.ani.cel.service.manager.agent.device.model.DeviceSlaveInfoDto;
import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;
import com.ani.cel.service.manager.agent.device.model.FunctionInfoDto;
import com.ani.cel.service.manager.agent.device.service.DeviceService;
import com.ani.cel.service.manager.agent.device.service.DeviceServiceImpl;
import com.ani.cel.service.manager.agent.oauth2.model.OAuth2AccessToken;
import com.anicloud.sunny.application.builder.DeviceAndFeatureRelationDtoBuilder;
import com.anicloud.sunny.application.builder.DeviceDtoBuilder;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.device.DeviceAndFeatureRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.device.DeviceAndFeatureRelationService;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.infrastructure.convert.DeviceInfoGeneratorService;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ArgumentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhaoyu on 15-7-11.
 */
@Service
@Transactional
public class ApplicationInitServiceImpl extends ApplicationInitService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationInitServiceImpl.class);

    @Resource
    private UserService userService;
    @Resource
    private DeviceFeatureService deviceFeatureService;
    @Resource
    private DeviceInfoGeneratorService deviceInfoGeneratorService;
    @Resource
    private com.anicloud.sunny.application.service.device.DeviceService sunnyDeviceService;
    @Resource
    private DeviceAndFeatureRelationService deviceAndFeatureRelationService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected UserDto initUser(UserDto userDto) {
        LOGGER.info("initUser {}.", userDto);
        return userService.saveUser(userDto);
    }

    @Override
    protected void initUserDeviceAndDeviceFeatureRelation(UserDto userDto, OAuth2AccessToken accessToken) {
        DeviceService deviceService = new DeviceServiceImpl(AnicelServiceConfig.getInstance());

        List<DeviceFeatureDto> deviceFeatureDtoList = null;
        //List<DeviceFeatureDto> deviceFeatureDtoList = deviceFeatureService.getAllDeviceFeature();
        Collection<DeviceMasterInfoDto> deviceMasterInfoDtoList = deviceService.getDeviceMasterInfoList(
                userDto.email, accessToken.getAccessToken());

        Map<String, Set<String>> featureFunctionMap = getFeatureFunctionMap(deviceFeatureDtoList);

        // keep the device name count
        Map<String, Integer> deviceNameCount = new HashMap<>();
        List<DeviceDto> deviceDtoList = new ArrayList<>();
        List<Map<String, Set<String>>> featureOfEachDeviceList = new ArrayList<>();
        for (DeviceMasterInfoDto deviceMasterInfoDto : deviceMasterInfoDtoList) {
            for (DeviceSlaveInfoDto slaveInfoDto : deviceMasterInfoDto.slaves) {

                Set<String> salveDeviceFunctionSet = getDeviceSlaveFunctionArgSet(slaveInfoDto);
                if (isDeviceInDeviceFeature(salveDeviceFunctionSet, featureFunctionMap)) {
                    DeviceDto deviceDto = toDeviceDto(deviceMasterInfoDto, slaveInfoDto, deviceNameCount, userDto);
                    Map<String, Set<String>> deviceAndFeatureRelationShip = getRelationOfDeviceAndFeature(
                            deviceDto, salveDeviceFunctionSet, featureFunctionMap);

                    deviceDtoList.add(deviceDto);
                    featureOfEachDeviceList.add(deviceAndFeatureRelationShip);
                }
                else {
                    break;
                }
            }
        }
        // convert
        List<DeviceAndFeatureRelationDto> relationDtoList = toDeviceAndFeatureRelationDtoList(featureOfEachDeviceList);
        sunnyDeviceService.batchSave(deviceDtoList);
        deviceAndFeatureRelationService.batchSave(relationDtoList);
        LOGGER.info("initUserDeviceAndDeviceFeatureRelation success.");
        try {
            String deviceMasterStr = objectMapper.writeValueAsString(deviceMasterInfoDtoList);
            String device = objectMapper.writeValueAsString(deviceDtoList);
            String deviceFeatureRelation = objectMapper.writeValueAsString(featureOfEachDeviceList);
            LOGGER.info("deviceMasterInfoDtoList {}.", deviceMasterStr);
            LOGGER.info("deviceDtoList {}.", device);
            LOGGER.info("deviceFeatureRelation {}.", deviceFeatureRelation);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected UserDto isUserNotExists(String hashUserId) {
        return userService.getUserByHashUserId(hashUserId);
    }

    private Map<String, Set<String>> getRelationOfDeviceAndFeature(DeviceDto deviceDto, Set<String> salveDeviceFunctionSet, Map<String, Set<String>> featureFunctionMap) {
        Map<String, Set<String>> relationMap = new HashMap<>();
        Set<String> featureNameSet = featureFunctionMap.keySet();
        Set<String> deviceRelatedFeature = new HashSet<>();
        for (String featureName : featureNameSet) {
            Set<String> functionSet = featureFunctionMap.get(featureName);
            Collection<String> resultSet = CollectionUtils.intersection(salveDeviceFunctionSet, functionSet);
            if (resultSet.size() > 0) {
                deviceRelatedFeature.add(featureName);
            }
        }
        relationMap.put(deviceDto.identificationCode, deviceRelatedFeature);
        return relationMap;
    }

    private DeviceDto toDeviceDto(DeviceMasterInfoDto deviceMasterInfoDto, DeviceSlaveInfoDto slaveInfoDto, Map<String, Integer> deviceNameCount, UserDto userDto) {
        DeviceDtoBuilder dtoBuilder = new DeviceDtoBuilder();
        String identificationCode = deviceMasterInfoDto.deviceId + "|" + slaveInfoDto.deviceId;
        String deviceType = deviceInfoGeneratorService.generatorDeviceType(slaveInfoDto);
        String deviceName = slaveInfoDto.name;

        if (deviceNameCount.containsKey(deviceName)) {
            Integer deviceNameNum = deviceNameCount.get(deviceName);
            deviceNameCount.put(deviceName, ++deviceNameNum);
        } else {
            deviceNameCount.put(deviceName, 1);
        }
        // deviceName = "#" +deviceName + deviceNameCount.get(deviceName);
        DeviceDto deviceDto = dtoBuilder
                .setDeviceName(deviceName)
                .setDeviceGroup(Constants.SUNNY_DEVICE_DEFAULT_GROUP)
                .setDeviceState(slaveInfoDto.deviceState)
                .setDeviceLogicState(Constants.DEVICE_DEFAULT_LOGIC_STATE)
                .setDeviceType(deviceType)
                .setIdentificationCode(identificationCode)
                .setOwner(userDto)
                .instance();

        return deviceDto;
    }

    private boolean isDeviceInDeviceFeature(Set<String> salveDeviceFunctionSet, Map<String, Set<String>> featureFunctionMap) {
        Set<String> featureNameSet = featureFunctionMap.keySet();
        for (String featureName : featureNameSet) {
            Set<String> functionSet = featureFunctionMap.get(featureName);
            Collection<String> resultSet = CollectionUtils.intersection(salveDeviceFunctionSet, functionSet);
            if (resultSet.size() > 0)
                return true;
        }
        return false;
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

    private Set<String> getDeviceSlaveFunctionArgSet(DeviceSlaveInfoDto slaveInfoDto) {
        Set<String> functionNameArgSet = new HashSet<>();
        for (FunctionInfoDto functionDto : slaveInfoDto.functions) {
            if (functionDto.inputArguments != null && functionDto.inputArguments.size() > 0) {
                for (FunctionArgumentDto functionArgumentDto : functionDto.inputArguments) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(functionDto.group.name)
                            .append(":")
                            .append(functionDto.name)
                            .append(":")
                            .append(ArgumentType.INPUT_ARGUMENT)
                            .append(":")
                            .append(functionArgumentDto.name);
                    functionNameArgSet.add(stringBuilder.toString());
                }
            }
            if (functionDto.outputArguments != null && functionDto.outputArguments.size() > 0) {
                for (FunctionArgumentDto functionArgumentDto : functionDto.outputArguments) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(functionDto.group.name)
                            .append(":")
                            .append(functionDto.name)
                            .append(":")
                            .append(ArgumentType.OUTPUT_ARGUMENT)
                            .append(":")
                            .append(functionArgumentDto.name);
                    functionNameArgSet.add(stringBuilder.toString());
                }
            }
            if ((functionDto.inputArguments.size() == 0 && functionDto.outputArguments.size() == 0)
                    || (functionDto.inputArguments == null && functionDto.outputArguments == null)) {
                functionNameArgSet.add(functionDto.group.name + ":" + functionDto.name);
            }
        }
        return functionNameArgSet;
    }

    private List<DeviceAndFeatureRelationDto> toDeviceAndFeatureRelationDtoList(List<Map<String, Set<String>>> relationList) {
        List<DeviceAndFeatureRelationDto> relationDtoList = new ArrayList<>();

        for (Map<String, Set<String>> relation : relationList) {
            Set<String> keySet = relation.keySet();
            for (String deviceId : keySet) {
                DeviceDto deviceDto = new DeviceDto(deviceId);
                List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>();
                Set<String> featureNameSet = relation.get(deviceId);
                for (String featureName : featureNameSet) {
                    DeviceFeatureDto deviceFeatureDto = new DeviceFeatureDto(featureName);
                    deviceFeatureDtoList.add(deviceFeatureDto);
                }

                DeviceAndFeatureRelationDtoBuilder builder = new DeviceAndFeatureRelationDtoBuilder();
                DeviceAndFeatureRelationDto relationDto = builder
                        .setDevice(deviceDto)
                        .setRelateDeviceFeature(deviceFeatureDtoList)
                        .instance();

                relationDtoList.add(relationDto);
            }
        }
        return relationDtoList;
    }
}
