package com.anicloud.sunny.application.dto.user;

import java.io.Serializable;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = -3722526475602732820L;

    public String hashUserId;
    public String email;
    public String screenName;

    public String accessToken;
    public String tokenType;
    public String refreshToken;
    public Long expiresIn;
    public String scope;

    public UserDto() {
    }

    public UserDto(String accessToken, String email,
                   Long expiresIn, String hashUserId,
                   String refreshToken, String scope,
                   String screenName, String tokenType) {
        this.accessToken = accessToken;
        this.email = email;
        this.expiresIn = expiresIn;
        this.hashUserId = hashUserId;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.screenName = screenName;
        this.tokenType = tokenType;
    }
}
