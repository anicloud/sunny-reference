package com.anicloud.sunny.application.utils;


import com.ani.agent.service.commons.oauth.dto.AuthorizationCodeParameter;
import com.ani.agent.service.commons.oauth.enumeration.GrantType;
import com.anicloud.sunny.application.dto.app.AniServiceDto;

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
