package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import org.springframework.stereotype.Service;

/**
 * Created by zhaoyu on 15-6-14.
 */
@Service
public interface UserPersistenceService {
    public UserDao saveUser(UserDao userDao);
    public UserDao modifyUser(UserDao userDao);
    public void deleteUser(UserDao userDao);

    public UserDao getUserByHashUserId(String hashUserId);
    public UserDao getUserByEmail(String email);
}
