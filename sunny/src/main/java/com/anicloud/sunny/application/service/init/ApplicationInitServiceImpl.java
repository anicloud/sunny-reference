package com.anicloud.sunny.application.service.init;

import com.ani.cel.service.manager.agent.core.AnicelServiceConfig;
import com.ani.cel.service.manager.agent.device.model.DeviceMasterInfoDto;
import com.ani.cel.service.manager.agent.device.model.DeviceSlaveInfoDto;
import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;
import com.ani.cel.service.manager.agent.device.model.FunctionInfoDto;
import com.ani.cel.service.manager.agent.device.service.DeviceService;
import com.ani.cel.service.manager.agent.device.service.DeviceServiceImpl;
import com.anicloud.sunny.application.builder.DeviceDtoBuilder;
import com.anicloud.sunny.application.builder.DeviceFeatureDtoBuilder;
import com.anicloud.sunny.application.builder.FeatureFunctionDtoBuilder;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.infrastructure.convert.DeviceInfoGeneratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhaoyu on 15-6-27.
 */
@Service
@Scope(value = "prototype")
@Transactional
public class ApplicationInitServiceImpl extends ApplicationInitService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationInitServiceImpl.class);

    private static final String RETURN_DEVICE_LIST = "device_list";
    private static final String RETURN_DEVICE_FEATURE_LIST = "device_feature_list";

    @Resource
    private UserService userService;
    @Resource
    private DeviceInfoGeneratorService deviceInfoGeneratorService;
    @Resource
    private com.anicloud.sunny.application.service.device.DeviceService sunnyDeviceService;
    @Resource
    private DeviceFeatureService deviceFeatureService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Collection<DeviceMasterInfoDto> dtoCollection;

    @Override
    protected void initUser(UserDto userDto) {
        super.userDto = userService.saveUser(userDto);
        LOGGER.info("initUser {}.", userDto);
    }

    @Override
    protected void initUserDevicesAndDeviceFeatures() {
        DeviceService agentDeviceService = new DeviceServiceImpl(AnicelServiceConfig.getInstance());
        dtoCollection =  agentDeviceService.getDeviceMasterInfoList(this.userDto.email, this.accessToken.getAccessToken());

        Map<String, List> returnMap = toDeviceDtoAndDeviceFeatureList(dtoCollection);
        List<DeviceDto> deviceDtoList = returnMap.get(RETURN_DEVICE_LIST);
        List<DeviceFeatureDto> deviceFeatureDtoList = returnMap.get(RETURN_DEVICE_FEATURE_LIST);

        sunnyDeviceService.batchSave(deviceDtoList);
        deviceFeatureService.batchSaveDeviceFeature(deviceFeatureDtoList);

        LOGGER.info("init user's devices and device feature success.");
        try {
            String dtoString = objectMapper.writeValueAsString(deviceDtoList);
            LOGGER.info("deviceDtoList {}", dtoString);
            String result = objectMapper.writeValueAsString(deviceFeatureDtoList);
            LOGGER.info("deviceFeatureDtoList {}.", result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean isUserNotExists(String hashUserId) {
        UserDto userDto = userService.getUserByHashUserId(hashUserId);
        // init super class userDto
        super.userDto = userDto;
        return userDto == null;
    }

    private Map<String, List> toDeviceDtoAndDeviceFeatureList(Collection<DeviceMasterInfoDto> dtoCollection) {
        // return map
        Map<String, List> returnMap = new HashMap<>();
        Set<String> deviceFeatureNameExistSet = new HashSet<>();
        Map<String, Integer> featureNameCount = new HashMap<>();

        List<DeviceDto> deviceDtoList = new ArrayList<>();
        List<DeviceFeatureDto> allDeviceFeatureDtoList = new ArrayList<>();

        for (DeviceMasterInfoDto deviceMasterInfoDto : dtoCollection) {
            for (DeviceSlaveInfoDto slaveInfoDto : deviceMasterInfoDto.slaves) {
                // get deviceDto
                DeviceDto deviceDto = toDeviceDto(deviceMasterInfoDto, slaveInfoDto, featureNameCount);
                List<DeviceFeatureDto> deviceFeatureDtoList = toDeviceFeatureDtoList(slaveInfoDto, deviceFeatureNameExistSet);
                // get DeviceFeature List
                deviceDtoList.add(deviceDto);
                allDeviceFeatureDtoList.addAll(deviceFeatureDtoList);
            }
        }

        returnMap.put(RETURN_DEVICE_LIST, deviceDtoList);
        returnMap.put(RETURN_DEVICE_FEATURE_LIST, allDeviceFeatureDtoList);
        return returnMap;
    }

    private List<DeviceFeatureDto> toDeviceFeatureDtoList(DeviceSlaveInfoDto slaveInfoDto, Set<String> deviceFeatureNameExistSet) {
        List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>();

        Map<String, List<FunctionInfoDto>> deviceFeatureNameMap = deviceInfoGeneratorService.generateDeviceFeatureSet(slaveInfoDto);
        Set<String> featureNameSet = deviceFeatureNameMap.keySet();
        for (String featureName : featureNameSet) {
            String deviceFeatureName = "#" + slaveInfoDto.name + "--" + featureName;
            if (deviceFeatureNameExistSet.contains(deviceFeatureName)) {
                continue;
            } else {
                deviceFeatureNameExistSet.add(deviceFeatureName);
            }

            Set<FeatureFunctionDto> functionDtoList = new HashSet<>();
            List<FunctionInfoDto> funcList = deviceFeatureNameMap.get(featureName);
            int sequenceNum = 0;
            for (FunctionInfoDto infoDto : funcList) {
                FeatureFunctionDtoBuilder featureFunctionDtoBuilder = new FeatureFunctionDtoBuilder()
                        .setFunctionName(infoDto.name)
                        .setFunctionGroup(infoDto.group.name)
                        .setFunctionType(infoDto.functionType)
                        .setFunctionArgument(new HashSet<FunctionArgumentDto>(infoDto.inputArguments))
                        .setSequenceNum(++sequenceNum);
                functionDtoList.add(featureFunctionDtoBuilder.instance());
            }

            DeviceFeatureDtoBuilder builder = new DeviceFeatureDtoBuilder()
                    .setFeatureName(deviceFeatureName)
                    .setDescription(slaveInfoDto.description)
                    .setFeatureFunction(functionDtoList);

            deviceFeatureDtoList.add(builder.instance());
        }
        return deviceFeatureDtoList;
    }

    private DeviceDto toDeviceDto(DeviceMasterInfoDto deviceMasterInfoDto, DeviceSlaveInfoDto slaveInfoDto, Map<String, Integer> deviceNameCount) {
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
        deviceName = "#" +deviceName + deviceNameCount.get(deviceName);

        DeviceDto deviceDto = dtoBuilder
                .setDeviceName(deviceName)
                .setDeviceGroup(Constants.SUNNY_DEVICE_DEFAULT_GROUP)
                .setDeviceState(slaveInfoDto.deviceState)
                .setDeviceType(deviceType)
                .setIdentificationCode(identificationCode)
                .setOwner(super.userDto)
                .instance();

        return deviceDto;
    }
}
