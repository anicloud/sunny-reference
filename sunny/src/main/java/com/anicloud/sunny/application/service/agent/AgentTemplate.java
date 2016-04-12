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
import com.ani.octopus.service.agent.service.websocket.AniInvokable;
import com.ani.octopus.service.agent.service.websocket.AniInvokerImpl;
import com.anicloud.sunny.application.constant.Constants;
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
    private AniInvokable aniInvokable;

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
        } else {
            accountService.setAccessToken(accessToken);
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
        } else {
            accountGroupService.setAccessToken(accessToken);
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

    public synchronized DeviceObjService getDeviceObjService(String accessToken) {
        if (deviceObjService == null) {
            deviceObjService = new DeviceObjServiceImpl(
                    anicelMeta,
                    restTemplateFactory,
                    accessToken
            );
        } else {
            deviceObjService.setAccessToken(accessToken);
        }
        return deviceObjService;
    }

    public synchronized AniInvokable getAniInvokable() {
        if (aniInvokable == null) {
            aniInvokable = new AniInvokerImpl(Constants.aniServiceSession);
        }
        return aniInvokable;
    }
}
