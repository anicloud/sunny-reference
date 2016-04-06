package com.anicloud.sunny.application.service.app;

import com.ani.bus.service.commons.dto.aniservice.AniServiceDto;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceEntranceDao;
import com.anicloud.sunny.interfaces.facade.AppServiceFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/root-context.xml"
})
public class AppServiceFacadeTest {
    private final static String FILE_PATH = "properties/AniServiceInfo.json";

    @Resource
    private AppServiceFacade appServiceFacade;

    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void testGetAniServiceInfo() throws Exception {
        AniServiceDto aniServiceDto = appServiceFacade.getAniServiceInfo();
        System.out.println(aniServiceDto);
    }

    @Test
    public void testUpdate() throws Exception {
        // TODO
    }

    @Test
    public void testCreateAniService() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        org.springframework.core.io.Resource resource = resourceLoader.getResource(FILE_PATH);
        File targetFile = resource.getFile();
        AniServiceDao aniServiceDao = new AniServiceDao();
        aniServiceDao.id = 1L;
        aniServiceDao.accessTokenValidity = 43200;
        aniServiceDao.accountId = 1707593791689932096L;
        aniServiceDao.aniServiceId = "1058595963104900977";
        aniServiceDao.archived = Boolean.FALSE;
        aniServiceDao.authorities = "ROLE_SYS";
        aniServiceDao.authorizedGrantTypes = "authorization_code,refresh_token,password,implicit";
        aniServiceDao.autoApprove = "true";
        aniServiceDao.clientSecret = "34d54214721d6077ae021ab5d8215258";
        aniServiceDao.refreshTokenValidity = 2592000;
        aniServiceDao.registerDate = new Date(1449815849286L);
        aniServiceDao.resourceIds = "system-resource";
        aniServiceDao.scope = "read,write";
        aniServiceDao.serviceName = "sunny-app";
        aniServiceDao.trusted = Boolean.TRUE;
        aniServiceDao.version = "1.0";
        aniServiceDao.webServerRedirectUri = "http://localhost:8080/sunny/redirect";
        aniServiceDao.description = "sunny app";
        aniServiceDao.languageSet = "ZH_CN";
        aniServiceDao.logoPath = "https://raw.githubusercontent.com/anicloud/anicloud.github.io/master/images/logo/ani_logo.png";
        aniServiceDao.onShelf = new Date(1449815846929L);
        aniServiceDao.price = 0.0;
        aniServiceDao.serviceServerUrl = "http://localhost:8080/sunny";
        aniServiceDao.tagSet = "life";

        List<AniServiceEntranceDao> entranceDaoList = new ArrayList<>();
        AniServiceEntranceDao aniServiceEntranceDao = new AniServiceEntranceDao(
                1L,
                "sunny entrance",
                "http://localhost:8080/sunny/strategy",
                "https://raw.githubusercontent.com/anicloud/anicloud.github.io/master/images/logo/ani_logo.png",
                "life",
                "sunny entrance"
        );

        entranceDaoList.add(aniServiceEntranceDao);
        aniServiceDao.entranceList = entranceDaoList;

        if (targetFile.exists()) {
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
            objectMapper.writeValue(targetFile, aniServiceDao);
        }
    }
}