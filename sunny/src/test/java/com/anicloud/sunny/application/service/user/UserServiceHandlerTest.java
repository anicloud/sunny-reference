package com.anicloud.sunny.application.service.user;

import com.anicloud.sunny.application.dto.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 15-6-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/rect-persist.xml",
        "classpath:spring/root-context.xml"
})
public class UserServiceHandlerTest{

    @Resource
    private UserService userService;

    private UserDto userDto = null;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void before() {
        userDto = new UserDto();
        userDto.accessToken = "69c14dd9-e896-4dd3-9351-6049b51f1342";
        userDto.tokenType = "bearer";
        userDto.email = "ching-zhou@anicloud.com";
        userDto.expiresIn = 43199L;
        userDto.hashUserId = "4c781d51d638cf133df74e6176f839e2";
        userDto.refreshToken = "f6f3f52d-06f4-49fc-ba23-c1e3c96e8809";
        userDto.scope = "read write";
        userDto.screenName = "Yeh";
    }

    @Ignore
    public void testSaveUser() throws Exception {
        userDto = userService.saveUser(userDto);
        System.out.println(objectMapper.writeValueAsString(userDto));
    }

    @Ignore
    public void testModifyUser() throws Exception {
        userDto = userService.getUserByHashUserId(userDto.hashUserId);
        userDto.screenName = "zhangzhaoyu";
        userDto.email = "zhangzhaoyu@anicloud.com";
        userDto = userService.modifyUser(userDto);

        System.out.println(objectMapper.writeValueAsString(userDto));
    }

    @Ignore
    public void testRemoveUser() throws Exception {
        userService.removeUser(userDto);
    }

    @Ignore
    public void testGetUserByHashUserId() throws Exception {
        userDto = userService.getUserByHashUserId(userDto.hashUserId);
        System.out.println(objectMapper.writeValueAsString(userDto));
    }

    @Ignore
    public void testGetUserByEmail() throws Exception {
        userDto = userService.getUserByEmail(userDto.email);
        System.out.println(objectMapper.writeValueAsString(userDto));
    }

    @After
    public void after() {

    }

}