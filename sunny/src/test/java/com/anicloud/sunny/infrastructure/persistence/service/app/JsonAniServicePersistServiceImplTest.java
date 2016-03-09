package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MRK on 2016/3/7.
 */
public class JsonAniServicePersistServiceImplTest {

    private AniServicePersistService aniServicePersistService;

    @Before
    public void before() {
        aniServicePersistService = new JsonAniServicePersistServiceImpl();
    }

    @Test
    public void testFindAniServiceInfo() throws Exception {
//        AniServiceDao aniServiceDao = aniServicePersistService.findAniServiceInfo("");
//        System.out.println(aniServiceDao.toString());
//        Assert.assertNotNull(aniServiceDao);
    }

    @Test
    public void testSave() throws Exception {
//        AniServiceDao aniServiceDao = new AniServiceDao("clientSecretzz", "serviceIxxd");
//        aniServicePersistService.save(aniServiceDao);
    }
}