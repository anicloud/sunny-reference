package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import com.anicloud.sunny.infrastructure.persistence.repository.user.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 15-6-14.
 */
@Component
public class UserPersistenceEventHandler implements UserPersistenceService {
    @Resource
    private UserRepository userRepository;

    @Override
    public UserDao saveUser(UserDao userDao) {
        UserDao originUserDao = userRepository.findByHashUserId(userDao.hashUserId);
        if (originUserDao!=null){
            userDao.id = originUserDao.id;
        }
        return userRepository.save(userDao);
    }

    @Override
    public UserDao modifyUser(UserDao userDao) {
        UserDao modifiedUserDao = userRepository.findByHashUserId(userDao.hashUserId);
        if(modifiedUserDao!=null)
            userDao.id = modifiedUserDao.id;
        return userRepository.save(userDao);
    }

    @Override
    public void deleteUser(UserDao userDao) {
        userDao = userRepository.findByHashUserId(userDao.hashUserId);
        userRepository.delete(userDao);
    }

    @Override
    public UserDao getUserByHashUserId(Long hashUserId) {
        return userRepository.findByHashUserId(hashUserId);
    }

    @Override
    public UserDao getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
