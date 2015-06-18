package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.builder.DeviceDtoBuilder;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/rect-persist.xml",
        "classpath:spring/root-context.xml"
})
public class DeviceServiceHandlerTest {

    @Resource
    private DeviceService deviceService;
    @Resource
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private DeviceDto deviceDto;
    private UserDto userDto;

    @Before
    public void before() throws JsonProcessingException {
        userDto = userService.getUserByEmail("ching-zhou@anicloud.com");
        System.out.println(objectMapper.writeValueAsString(userDto));
        deviceDto = new DeviceDto();

        deviceDto.identificationCode = "11222asdfx|122qdasdfasda";
        deviceDto.name = "light002";
        deviceDto.deviceState = DeviceState.CONNECTED;
        deviceDto.deviceType = "Conditioner";
        deviceDto.deviceGroup = "Air Conditioner";

        deviceDto.owner = userDto;
    }

    @Ignore
    public void testSaveDevice() throws Exception {
        deviceDto = deviceService.saveDevice(deviceDto);
        System.out.println(objectMapper.writeValueAsString(deviceDto));
    }

    @Ignore
    public void testModifyDevice() throws Exception {
        deviceDto = deviceService.getDeviceByIdentificationCode(deviceDto.identificationCode);
        deviceDto.name = "lightxx002";
        deviceDto.deviceState = DeviceState.DISCONNECTED;
        deviceDto = deviceService.modifyDevice(deviceDto);
        Assert.assertSame(deviceDto.deviceState, DeviceState.DISCONNECTED);
    }

    @Test
    public void testRemoveDevice() throws Exception {

    }

    @Ignore
    public void testModifyDeviceState() throws Exception {
        deviceDto = deviceService.getDeviceByIdentificationCode(deviceDto.identificationCode);
        deviceService.modifyDeviceState(deviceDto, DeviceState.CONNECTED);
    }

    @Ignore
    public void testGetDeviceByUser() throws Exception {
        List<DeviceDto> deviceDtoList = deviceService.getDeviceByUser(userDto);
        Assert.assertEquals(2, deviceDtoList.size());
    }

    @Ignore
    public void testGetDeviceByUserAndGroup() throws Exception {
        List<DeviceDto> deviceDtoList = deviceService.getDeviceByUserAndGroup(userDto, "light");
        Assert.assertEquals(2, deviceDtoList.size());
    }

    @Ignore
    public void testGetDeviceByUserAndType() throws Exception {
        List<DeviceDto> deviceDtoList = deviceService.getDeviceByUserAndType(userDto, "Conditioner");
        Assert.assertEquals(1, deviceDtoList.size());
    }

    @Ignore
    public void testGetDeviceByUserAndState() throws Exception {
        List<DeviceDto> deviceDtoList = deviceService.getDeviceByUserAndState(userDto, DeviceState.CONNECTED);
        Assert.assertEquals(2, deviceDtoList.size());
    }

    @Ignore
    public void testGetUserDeviceGroupList() throws Exception {
        List<String> groupList = deviceService.getUserDeviceGroupList(userDto);
        System.out.println(groupList);
    }

    @Ignore
    public void testGetDeviceByIdentificationCode() throws Exception {
        deviceDto = deviceService.getDeviceByIdentificationCode(deviceDto.identificationCode);
        System.out.println(objectMapper.writeValueAsString(deviceDto));
    }

    @Test
    public void testDeviceDtoBuilder() throws JsonProcessingException {
        DeviceDto deviceDto = createData();
        System.out.println(objectMapper.writeValueAsString(deviceDto));
    }

    @After
    public void after() {

    }

    private DeviceDto createData() {
        userDto = userService.getUserByEmail("ching-zhou@anicloud.com");
        DeviceDtoBuilder dtoBuilder = new DeviceDtoBuilder();
        DeviceDto deviceDto = dtoBuilder.setDeviceName("air002")
                .setIdentificationCode("abcdef001|fedcsaa001")
                .setDeviceGroup("Air Conditioner")
                .setDeviceState(DeviceState.CONNECTED)
                .setDeviceType("Conditioner")
                .setOwner(userDto)
                .instance();
        return deviceDto;
    }
}