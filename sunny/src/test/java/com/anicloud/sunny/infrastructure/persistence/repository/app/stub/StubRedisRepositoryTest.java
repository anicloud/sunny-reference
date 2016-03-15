package com.anicloud.sunny.infrastructure.persistence.repository.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MRK on 2016/3/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/persistence/database/RedisDataSource.xml"
})
public class StubRedisRepositoryTest {

    private String name = "test";

    @Resource
    private StubRedisRepository stubRedisRepository;

    @Before
    public void before() {
        System.out.println(stubRedisRepository);
    }

    @Test
    public void testAdd() {
        StubDao stubDao = new StubDao(1, "xx", null, null, null);
        StubDao stubDao2 = new StubDao(2, "ss", null, null, null);
        stubRedisRepository.put(name, stubDao);
        stubRedisRepository.put(name, stubDao2);
    }

    @Test
    public void testGetAll() {
        List<StubDao> result = stubRedisRepository.get(name);
        System.out.println(result);
    }

    @Test
    public void testRemove() {
        StubDao stubDao = new StubDao(1, "xx", null, null, null);
        stubRedisRepository.evict(name, stubDao);
        testGetAll();
    }
}