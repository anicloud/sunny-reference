package com.anicloud.sunny.application.service;

import com.anicloud.sunny.application.assemble.UserDtoAssembler;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.infrastructure.persistence.service.UserPersistenceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zsl on 16-8-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context/root-context.xml")
public class Testhibernate {
    @Resource
    private UserService userService;
    @Resource
    private UserPersistenceService userPersistenceService;
    @Test
    public void testSaveUser() {
        UserDto userDao = new UserDto("71c84473-028b-434c-b817-8e98066255ea","huangbin@anicloud.com",
                (long)27557,Long.valueOf("764111382711898568"),"caf0c771-adc4-45d5-9e93-f52ac248d3e3",
                "read write","Huang","bearer",Long.valueOf("1472543499859"));
        User user = UserDtoAssembler.toUser(userDao);
        User.save(userPersistenceService, user);
    }
}
