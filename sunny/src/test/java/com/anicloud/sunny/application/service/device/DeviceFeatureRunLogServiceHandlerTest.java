package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.builder.DeviceFeatureRunLogDtoBuilder;
import com.anicloud.sunny.application.dto.device.*;
import com.anicloud.sunny.application.dto.share.FunctionValueDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.user.UserService;
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

import java.util.List;

/**
 * Created by zhaoyu on 15-6-16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/rect-persist.xml",
        "classpath:spring/root-context.xml"
})
public class DeviceFeatureRunLogServiceHandlerTest {

    @Resource
    private DeviceFeatureRunLogService featureRunLogService;
    @Resource
    private UserService userService;
    @Resource
    private DeviceFeatureService deviceFeatureService;
    @Resource
    private DeviceService deviceService;

    private DeviceFeatureRunLogDto deviceFeatureRunLogDto;

    public ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void before() throws JsonProcessingException {
        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode("111222asdf|1212qdasdfasd");
        DeviceFeatureDto featureDto = deviceFeatureService.getDeviceFeatureByNum("3aa1831f0325477e988973e50dd74a2d");
        UserDto userDto = userService.getUserByHashUserId("4c781d51d638cf133df74e6176f839e2");

        DeviceFeatureRunLogDtoBuilder dtoBuilder = new DeviceFeatureRunLogDtoBuilder();
        dtoBuilder.setFunctionValue(new FunctionValueDto("deviceGroup", "setTemperature", "temperature", "25.5"));
        dtoBuilder.setFunctionValue(new FunctionValueDto("deviceGroup", "setTemperature", "isopen", "true"));
        dtoBuilder.setDevice(deviceDto);
        dtoBuilder.setDeviceFeature(featureDto);
        dtoBuilder.setOwner(userDto);

        deviceFeatureRunLogDto = dtoBuilder.instance();
        System.out.println(objectMapper.writeValueAsString(deviceFeatureRunLogDto));
    }

    @Ignore
    public void testSaveDeviceFeatureRunLog() throws Exception {
        deviceFeatureRunLogDto = featureRunLogService.saveDeviceFeatureRunLog(deviceFeatureRunLogDto);
        System.out.println(objectMapper.writeValueAsString(deviceFeatureRunLogDto));
    }

    @Ignore
    public void testRemoveDeviceFeatureRunLog() throws Exception {
        String logNum = "b5997f242a864a818bb94a95c5334981";
        featureRunLogService.removeDeviceFeatureRunLog(logNum);
    }

    @Ignore
    public void testGetDeviceFeatureRunLogByUser() throws Exception {
        List<DeviceFeatureRunLogDto> logDtoList = featureRunLogService.getDeviceFeatureRunLogByUser("4c781d51d638cf133df74e6176f839e2");
        System.out.println(objectMapper.writeValueAsString(logDtoList));
    }

    @Ignore
    public void testGetDeviceFeatureRunLogByDeviceAndUser() throws Exception {
        List<DeviceFeatureRunLogDto> logDtoList = featureRunLogService
                .getDeviceFeatureRunLogByDeviceAndUser("111222asdf|1212qdasdfasd", "4c781d51d638cf133df74e6176f839e2");
        System.out.println(objectMapper.writeValueAsString(logDtoList));
    }

    @Test
    public void testGetDeviceFeatureRunLogByNum() throws Exception {
        String deviceFeatureRunLogNum = "428d7909e4fe47a3a5a0a9d8ba05fad0";
        DeviceFeatureRunLogDto deviceFeatureRunLogDto = featureRunLogService.getDeviceFeatureRunLogByNum(deviceFeatureRunLogNum);
        System.out.println(objectMapper.writeValueAsString(deviceFeatureRunLogDto));
    }

    @After
    public void after() {

    }

    public DeviceFeatureRunLogDto createData()  {
        DeviceFeatureRunLogDtoBuilder dtoBuilder = new DeviceFeatureRunLogDtoBuilder();
        dtoBuilder.setFunctionValue(new FunctionValueDto("deviceGroup", "setTemperature", "temperature", "25.5"));
        dtoBuilder.setFunctionValue(new FunctionValueDto("deviceGroup", "setTemperature", "isopen", "true"));

        return dtoBuilder.instance();
    }
}