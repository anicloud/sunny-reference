package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.dto.share.FunctionValueDto;
import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.application.dto.strategy.FeatureTriggerDto;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.service.device.DeviceService;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.infrastructure.persistence.domain.share.StrategyState;
import com.anicloud.sunny.infrastructure.persistence.domain.share.TriggerType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/rect-persist.xml",
        "classpath:spring/root-context.xml"
})
public class StrategyServiceHandlerTest {

    private static final String HASH_USER_ID = "4c781d51d638cf133df74e6176f839e2";

    @Resource
    private StrategyService strategyService;

    @Resource
    private UserService userService;
    @Resource
    private DeviceFeatureService deviceFeatureService;
    @Resource
    private DeviceService deviceService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private StrategyDto strategyDto = null;
    @Before
    public void before() throws JsonProcessingException {
        strategyDto = createData();
    }

    @Test
    public void testSaveStrategy() throws Exception {
        strategyDto = strategyService.saveStrategy(strategyDto);
        System.out.println(objectMapper.writeValueAsString(strategyDto));
    }

    @Ignore
    public void testModifyStrategy() throws Exception {
        String strategyNum = "29f008cdc2834560a36e1b9cd9bf5ad8";
        StrategyDto strategyDto = strategyService.getStrategyById(strategyNum);
        strategyDto.strategyName = "strategy001";
        strategyDto.state = StrategyState.SCHEDULING;

        strategyService.modifyStrategy(strategyDto);
    }

    @Ignore
    public void testRemoveStrategy() throws Exception {
        String strategyNum = "f85f6336b5d34fb9b029a2ac1f0ea5bb";
        strategyService.removeStrategy(strategyNum);
    }

    @Ignore
    public void testModifyStrategyState() throws Exception {
        strategyService.modifyStrategyState("29f008cdc2834560a36e1b9cd9bf5ad8", StrategyState.RUNNING);
    }

    @Ignore
    public void testGetStrategyByNum() throws Exception {
        String strategyNum = "29f008cdc2834560a36e1b9cd9bf5ad8";
        StrategyDto strategyDto = strategyService.getStrategyById(strategyNum);
        System.out.println(objectMapper.writeValueAsString(strategyDto));
    }

    @Ignore
    public void testGetStrategyByUser() throws Exception {
        List<StrategyDto> list = strategyService.getStrategyByUser(HASH_USER_ID);
        System.out.println(objectMapper.writeValueAsString(list));
    }

    @Ignore
    public void testGetStrategyByUserAndState() throws Exception {
        List<StrategyDto> strategyDtoList = strategyService.getStrategyByUserAndState(HASH_USER_ID, StrategyState.RUNNING);
        System.out.println(objectMapper.writeValueAsString(strategyDtoList));
    }

    @After
    public void after() {

    }

    private StrategyDto createData() throws JsonProcessingException {
        List<FunctionValueDto> functionValueDtoList = new ArrayList<>();
        functionValueDtoList.add(new FunctionValueDto("temperature", "25.5"));
        functionValueDtoList.add(new FunctionValueDto("isopen", "true"));

        List<FunctionValueDto> functionValueDtoList1 = new ArrayList<>();
        functionValueDtoList1.add(new FunctionValueDto("temperature", "30"));
        functionValueDtoList1.add(new FunctionValueDto("isopen", "false"));


        List<FeatureTriggerDto> triggerDtoList = new ArrayList<>();
        triggerDtoList.add(new FeatureTriggerDto(TriggerType.TIMER, "{time : 2015-06-19 12:10:30}"));

        DeviceFeatureInstanceDto instanceDto = new DeviceFeatureInstanceDto();
        instanceDto.deviceDto = deviceService.getDeviceByIdentificationCode("111222asdf|1212qdasdfasd");
        instanceDto.deviceFeatureDto = deviceFeatureService.getDeviceFeatureById("3aa1831f0325477e988973e50dd74a2d");
        instanceDto.functionValueDtoList = functionValueDtoList;
        instanceDto.triggerDtoList = triggerDtoList;

        DeviceFeatureInstanceDto instanceDto1 = new DeviceFeatureInstanceDto();
        instanceDto1.deviceDto = deviceService.getDeviceByIdentificationCode("111222asdf|1212qdasdfasd");
        instanceDto1.deviceFeatureDto = deviceFeatureService.getDeviceFeatureById("3aa1831f0325477e988973e50dd74a2d");
        instanceDto1.functionValueDtoList = functionValueDtoList1;
        instanceDto1.triggerDtoList = triggerDtoList;

        StrategyDto strategyDto = new StrategyDto();
        strategyDto.strategyName = "strategy001";
        strategyDto.state = StrategyState.SCHEDULING;
        strategyDto.description = " strategy instance";
        UserDto userDto = userService.getUserByHashUserId(HASH_USER_ID);

        strategyDto.owner = userDto;
        strategyDto.deviceFeatureInstanceList = Arrays.asList(instanceDto, instanceDto1);

        System.out.println(objectMapper.writeValueAsString(strategyDto));
        return strategyDto;
    }
}