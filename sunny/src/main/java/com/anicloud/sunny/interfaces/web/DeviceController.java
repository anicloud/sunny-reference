package com.anicloud.sunny.interfaces.web;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.service.device.DeviceService;
import com.anicloud.sunny.application.service.strategy.TriggerTypeService;
import com.anicloud.sunny.infrastructure.persistence.domain.share.TriggerType;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import com.anicloud.sunny.interfaces.web.dto.DeviceFormDto;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/6/28.
 */
@Controller
public class DeviceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);

    @Resource
    private DeviceService deviceService;

    @RequestMapping(value = "/devices",method= RequestMethod.GET)
    @ResponseBody
    public List<DeviceFormDto> getDevice(@RequestParam(value = "hashUserId")String hashUserId){
        UserDto userDto = new UserDto();
        userDto.hashUserId = hashUserId;
        List<DeviceDto> deivces = deviceService.getDeviceByUser(userDto);
        return DeviceFormDto.convertToDeviceForms(deivces);
    }

    @RequestMapping(value = "/device/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void removeDevice(@PathVariable("id")String identificationCode){
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.identificationCode = identificationCode;
        deviceService.removeDevice(deviceDto);
    }

    @RequestMapping(value="/device/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public void modifyDevice(@PathVariable("id")String identificationCode,@RequestParam("deviceGroup")String deviceGroup){

    }
}
