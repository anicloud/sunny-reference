package com.anicloud.sunny.domain.model.user;

import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.domain.model.device.Device;
import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import com.anicloud.sunny.infrastructure.persistence.service.UserPersistenceService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class User extends AbstractDomain {
    private static final long serialVersionUID = -3291503820309014053L;

    public String hashUserId;
    public String email;
    public String screenName;

    public String accessToken;
    public String tokenType;
    public String refreshToken;
    public Long expiresIn;
    public String scope;
    public Long createTime;

    public Set<Device> deviceSet;

    public User() {
    }

    public User(String accessToken, String email,
                Long expiresIn, String hashUserId, String refreshToken,
                String scope, String screenName, String tokenType, Long createTime) {
        this.accessToken = accessToken;
        this.email = email;
        this.expiresIn = expiresIn;
        this.hashUserId = hashUserId;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.screenName = screenName;
        this.tokenType = tokenType;
        this.createTime = createTime;
    }

    public static User save(UserPersistenceService userPersistenceService, User user) {
        UserDao userDao = toDao(user);
        userDao = userPersistenceService.saveUser(userDao);
        return toUser(userDao);
    }

    public static User modify(UserPersistenceService userPersistenceService, User user) {
        UserDao userDao = userPersistenceService.getUserByHashUserId(user.hashUserId);

        userDao.email = user.email;
        userDao.screenName = user.screenName;
        userDao.accessToken = user.accessToken;
        userDao.tokenType = user.tokenType;
        userDao.refreshToken = user.refreshToken;
        userDao.expiresIn = user.expiresIn;
        userDao.scope = user.scope;

        userDao = userPersistenceService.saveUser(userDao);
        return toUser(userDao);
    }

    public static void remove(UserPersistenceService userPersistenceService, User user) {
        UserDao userDao = userPersistenceService.getUserByHashUserId(user.hashUserId);
        if (userDao == null) {
            throw new EmptyResultDataAccessException(1);
        }
        userPersistenceService.deleteUser(userDao);
    }

    public static User getUserByHashUserId(UserPersistenceService userPersistenceService, String hashUserId) {
        UserDao userDao = userPersistenceService.getUserByHashUserId(hashUserId);
        if (userDao == null) {
            return null;
        }
        return toUser(userDao);
    }

    public static User getUserByEmail(UserPersistenceService userPersistenceService, String email) {
        UserDao userDao = userPersistenceService.getUserByEmail(email);
        if (userDao == null) {
            return null;
        }
        return toUser(userDao);
    }

    public static User toUser(UserDao userDao) {
        User user = new User(
                userDao.accessToken,
                userDao.email,
                userDao.expiresIn,
                userDao.hashUserId,
                userDao.refreshToken,
                userDao.scope,
                userDao.screenName,
                userDao.tokenType,
                userDao.createTime);
        return user;
    }

    public static UserDao toDao(User user) {
        UserDao userDao = new UserDao(
                user.accessToken,
                user.email,
                user.expiresIn,
                user.hashUserId,
                user.refreshToken,
                user.scope,
                user.screenName,
                user.tokenType,
                user.createTime);
        return userDao;
    }

}
