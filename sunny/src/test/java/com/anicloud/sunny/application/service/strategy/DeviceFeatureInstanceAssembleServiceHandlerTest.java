package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceAssembleDto;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by zhaoyu on 15-6-18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/rect-persist.xml",
        "classpath:spring/root-context.xml"
})
public class DeviceFeatureInstanceAssembleServiceHandlerTest {

    @Resource
    private StrategyService strategyService;
    @Resource
    private DeviceFeatureInstanceAssembleService assembleService;

    private DeviceFeatureInstanceAssembleDto assembleDto;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void before() throws JsonProcessingException {
        assembleDto = createData();
        System.out.println(objectMapper.writeValueAsString(assembleDto));
    }

    @Ignore
    public void testSaveAssemble() throws Exception {
        assembleDto = assembleService.saveAssemble(assembleDto);
        System.out.println(objectMapper.writeValueAsString(assembleDto));
    }

    @Ignore
    public void testGetFatherInstanceList() throws Exception {
        String fatherInstanceNum = "e4182324b0d64fe8a3c591318c2a05d9";
        List<DeviceFeatureInstanceAssembleDto> dtoList = assembleService.getFatherInstanceList(fatherInstanceNum);
        System.out.println(objectMapper.writeValueAsString(dtoList));
    }

    @Ignore
    public void testGetLeafInstanceList() throws Exception {
        String leafInstanceNum = "cd82b8f7367e477bb6ab50cae4508def";
        List<DeviceFeatureInstanceAssembleDto> dtoList = assembleService.getLeafInstanceList(leafInstanceNum);
        System.out.println(objectMapper.writeValueAsString(dtoList));
    }

    @After
    public void after() {
    }

    private DeviceFeatureInstanceAssembleDto createData() {
        DeviceFeatureInstanceAssembleDto assembleDto = new DeviceFeatureInstanceAssembleDto();
        String strategyNum = "b754a544b41d4a7b813d2002c07bc138";
        StrategyDto strategyDto = strategyService.getStrategyDtoById(strategyNum);
        assembleDto.featureInstanceDto = strategyDto.deviceFeatureInstanceList.get(0);
        assembleDto.assembleInstanceDto = strategyDto.deviceFeatureInstanceList.get(1);
        return assembleDto;
    }
}