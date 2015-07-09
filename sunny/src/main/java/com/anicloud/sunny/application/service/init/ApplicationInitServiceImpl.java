package com.anicloud.sunny.application.service.init;

import com.ani.cel.service.manager.agent.core.AnicelServiceConfig;
import com.ani.cel.service.manager.agent.device.model.DeviceMasterInfoDto;
import com.ani.cel.service.manager.agent.device.model.DeviceSlaveInfoDto;
import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;
import com.ani.cel.service.manager.agent.device.model.FunctionInfoDto;
import com.ani.cel.service.manager.agent.device.service.DeviceService;
import com.ani.cel.service.manager.agent.device.service.DeviceServiceImpl;
import com.anicloud.sunny.application.builder.DeviceAndFeatureRelationDtoBuilder;
import com.anicloud.sunny.application.builder.DeviceDtoBuilder;
import com.anicloud.sunny.application.builder.DeviceFeatureDtoBuilder;
import com.anicloud.sunny.application.builder.FeatureFunctionDtoBuilder;
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
    private static final String RETURN_RELATION_OF_DEVICE_AND_FEATURE = "relation_of_device_and_feature";

    @Resource
    private UserService userService;
    @Resource
    private DeviceInfoGeneratorService deviceInfoGeneratorService;
    @Resource
    private com.anicloud.sunny.application.service.device.DeviceService sunnyDeviceService;
    @Resource
    private DeviceFeatureService deviceFeatureService;
    @Resource
    private DeviceAndFeatureRelationService deviceAndFeatureRelationService;


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
        List<DeviceFeatureDto> deviceFeatureDtoList =
                filterRepeatByDeviceFeatureName(returnMap.get(RETURN_DEVICE_FEATURE_LIST));
        //to dto list
        List<Map<String, Set<String>>> featureRelation = returnMap.get(RETURN_RELATION_OF_DEVICE_AND_FEATURE);
        List<DeviceAndFeatureRelationDto> relationDtoList = toDeviceAndFeatureRelationDtoList(featureRelation);

        sunnyDeviceService.batchSave(deviceDtoList);
        deviceFeatureService.batchSaveDeviceFeature(deviceFeatureDtoList);
        deviceAndFeatureRelationService.batchSave(relationDtoList);

        LOGGER.info("init user's devices and device feature success.");
        try {
            String dtoString = objectMapper.writeValueAsString(deviceDtoList);
            LOGGER.info("deviceDtoList {}", dtoString);
            String result = objectMapper.writeValueAsString(deviceFeatureDtoList);
            LOGGER.info("deviceFeatureDtoList {}.", result);
            String relationString = objectMapper.writeValueAsString(featureRelation);
            LOGGER.info("featureRelation {}.", relationString);
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
        // keep the relationship between device and device feature
        List<Map<String, Set<String>>> featureOfEachDeviceList = new ArrayList<>();
        List<DeviceDto> deviceDtoList = new ArrayList<>();
        List<DeviceFeatureDto> allDeviceFeatureDtoList = new ArrayList<>();

        for (DeviceMasterInfoDto deviceMasterInfoDto : dtoCollection) {
            for (DeviceSlaveInfoDto slaveInfoDto : deviceMasterInfoDto.slaves) {
                // get deviceDto
                DeviceDto deviceDto = toDeviceDto(deviceMasterInfoDto, slaveInfoDto, featureNameCount);
                // get DeviceFeature List
                List<DeviceFeatureDto> deviceFeatureDtoList = toDeviceFeatureDtoList(slaveInfoDto);
                // get the relationship of device and device feature
                Map<String, Set<String>> relationshipMap = getRelationOfDeviceAndFeature(deviceDto, deviceFeatureDtoList);

                deviceDtoList.add(deviceDto);
                allDeviceFeatureDtoList.addAll(deviceFeatureDtoList);
                featureOfEachDeviceList.add(relationshipMap);
            }
        }

        returnMap.put(RETURN_DEVICE_LIST, deviceDtoList);
        returnMap.put(RETURN_DEVICE_FEATURE_LIST, allDeviceFeatureDtoList);
        returnMap.put(RETURN_RELATION_OF_DEVICE_AND_FEATURE, featureOfEachDeviceList);
        return returnMap;
    }

    private List<DeviceFeatureDto> toDeviceFeatureDtoList(DeviceSlaveInfoDto slaveInfoDto) {
        List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>();

        Map<String, List<FunctionInfoDto>> deviceFeatureNameMap = deviceInfoGeneratorService.generateDeviceFeatureSet(slaveInfoDto);
        Set<String> featureNameSet = deviceFeatureNameMap.keySet();
        for (String featureName : featureNameSet) {
            List<FeatureFunctionDto> functionDtoList = new ArrayList<>();
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

            String deviceFeatureName = "#" + slaveInfoDto.name + "--" + featureName;
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
                .setDeviceLogicState(Constants.DEVICE_DEFAULT_LOGIC_STATE)
                .setDeviceType(deviceType)
                .setIdentificationCode(identificationCode)
                .setOwner(super.userDto)
                .instance();

        return deviceDto;
    }

    /**
     * keep the relationship between device and device feature
     * @param deviceDto
     * @param deviceFeatureDtoList
     * @return
     */
    private Map<String, Set<String>> getRelationOfDeviceAndFeature(DeviceDto deviceDto, List<DeviceFeatureDto> deviceFeatureDtoList) {
        // key is the identification code of device
        Map<String, Set<String>> setMap = new HashMap<>();
        // feature name set
        Set<String> featureNameSet = new HashSet<>();
        for (DeviceFeatureDto deviceFeatureDto : deviceFeatureDtoList) {
            featureNameSet.add(deviceFeatureDto.featureName);
        }
        setMap.put(deviceDto.identificationCode, featureNameSet);
        return setMap;
    }

    /**
     * remove the repeat device feature
     * @return
     */
    private List<DeviceFeatureDto> filterRepeatByDeviceFeatureName(List<DeviceFeatureDto> deviceFeatureDtoList) {
        Set<String> deviceFeatureNameSet = new HashSet<>();
        List<DeviceFeatureDto> returnDeviceFeatureList = new ArrayList<>();
        for (DeviceFeatureDto deviceFeatureDto : deviceFeatureDtoList) {
            String featureName = deviceFeatureDto.featureName;
            if (deviceFeatureNameSet.contains(featureName)) {
                break;
            } else {
                returnDeviceFeatureList.add(deviceFeatureDto);
                deviceFeatureNameSet.add(featureName);
            }
        }
        return returnDeviceFeatureList;
    }

    public List<DeviceAndFeatureRelationDto> toDeviceAndFeatureRelationDtoList(List<Map<String, Set<String>>> relationList) {
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
