package com.anicloud.sunny.application.builder;

import com.ani.octopus.service.agent.service.oauth.dto.AuthorizationCodeParameter;
import com.ani.octopus.service.agent.service.oauth.dto.GrantType;
import com.anicloud.sunny.application.dto.app.AppClientDto;

/**
 * Created by zhaoyu on 15-6-27.
 */
public class OAuth2ParameterBuilder {
    private OAuth2ParameterBuilder() {}

    public static AuthorizationCodeParameter buildForAccessToken(AppClientDto appClientDto) {
        AuthorizationCodeParameter authorizationCodeParameter = new AuthorizationCodeParameter();
        authorizationCodeParameter.setClientId(appClientDto.clientId)
                .setClientSecret(appClientDto.clientSecret)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setRedirectUri(appClientDto.webServerRedirectUri);
        return authorizationCodeParameter;
    }

    public static AuthorizationCodeParameter buildForRefreshToken(AppClientDto appClientDto) {
        AuthorizationCodeParameter authorizationCodeParameter = new AuthorizationCodeParameter();
        authorizationCodeParameter.setClientId(appClientDto.clientId)
                .setClientSecret(appClientDto.clientSecret)
                .setGrantType(GrantType.REFRESH_TOKEN);
        return authorizationCodeParameter;
    }
}
