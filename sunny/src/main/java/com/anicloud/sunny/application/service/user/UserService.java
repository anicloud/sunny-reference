package com.anicloud.sunny.application.service.user;

import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface UserService {
    public UserDto saveUser(UserDto userDao);
    public UserDto modifyUser(UserDto userDao);
    public void removeUser(UserDto userDao);

    public UserDto getUserByHashUserId(String hashUserId);
    public UserDto getUserByEmail(String email);
}
