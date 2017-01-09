package com.anicloud.sunny.application.service.init;

import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.ani.agent.service.service.AgentTemplate;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.ani.earth.commons.dto.AccountDto;
import com.ani.octopus.commons.object.enumeration.AniObjectState;
import com.ani.octopus.commons.stub.dto.StubInfoDto;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.device.*;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.device.DeviceAndFeatureRelationService;
import com.anicloud.sunny.application.service.device.DeviceAndUserRelationServcie;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.domain.model.device.Device;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceLogicState;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.Access;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhaoyu on 15-7-11.
 */
@Service
@Transactional
public class ApplicationInitServiceImpl extends ApplicationInitService {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(ApplicationInitServiceImpl.class);

    @Resource
    private UserService userService;
    @Resource
    private DeviceFeatureService deviceFeatureService;
    @Resource
    private DeviceInfoGeneratorService deviceInfoGeneratorService;
    @Resource
    private DeviceAndFeatureRelationService deviceAndFeatureRelationService;
    @Resource
    private DeviceAndUserRelationServcie deviceAndUserRelationServcie;
    @Resource(name = "agentTemplate")
    private AgentTemplate agentTemplate;

    private static List<DeviceFeatureDto> deviceFeatureDtos;

    @PostConstruct
    protected void getAllDeviceFeature() {
        deviceFeatureDtos = deviceFeatureService.getAllDeviceFeature();
    }

    @Override
    protected UserDto initUser(UserDto userDto) {
        LOGGER.info("initUser {}.", userDto);
        return userService.saveUser(userDto);
    }

    @Override
    protected void initUserDeviceAndDeviceFeatureRelation(UserDto userDto,List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtoList) throws Exception {
        LOGGER.info("initUserDeviceAndDeviceFeatureRelation");
        if(deviceMasterObjInfoDtoList!=null){
            LOGGER.info("deviceMasterObjInfoDtoList size is "+deviceMasterObjInfoDtoList.size());
            List<DeviceAndFeatureRelationDto> deviceAndFeatureRelationDtos =
                    getRelation(deviceMasterObjInfoDtoList);
            LOGGER.info("Initialize DeviceAndFeatureRelation...");
            deviceAndFeatureRelationService.batchSave(deviceAndFeatureRelationDtos);
            List<DeviceAndUserRelationDto> deviceAndUserRelationDtoList =
                    getDeviceAndUserRelations(deviceAndFeatureRelationDtos, userDto);
            deviceAndUserRelationServcie.batchSave(deviceAndUserRelationDtoList);
        }
    }

    @Override
    public void updateUserDeviceAndDeviceFeatureRelation(DeviceMasterObjInfoDto masterDto) {
        List<DeviceAndFeatureRelationDto> deviceAndFeatureRelationDtos = getRalation(masterDto);
        DeviceAndFeatureRelationDto relationDto = deviceAndFeatureRelationService.findByDeviceIdentificationCode(buildId(masterDto.objectId,-1));
        if(relationDto == null) {
            deviceAndFeatureRelationService.batchSave(deviceAndFeatureRelationDtos);
        } else {
            deviceAndFeatureRelationService.batchModify(deviceAndFeatureRelationDtos);
        }
    }

    @Override
    protected boolean isUserNotExists(Long accountId) {
        // return userService.getUserByHashUserId(hashUserId);
        UserDto userDto = userService.getUserByHashUserId(accountId);
        return userDto == null;
    }

    @Override
    public UserDto initApplication(AniOAuthAccessToken accessToken) throws Exception {
        AccountDto accountDto = agentTemplate
                .getAccountService(accessToken.getAccessToken())
                .getByAccessToken();
        UserDto userDto = fetchUserInfo(accountDto, accessToken);
        // not exists
        List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtoList = agentTemplate
                .getDeviceObjService(accessToken.getAccessToken())
                .getAccessibleDeviceObjInfoList(userDto.hashUserId, Boolean.TRUE);
        if(deviceMasterObjInfoDtoList != null && deviceMasterObjInfoDtoList.size() >0) {
            for(DeviceMasterObjInfoDto deviceMasterObjInfoDto:deviceMasterObjInfoDtoList) {
                List<Integer> slaveIds = new ArrayList<>();
                if(deviceMasterObjInfoDto.slaves != null && deviceMasterObjInfoDto.slaves.size()>0) {
                    slaveIds.addAll(deviceMasterObjInfoDto.slaves.stream().
                            map(deviceSlaveObjInfoDto -> deviceSlaveObjInfoDto.objectSlaveId).collect(Collectors.toList()));
                }
                Constants.DEVICE_ID_RELATION_MAP.put(deviceMasterObjInfoDto.objectId,slaveIds);
            }
        }
        if (isUserNotExists(accountDto.accountId)) {
            // init user
            initUser(userDto);
            // init user-device-devicefeature relation
            initUserDeviceAndDeviceFeatureRelation(userDto, deviceMasterObjInfoDtoList);

        }else{
            initUser(userDto);
        }
        return userDto;
    }

    protected UserDto fetchUserInfo(AccountDto accountDto, AniOAuthAccessToken accessToken) {
        if(accessToken != null) {
            return new UserDto(
                    accessToken.getAccessToken(),
                    accountDto.email,
                    accessToken.getExpiresIn(),
                    accountDto.accountId,
                    accessToken.getRefreshToken(),
                    accessToken.getScope(),
                    accountDto.screenName,
                    accessToken.getTokenType(),
                    getCurrentTime()
            );
        } else {
            return userService.getUserByHashUserId(accountDto.accountId);
        }
    }

    public List<DeviceAndUserRelationDto> getDeviceAndUserRelations(List<DeviceAndFeatureRelationDto> deviceAndFeatureRelationDtos,UserDto userDto){
        List<DeviceAndUserRelationDto> relations = new ArrayList<>(deviceAndFeatureRelationDtos.size());
        for (DeviceAndFeatureRelationDto featureRelationDto : deviceAndFeatureRelationDtos) {
            DeviceAndUserRelationDto relationDto = new DeviceAndUserRelationDto(featureRelationDto.
                    deviceDto,userDto,initDefaultParam(),featureRelationDto.deviceDto.name,"default");
            relations.add(relationDto);
        }
        return relations;
    }

    private String initDefaultParam() {
        //返回一个空json对象
        return "{}";
    }

    public List<DeviceAndFeatureRelationDto> getRelation(
            List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtoList) throws Exception {
        List<DeviceAndFeatureRelationDto> deviceAndFeatureDtos = new ArrayList<>();
        for (DeviceMasterObjInfoDto masterDto : deviceMasterObjInfoDtoList) {
            String masterType = deviceInfoGeneratorService.generatorDeviceType(masterDto.stubs);
            DeviceDto masterDeviceDto = new DeviceDto(
                    "default",
                    convert(masterDto.state),
                    masterType,
                    buildId(masterDto.objectId,-1),
                    masterDto.name,
                    masterDto.owner.accountId,
                    DeviceLogicState.OPEN,
                    deviceInfoGeneratorService.getDeviceLogoUrl(masterType)
            );
            if (masterDeviceDto.deviceState == null)
                continue;
            DeviceAndFeatureRelationDto masterDeviceAndFeatureDto =
                    new DeviceAndFeatureRelationDto(
                            masterDeviceDto,
                            buildDeviceFeatureByStubDto(masterDto.stubs)
                    );
            deviceAndFeatureDtos.add(masterDeviceAndFeatureDto);

            for (DeviceSlaveObjInfoDto objDto : masterDto.slaves) {
                String deviceType = deviceInfoGeneratorService.generatorDeviceType(objDto.stubs);
                DeviceDto deviceDto = new DeviceDto(
                        "default",
                        convert(objDto.state),
                        deviceType,
                        buildId(masterDto.objectId,objDto.objectSlaveId),
                        objDto.name,
                        masterDto.owner.accountId,
                        DeviceLogicState.OPEN,
                        deviceInfoGeneratorService.getDeviceLogoUrl(deviceType)
                );
                if(deviceDto.deviceState == null)
                    continue;
                DeviceAndFeatureRelationDto deviceAndFeatureDto =
                        new DeviceAndFeatureRelationDto(
                                deviceDto,
                                buildDeviceFeatureByStubDto(objDto.stubs)
                        );
                deviceAndFeatureDtos.add(deviceAndFeatureDto);
            }
        }
        return deviceAndFeatureDtos;
    }

    public List<DeviceAndFeatureRelationDto> getRalation(DeviceMasterObjInfoDto masterDto) {
        List<DeviceAndFeatureRelationDto> deviceAndFeatureDtos = new ArrayList<>();
        String masterType = deviceInfoGeneratorService.generatorDeviceType(masterDto.stubs);
        DeviceDto masterDeviceDto = new DeviceDto(
                "default",
                convert(masterDto.state),
                masterType,
                buildId(masterDto.objectId,-1),
                masterDto.name,
                masterDto.owner.accountId,
                DeviceLogicState.OPEN,
                deviceInfoGeneratorService.getDeviceLogoUrl(masterType)
        );
        DeviceAndFeatureRelationDto masterDeviceAndFeatureDto = new DeviceAndFeatureRelationDto(
                masterDeviceDto,
                buildDeviceFeatureByStubDto(masterDto.stubs)
        );
        deviceAndFeatureDtos.add(masterDeviceAndFeatureDto);

        for (DeviceSlaveObjInfoDto objDto : masterDto.slaves) {
            String deviceType = deviceInfoGeneratorService.generatorDeviceType(objDto.stubs);
            DeviceDto deviceDto = new DeviceDto(
                    "default",
                    convert(objDto.state),
                    deviceType,
                    buildId(masterDto.objectId,objDto.objectSlaveId),
                    objDto.name,
                    masterDto.owner.accountId,
                    DeviceLogicState.OPEN,
                    deviceInfoGeneratorService.getDeviceLogoUrl(deviceType)
            );
            if(deviceDto.deviceState == null)
                continue;
            DeviceAndFeatureRelationDto deviceAndFeatureDto =
                    new DeviceAndFeatureRelationDto(
                            deviceDto,
                            buildDeviceFeatureByStubDto(objDto.stubs)
                    );
            deviceAndFeatureDtos.add(deviceAndFeatureDto);
        }

        return deviceAndFeatureDtos;
    }

    public static DeviceState convert(AniObjectState state) {
        if (state != null) {
            switch (state) {
                case ACTIVE:
                    return DeviceState.CONNECTED;
                case DISABLE:
                    return DeviceState.DISCONNECTED;
                case REMOVED:
                    return DeviceState.REMOVED;
            }
        }
        LOGGER.info("device state is "+ state);
        return null;
    }

    public String buildId(Long deviceMasterId, Integer slaveId) {
        return deviceMasterId + Device.DEVICE_CODE_SEPARATOR + slaveId;
    }

    public List<DeviceFeatureDto> buildDeviceFeatureByStubDto(List<StubInfoDto> stubDtos) {
        List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>();
        if (stubDtos != null) {
            Set<StubIdentity> deviceStubSet = fetchDeviceStubSet(stubDtos);
            for (DeviceFeatureDto deviceFeatureDto : deviceFeatureDtos) {
                Set<StubIdentity> featureStubSet = fetchDeviceFeatureStubSet(deviceFeatureDto);
                Collection<StubIdentity> intersectionList = CollectionUtils.intersection(deviceStubSet, featureStubSet);
                if (featureStubSet.size() == intersectionList.size()) {
                    deviceFeatureDtoList.add(deviceFeatureDto);
                }
            }
        }
        return deviceFeatureDtoList;
    }

    public Set<StubIdentity> fetchDeviceStubSet(List<StubInfoDto> stubDtos) {
        Set<StubIdentity> stubIdentitySet = new HashSet<>();
        for (StubInfoDto stubDto : stubDtos) {
            StubIdentity stubIdentity = new StubIdentity(
                    stubDto.stubId,
                    stubDto.group.groupId
            );
            stubIdentitySet.add(stubIdentity);
        }
        return stubIdentitySet;
    }

    public Set<StubIdentity> fetchDeviceFeatureStubSet(DeviceFeatureDto deviceFeatureDto) {
        Set<StubIdentity> stubIdentitySet = new HashSet<>();
        for (FeatureFunctionDto ffd : deviceFeatureDto.featureFunctionDtoList) {
            StubIdentity stubIdentity = new StubIdentity(
                    ffd.stubId,
                    ffd.groupId
            );
            stubIdentitySet.add(stubIdentity);
        }
        return stubIdentitySet;
    }

    public Long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Set<StubIdentity> stubIdentities = new HashSet<>();
        stubIdentities.add(new StubIdentity(1, 1L));
        stubIdentities.add(new StubIdentity(1, 1L));

        Set<StubIdentity> stubIdentities1 = new HashSet<>();
        stubIdentities1.add(new StubIdentity(1, 1L));
        stubIdentities1.add(new StubIdentity(1, 2L));

        System.out.println(stubIdentities.equals(stubIdentities1));
    }
}
