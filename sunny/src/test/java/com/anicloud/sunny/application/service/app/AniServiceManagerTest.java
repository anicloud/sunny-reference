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
    public void testAddAniService() {

        AniServiceEntranceDao aniServiceEntranceDao=new AniServiceEntranceDao("asds","asd","asd7","456as","asd5");
        List<AniServiceEntranceDao> entranceList=new ArrayList<AniServiceEntranceDao>();
        entranceList.add(aniServiceEntranceDao);
        AniServiceDao aniServiceDao=new AniServiceDao(
                null,
                "12356465465",
                "sunny",
                "1.0 Beta",
                "asd12156465das",
                "123,ASS,212",
                "123,123,456",
                "asd,asd,sad",
                "asd,asd,456",
                "www.baidu.com",
                132456,
                456797,
                "asdasd",
                new Date(System.currentTimeMillis()),
                true,true,
                "www.google.com",
                "/D:logo/",
                "english,chinese",
                "asd,asdas",
                1500.0,
                new Date(System.currentTimeMillis()),
                "nothing",
                new Long("111111111111111"),
                entranceList
        );

        AniService aniService= DaoAdapter.toDomain(aniServiceDao);

        aniServiceManager.save(aniService);
        System.out.println("------------------save finished--------------");

    }

    @Test
    public void testFindAniService() {
        AniService aniService=aniServiceManager.getAniServiceInfo();
        System.err.println("--------------AniService-----"+aniService.id+"---------------");
        aniService.accountId=new Long("15648543458564");
        aniServiceManager.update(aniService);
        System.err.println("----------------update complete---------------");
    }
}