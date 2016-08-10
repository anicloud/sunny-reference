package com.anicloud.sunny.application.service.app;

import com.ani.agent.service.service.AgentTemplate;
import com.ani.agent.service.service.account.AccountService;
import com.ani.agent.service.service.aniservice.AniServiceManager;
import com.ani.bus.service.commons.dto.aniservice.AniServiceInfoDto;
import com.ani.bus.service.commons.dto.aniservice.AniServiceRegisterDto;
import com.ani.bus.service.commons.dto.aniservice.LanguageEnum;
import com.ani.earth.commons.dto.AccountRegisterDto;
import com.ani.earth.commons.dto.AccountType;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.domain.adapter.AniServiceDaoAdapter;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceEntranceDao;
import com.anicloud.sunny.interfaces.facade.AppServiceFacade;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceDto;
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
import java.util.*;

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

    @Resource
    private AgentTemplate agentTemplate;
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
        //aniServiceDao.onShelf = new Date(1449815846929L);
        aniServiceDao.price = 0.0;
        aniServiceDao.serviceServerUrl = "http://localhost:8080/sunny";
        aniServiceDao.tagSet = "life";

        List<AniServiceEntranceDao> entranceDaoList = new ArrayList<>();
        AniServiceEntranceDao aniServiceEntranceDao = new AniServiceEntranceDao(
                null,
                "yihealth entrance",
                "http://localhost:8080/yihealth/strategy",
                "https://raw.githubusercontent.com/anicloud/anicloud.github.io/master/images/logo/ani_logo.png",
                "life",
                "yihealth entrance"
        );
        Set<LanguageEnum> languageEnumSet  =new HashSet<>();
        for(String lan :Constants.aniServiceDto.languageSet){
            LanguageEnum languageEnum = (LanguageEnum)Enum.valueOf(LanguageEnum.class,lan.trim());
            languageEnumSet.add(languageEnum);
        }
        AniServiceInfoDto aniserviceinfo = new AniServiceInfoDto(
                null,
                "http://localhost:8080/yihealth",
                Constants.aniServiceDto.logoPath,
                languageEnumSet,
                Constants.aniServiceDto.tagSet,
                Constants.aniServiceDto.price,
                Constants.aniServiceDto.onShelf,
                "yihealth app"
        );
        entranceDaoList.add(aniServiceEntranceDao);
        aniServiceDao.entranceList = entranceDaoList;
        AniServiceManager aniServiceManager = agentTemplate.getAniServiceManager();
        List<com.ani.bus.service.commons.dto.aniservice.AniServiceEntranceDto> aniServiceEntranceDto =
                AniServiceDaoAdapter.fromCommonsToLocal(Constants.aniServiceDto.entranceList);
        AniServiceRegisterDto aniServiceRegisterDto = new AniServiceRegisterDto(
                Constants.aniServiceDto.aniServiceId,
                "yihealth-app",
                Constants.aniServiceDto.version,
                "http://localhost:8080/yihealth/home/redirect",
                Constants.aniServiceDto.accountId,
                aniServiceEntranceDto,
                aniserviceinfo,
                null
        );
        aniServiceRegisterDto.addStub(1L, 1);

        try {
            com.ani.bus.service.commons.dto.aniservice.AniServiceDto dto = aniServiceManager.register(aniServiceRegisterDto);
            String str = objectMapper.writeValueAsString(dto);
            System.out.println(str);
        }catch (Exception e){

        }
    }
    @Test
    public void testAccountRegist() throws IOException{
        AccountService accountService = agentTemplate.getAccountService(null);
        AccountRegisterDto accountRegisterDto = new AccountRegisterDto(
                        "Bill",
                        "bill@anicloud.com",
                        "123456",
                        AccountType.PERSONAL,
                        "18511929814",
                        "Fengtai, Beijing",
                        "Anicloud Limited",
                        "https://raw.githubusercontent.com/anicloud/anicloud.github.io/master/images/logo/ani_logo.png"
        );
        try {
            accountService.register(accountRegisterDto);
        }catch (Exception e) {

        }
    }
}