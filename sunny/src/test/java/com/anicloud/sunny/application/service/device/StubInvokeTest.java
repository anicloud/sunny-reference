package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.service.strategy.CurrentFeatureService;
import com.anicloud.sunny.infrastructure.persistence.domain.device.CurrentFeatureInstanceDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihui on 16-7-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/persistence/database/rect-persist.xml",
        "classpath:application-context/root-context.xml"
})
public class StubInvokeTest {
    @Resource
    private CurrentFeatureService service;
    @Test
    public void testUpdateCurrentFeature(){
        CurrentFeatureInstanceDao dao = new CurrentFeatureInstanceDao("111","222",1,123L);
        service.saveCurrentFeature(dao);
    }
    @Test
    public void testFindCurrentFeature1(){
        service.updateCurrentFeatureInstance(10,"456");
    }
    @Test
    public void testFindCurrentFeature(){
        List<CurrentFeatureInstanceDao> list = service.findAll();
        for(int i=0; i<list.size(); i++) {
            String deviceId = list.get(i).deviceId;
        }
    }
}
