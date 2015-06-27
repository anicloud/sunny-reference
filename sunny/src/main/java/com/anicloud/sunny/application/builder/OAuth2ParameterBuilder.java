package com.anicloud.sunny.application.builder;

import com.ani.cel.service.manager.agent.oauth2.model.AuthorizationCodeParameter;
import com.ani.cel.service.manager.agent.oauth2.model.GrantType;
import com.anicloud.sunny.application.dto.app.AppClientDto;

/**
 * Created by zhaoyu on 15-6-27.
 */
public class OAuth2ParameterBuilder {
    private OAuth2ParameterBuilder() {}

    public static AuthorizationCodeParameter build(AppClientDto appClientDto) {
        AuthorizationCodeParameter authorizationCodeParameter = new AuthorizationCodeParameter();
        authorizationCodeParameter.setClientId(appClientDto.clientId)
                .setClientSecret(appClientDto.clientSecret)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setRedirectUri(appClientDto.webServerRedirectUri);
        return authorizationCodeParameter;
    }
}
