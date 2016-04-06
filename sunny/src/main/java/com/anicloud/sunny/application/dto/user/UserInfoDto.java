package com.anicloud.sunny.application.dto.user;

import java.io.Serializable;

/**
 * Created by zhaoyu on 15-8-1.
 */
public class UserInfoDto implements Serializable {
    private static final long serialVersionUID = -5416766932834647752L;

    public Long hashUserId;
    public String email;
    public String screenName;

    public UserInfoDto() {
    }

    public UserInfoDto(Long hashUserId,
                       String email, String screenName) {
        this.hashUserId = hashUserId;
        this.email = email;
        this.screenName = screenName;
    }

    public UserInfoDto(UserDto userDto) {
        this(
                userDto.hashUserId,
                userDto.email,
                userDto.screenName
        );
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "hashUserId='" + hashUserId + '\'' +
                ", email='" + email + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }

    public Long getHashUserId() {
        return hashUserId;
    }

    public void setHashUserId(Long hashUserId) {
        this.hashUserId = hashUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
