package com.anicloud.sunny.application.service.init;

import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.agent.service.commons.object.enumeration.DeviceState;
import com.ani.agent.service.service.AgentTemplate;
import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.ani.octopus.commons.accout.dto.AccountDto;
import com.ani.octopus.commons.object.enumeration.AniObjectState;
import com.ani.octopus.commons.stub.dto.StubDto;
import com.ani.utils.core.AniByte;
import com.anicloud.sunny.application.dto.device.DeviceAndFeatureRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.device.DeviceAndFeatureRelationService;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.domain.model.device.Device;
import com.anicloud.sunny.domain.model.device.DeviceAndFeatureRelation;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceLogicState;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

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
    private com.anicloud.sunny.application.service.device.DeviceService sunnyDeviceService;
    @Resource
    private DeviceAndFeatureRelationService deviceAndFeatureRelationService;
    @Resource
    private ObjectMapper objectMapper;
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
    protected void initUserDeviceAndDeviceFeatureRelation(AccountDto accountDto,
                                                          AniOAuthAccessToken accessToken) throws Exception {
        List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtoList = agentTemplate
                .getDeviceObjService(accessToken.getAccessToken())
                .getDeviceObjInfo(accountDto.accountId, Boolean.TRUE);
        List<DeviceAndFeatureRelationDto> deviceAndFeatureRelationDtos =
                getRelation(deviceMasterObjInfoDtoList, accessToken);
        LOGGER.info("Initialize DeviceAndFeatureRelation...");
        deviceAndFeatureRelationService.batchSave(deviceAndFeatureRelationDtos);
    }

    @Override
    public void updateUserDeviceAndDeviceFeatureRelation(DeviceMasterObjInfoDto masterDto) {
        List<DeviceAndFeatureRelationDto> deviceAndFeatureRelationDtos = getRalation(masterDto);
        DeviceAndFeatureRelationDto relationDto = deviceAndFeatureRelationService.findByDeviceIdentificationCode(String.valueOf(masterDto.objectId));
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
        return userDto == null ? true : false;
    }

    @Override
    public UserDto initApplication(AniOAuthAccessToken accessToken) throws Exception {
        AccountDto accountDto = agentTemplate
                .getAccountService(accessToken.getAccessToken())
                .getByAccessToken();
        UserDto userDto = fetchUserInfo(accountDto, accessToken);
        // not exists
        if (isUserNotExists(accountDto.accountId)) {
            // init user
            initUser(userDto);
            // init user-device-devicefeature relation
            initUserDeviceAndDeviceFeatureRelation(accountDto, accessToken);
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

    public List<DeviceAndFeatureRelationDto> getRelation(
            List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtoList,
            AniOAuthAccessToken accessToken) throws Exception {
        List<DeviceAndFeatureRelationDto> deviceAndFeatureDtos = new ArrayList<>();
        for (DeviceMasterObjInfoDto masterDto : deviceMasterObjInfoDtoList) {
            DeviceDto masterDeviceDto = new DeviceDto(
                    "default",
                    convertMasterState(masterDto),
                    deviceInfoGeneratorService.generatorDeviceType(masterDto.stubs),
                    String.valueOf(masterDto.objectId),
                    masterDto.name,
                    fetchUserInfo(masterDto.owner, accessToken),
                    DeviceLogicState.OPEN
            );
            DeviceAndFeatureRelationDto masterDeviceAndFeatureDto =
                    new DeviceAndFeatureRelationDto(
                            masterDeviceDto,
                            buildDeviceFeatureByStubDto(masterDto.stubs)
                    );
            deviceAndFeatureDtos.add(masterDeviceAndFeatureDto);

            for (DeviceSlaveObjInfoDto objDto : masterDto.slaves) {
                DeviceDto deviceDto = new DeviceDto(
                        "default",
                        convert(objDto.state),
                        deviceInfoGeneratorService.generatorDeviceType(objDto.stubs),
                        String.valueOf(objDto.objectSlaveId),
                        objDto.name,
                        fetchUserInfo(masterDto.owner, accessToken),
                        DeviceLogicState.OPEN
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
        DeviceDto masterDeviceDto = new DeviceDto(
                "default",
                convertMasterState(masterDto),
                deviceInfoGeneratorService.generatorDeviceType(masterDto.stubs),
                String.valueOf(masterDto.objectId),
                masterDto.name,
                fetchUserInfo(masterDto.owner,null),
                DeviceLogicState.OPEN
        );
        DeviceAndFeatureRelationDto masterDeviceAndFeatureDto = new DeviceAndFeatureRelationDto(
                masterDeviceDto,
                buildDeviceFeatureByStubDto(masterDto.stubs)
        );
        deviceAndFeatureDtos.add(masterDeviceAndFeatureDto);

        for (DeviceSlaveObjInfoDto objDto : masterDto.slaves) {
            DeviceDto deviceDto = new DeviceDto(
                    "default",
                    convert(objDto.state),
                    deviceInfoGeneratorService.generatorDeviceType(objDto.stubs),
                    String.valueOf(objDto.objectSlaveId),
                    objDto.name,
                    fetchUserInfo(masterDto.owner, null),
                    DeviceLogicState.OPEN
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
        return null;
    }
    public static DeviceState convertMasterState(DeviceMasterObjInfoDto dto){
        Set<AniByte> keyset = dto.hostsState.keySet();
        AniObjectState state = null;
        for(AniByte key : keyset){
            state = dto.hostsState.get(key);
           break;
        }
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
        return null;
    }

    public String buildId(Long deviceMasterId, Long slaveId) {
        return deviceMasterId + Device.DEVICE_CODE_SEPARATOR + slaveId;
    }

    public List<DeviceFeatureDto> buildDeviceFeatureByStubDto(List<StubDto> stubDtos) {
        List<DeviceFeatureDto> deviceFeatureDtoList = new ArrayList<>();
        for (DeviceFeatureDto deviceFeatureDto : deviceFeatureDtos) {
            Set<StubIdentity> deviceStubSet = fetchDeviceStubSet(stubDtos);
            Set<StubIdentity> featureStubSet = fetchDeviceFeatureStubSet(deviceFeatureDto);
            Collection<StubIdentity> intersectionList = CollectionUtils.intersection(deviceStubSet, featureStubSet);
            if(featureStubSet.size() == intersectionList.size()){
                deviceFeatureDtoList.add(deviceFeatureDto);
            }
        }
        return deviceFeatureDtoList;
    }

    public Set<StubIdentity> fetchDeviceStubSet(List<StubDto> stubDtos) {
        Set<StubIdentity> stubIdentitySet = new HashSet<>();
        for (StubDto stubDto : stubDtos) {
            StubIdentity stubIdentity = new StubIdentity(
                    stubDto.stubId,
                    stubDto.stubGroupId
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
