package com.anicloud.sunny.application.service.app.stub;

import com.anicloud.sunny.domain.model.app.stub.ArgumentType;
import com.anicloud.sunny.domain.model.app.stub.Stub;
import com.anicloud.sunny.domain.model.app.stub.StubArgument;
import com.anicloud.sunny.domain.model.app.stub.StubGroup;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.DataType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MRK on 2016/3/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context/root-context.xml"
})
public class StubServiceTest {

    @Resource
    private StubService stubService;

    @Resource
    private StubGroupService stubGroupService;

    @Test
    public void testGetAll() throws Exception {
        List<Stub> list= stubService.getAll();
        System.err.println("----***---***"+list+"---***---***");
    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testSave() throws Exception {
        StubGroup stubGroup=stubGroupService.getById(1);
        StubArgument in=new StubArgument("arg1",new ArgumentType(DataType.ARRAY,new ArgumentType(DataType.BOOLEAN)));
        StubArgument out=new StubArgument("arg2",new ArgumentType(DataType.DOUBLE));
        List<StubArgument> list1=new ArrayList<>();
        List<StubArgument> list2=new ArrayList<>();
        list1.add(in);
        list2.add(out);
        Stub stub=new Stub(
                1,
                "sunny",
                stubGroup,
                null,
                null
        );
        stubService.save(stub);
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }
}