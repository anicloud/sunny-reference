package com.anicloud.sunny.application.service.app;

import com.anicloud.sunny.domain.adapter.DaoAdapter;
import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.domain.model.app.AniServiceEntrance;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceEntranceDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MRK on 2016/3/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/root-context.xml"
})
public class AniServiceManagerTest {

    @Resource
    private AniServiceManager aniServiceManager;

    @Test
    public void testCreateAniService() {
        if (aniServiceManager != null) {
            System.out.println("----------------------------------------------------------" + aniServiceManager);
        } else {
            System.out.println("aniservice manager is null.");
        }
    }

    @Test
    public void testAddAniService() throws IOException {

        AniServiceEntranceDao aniServiceEntranceDao=new AniServiceEntranceDao(
                "xinwo entrance","http://localhost:8080/xinwo",
                "https://raw.githubusercontent.com/anicloud/anicloud.github.io/master/images/logo/ani_logo.png",
                "life","description");

        List<AniServiceEntranceDao> entranceList=new ArrayList<AniServiceEntranceDao>();
        entranceList.add(aniServiceEntranceDao);
        AniServiceDao aniServiceDao=new AniServiceDao(
                null,
                "1058595963104900977",
                "sunny-app",
                "1.0",
                "34d54214721d6077ae021ab5d8215258",
                "system-resource",
                "read,write",
                "authorization_code,refresh_token,password,implicit",
                "ROLE_SYS",
                "http://localhost:8080/sunny/redirect",
                43200,
                2592000,
                "true",
                new Date(new Long(("1449815849286"))),
                false,true,
                "http://localhost:8080/xinwo",
                "https://raw.githubusercontent.com/anicloud/anicloud.github.io/master/images/logo/ani_logo.png",
                "ZH_CN",
                "life",
                0.0,
                new Date(new Long("1449815846929")),
                "sunny app",
                new Long("3888396496254000114"),
                entranceList
        );

        AniService aniService= DaoAdapter.toDomain(aniServiceDao);

        aniServiceManager.save(aniService);
        System.out.println("------------------save finished--------------");

    }

    @Test
    public void testFindAniService() throws IOException {
        AniService aniService=aniServiceManager.getAniServiceInfo();
        System.err.println(aniService.toString());
    }









}