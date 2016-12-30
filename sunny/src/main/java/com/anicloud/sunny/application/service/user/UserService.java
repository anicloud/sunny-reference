package com.anicloud.sunny.application.service.user;

import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface UserService {
    public User saveUser(User userDao);
    public User modifyUser(User userDao);
    public void removeUser(User userDao);

    public User getUserByHashUserId(Long hashUserId);
    public User getUserByEmail(String email);
    public User refreshUserToken(Long hashUserId);
}
