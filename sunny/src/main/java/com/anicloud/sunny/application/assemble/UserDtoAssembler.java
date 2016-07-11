package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.domain.model.user.User;

/**
 * Created by zhaoyu on 15-6-14.
 */
public class UserDtoAssembler {
    private UserDtoAssembler() {}

    public static User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return new User(
                userDto.accessToken,
                userDto.email,
                userDto.expiresIn,
                userDto.hashUserId,
                userDto.refreshToken,
                userDto.scope,
                userDto.screenName,
                userDto.tokenType,
                userDto.createTime
        );
    }

    public static UserDto fromUser(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
                user.accessToken,
                user.email,
                user.expiresIn,
                user.hashUserId,
                user.refreshToken,
                user.scope,
                user.screenName,
                user.tokenType,
                user.createTime
        );
    }
}
