package com.anicloud.sunny.application.builder;

import com.ani.bus.service.commons.dto.aniservice.AniServiceDto;
import com.ani.cel.service.manager.agent.oauth2.model.AuthorizationCodeParameter;
import com.ani.cel.service.manager.agent.oauth2.model.GrantType;
import com.anicloud.sunny.application.dto.app.AppClientDto;

/**
 * Created by zhaoyu on 15-6-27.
 */
public class OAuth2ParameterBuilder {
    private OAuth2ParameterBuilder() {}

    public static AuthorizationCodeParameter buildForAccessToken(AniServiceDto aniServiceDto) {
        AuthorizationCodeParameter authorizationCodeParameter = new AuthorizationCodeParameter();
        authorizationCodeParameter.setClientId(aniServiceDto.aniServiceId)
                .setClientSecret(aniServiceDto.clientSecret)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setRedirectUri(aniServiceDto.webServerRedirectUri);
        return authorizationCodeParameter;
    }

    public static AuthorizationCodeParameter buildForRefreshToken(AniServiceDto aniServiceDto) {
        AuthorizationCodeParameter authorizationCodeParameter = new AuthorizationCodeParameter();
        authorizationCodeParameter.setClientId(aniServiceDto.aniServiceId)
                .setClientSecret(aniServiceDto.clientSecret)
                .setGrantType(GrantType.REFRESH_TOKEN);
        return authorizationCodeParameter;
    }
}
