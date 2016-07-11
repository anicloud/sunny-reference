package com.anicloud.sunny.application.service.user;

import com.anicloud.sunny.application.dto.user.UserDto;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by wyf on 16-6-27.
 */
public class UserServiceTest {
    @Resource
    private UserService userService;
    @Before
    public void before(){
        //userService = new UserServiceEventHandler();
    }
    @Test
    public void saveUser() throws Exception {
        UserDto userDto = new UserDto("c4089388-cf1e-46de-8ce9-90807f44e757",
                "ching-zhou@anicloud.com"
                ,23485L
                ,1259429903855376344L
                ,"3e4bedab-c332-4c64-adec-42929fcc3a8c"
                ,"read write"
                ,"yeh"
                ,"bearer"
                ,146701L);
        userService.saveUser(userDto);
    }

    @Test
    public void modifyUser() throws Exception {

    }

    @Test
    public void removeUser() throws Exception {

    }

    @Test
    public void getUserByHashUserId() throws Exception {

    }

    @Test
    public void getUserByEmail() throws Exception {

    }

    @Test
    public void refreshUserToken() throws Exception {

    }

}