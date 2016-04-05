package com.anicloud.sunny.application.service.init;

import com.ani.bus.service.commons.dto.anidevice.DeviceMasterObjInfoDto;
import com.ani.octopus.commons.accout.dto.AccountDto;
import com.ani.octopus.service.agent.service.oauth.dto.AniOAuthAccessToken;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.agent.AgentTemplate;
import com.anicloud.sunny.application.service.user.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
@Component("applicationInitService")
public class ApplicationInitServiceImpl2 extends ApplicationInitService {

    @Resource(name = "agentTemplate")
    private AgentTemplate agentTemplate;
    @Resource
    private DeviceTypeGenerator deviceTypeGenerator;
    @Resource
    private UserService userService;

    @Override
    protected UserDto initUser(UserDto userDto) {
        return null;
    }

    @Override
    protected void initUserDeviceAndDeviceFeatureRelation(AccountDto accountDto,
                                                          AniOAuthAccessToken accessToken) throws Exception {
        List<DeviceMasterObjInfoDto> deviceMasterObjInfoDtoList = agentTemplate
                .getDeviceObjService(accessToken.getAccessToken())
                .getDeviceObjInfo(accountDto.accountId, Boolean.TRUE);
    }

    @Override
    protected boolean isUserNotExists(Long accountId) {
        // TODO
        // judge if the account exists in sunny database
        return false;
    }

    @Override
    public UserDto initApplication(AniOAuthAccessToken accessToken) throws Exception {
        AccountDto accountDto = agentTemplate
                .getAccountService(accessToken.getAccessToken())
                .getByAccessToken();
        UserDto userDto = fetchUserInfo(accountDto, accessToken);
        // not exists
        if (!isUserNotExists(accountDto.accountId)) {
            // init user
            initUser(userDto);
            // init user-device-devicefeature relation
            initUserDeviceAndDeviceFeatureRelation(accountDto, accessToken);
        }
        return userDto;
    }

    protected UserDto fetchUserInfo(AccountDto accountDto, AniOAuthAccessToken accessToken) {
        // TODO
        return null;
    }
}
