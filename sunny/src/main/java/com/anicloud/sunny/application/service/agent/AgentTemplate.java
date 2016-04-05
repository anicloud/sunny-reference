package com.anicloud.sunny.application.service.agent;

import com.ani.octopus.service.agent.core.config.AnicelMeta;
import com.ani.octopus.service.agent.core.http.RestTemplateFactory;
import com.ani.octopus.service.agent.service.account.AccountGroupService;
import com.ani.octopus.service.agent.service.account.AccountGroupServiceImpl;
import com.ani.octopus.service.agent.service.account.AccountService;
import com.ani.octopus.service.agent.service.account.AccountServiceImpl;
import com.ani.octopus.service.agent.service.deviceobj.DeviceObjService;
import com.ani.octopus.service.agent.service.deviceobj.DeviceObjServiceImpl;
import com.ani.octopus.service.agent.service.oauth.AniOAuthService;
import com.ani.octopus.service.agent.service.oauth.AniOAuthServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
@Component(value = "agentTemplate")
public class AgentTemplate {

    private AccountService accountService;
    private AccountGroupService accountGroupService;
    private AniOAuthService aniOAuthService;
    private DeviceObjService deviceObjService;

    @Resource
    private AnicelMeta anicelMeta;

    @Resource
    private RestTemplateFactory restTemplateFactory;

    public synchronized AccountService getAccountService(String accessToken) {
        if (accountService == null) {
            accountService = new AccountServiceImpl(
                    anicelMeta,
                    restTemplateFactory,
                    accessToken
            );
        }
        return accountService;
    }

    public synchronized AccountGroupService getAccountGroupService(String accessToken) {
        if (accountGroupService == null) {
            accountGroupService = new AccountGroupServiceImpl(
                    anicelMeta,
                    restTemplateFactory,
                    accessToken
            );
        }
        return accountGroupService;
    }

    public synchronized AniOAuthService getAniOAuthService() {
        if (aniOAuthService == null) {
            aniOAuthService = new AniOAuthServiceImpl(
                    anicelMeta,
                    restTemplateFactory
            );
        }
        return aniOAuthService;
    }

    public synchronized  DeviceObjService getDeviceObjService(String accessToken) {
        if (deviceObjService == null) {
            deviceObjService = new DeviceObjServiceImpl(
                    anicelMeta,
                    restTemplateFactory,
                    accessToken
            );
        }
        return deviceObjService;
    }
}
