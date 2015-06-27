package com.anicloud.sunny.application.service.app;

import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.app.AppClientDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 15-6-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/rect-persist.xml",
        "classpath:spring/root-context.xml"
})
public class AppEventHandlerTest {

    @Resource
    private AppService appService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private AppClientDto clientDto;

    @Before
    public void before() throws JsonProcessingException {
        clientDto = new AppClientDto();
        clientDto.clientName = "sunny-client";
        clientDto.clientServerUrl = "www.sunny.anicloud.com";
        clientDto.description = "sunny smart home";
        clientDto.language = "zh_CN";
        clientDto.logoPath = "https://github.com/anicloud/anicloud.github.io/blob/master/images/logo/ani_logo.png";
        clientDto.onShelf = new Date().getTime();
        clientDto.price = 0.0;
        clientDto.tags = "LIFE,SMART HOME";
        clientDto.version = "1.0.0";

        clientDto.developerAddress = "Beijing,China";
        clientDto.developerEmail = "zhangzhaoyu@anicloud.com";
        clientDto.developerName = "anicloud";
        clientDto.developerPhoneNum = "18511929810";

        clientDto.clientId = "sunny-client";
        clientDto.resourceIds = "user-resource,app-resource,device-resource";
        clientDto.clientSecret = "sunny-client-secret";
        clientDto.scope = "read,write";
        clientDto.authorizedGrantType = "authorization_code,refresh_token,password,implicit";
        clientDto.webServerRedirectUri = "http://localhost:8080/sunny/redirect";
        clientDto.authorities = "ROLE_USER,ROLE_APP,ROLE_DEVICE";
        clientDto.autoApprove = null;
        clientDto.createTime = new Date();

        System.out.println(objectMapper.writeValueAsString(clientDto));
    }

    @Ignore
    public void testSave() throws Exception {
        clientDto = this.appService.save(clientDto);
        System.out.println(objectMapper.writeValueAsString(clientDto));
    }

    @Test
    public void testModify() throws Exception {

    }

    @Ignore
    public void testFindByClientName() throws Exception {
        clientDto = this.appService.findByClientName(Constants.SUNNY_APP_REGISTER_NAME);
        System.out.println(objectMapper.writeValueAsString(clientDto));
    }
}