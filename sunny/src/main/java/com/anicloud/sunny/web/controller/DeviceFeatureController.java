package com.anicloud.sunny.web.controller;

import com.anicloud.sunny.application.dto.device.DeviceAndFeatureRelationDto;
import com.anicloud.sunny.application.service.device.DeviceAndFeatureRelationService;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.application.service.device.DeviceService;
import com.anicloud.sunny.web.dto.DeviceAndFeatureRelationFromDto;
import com.anicloud.sunny.web.dto.DeviceFeatureFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/10.
 */
@Controller
public class DeviceFeatureController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceFeatureController.class);
    @Resource
    private DeviceService deviceService;
    @Resource
    private DeviceFeatureService deviceFeatureService;
    @Resource
    private DeviceAndFeatureRelationService deviceAndFeatureRelationService;

    @RequestMapping(value = "/features",method = RequestMethod.GET)
    @ResponseBody
    public List<DeviceAndFeatureRelationFromDto> getDeviceFeatures(HttpServletRequest request){
        List<DeviceAndFeatureRelationDto> deviceAndFeatures = new ArrayList<>();
        deviceAndFeatures = deviceAndFeatureRelationService.findAll();
        return DeviceAndFeatureRelationFromDto.convertToDeviceAndFeatureRelationFroms(deviceAndFeatures);
    }

    @RequestMapping(value = "/feature",method = RequestMethod.GET)
    @ResponseBody
    public List<DeviceFeatureFormDto> getDeviceFeatureById(@RequestParam("deviceId")String deviceId){
        DeviceAndFeatureRelationDto deviceAndFeatureRelationDto = deviceAndFeatureRelationService.findByDeviceIdentificationCode(deviceId);
        return  DeviceFeatureFormDto.convertToDeviceFeatureForms(deviceAndFeatureRelationDto.deviceFeatureDtoList);
    }

    @RequestMapping("/featuresOfUser")
    @ResponseBody
    public List<DeviceAndFeatureRelationFromDto> getDeviceFeaturesByUser(@RequestParam("hashUserId")Long hashUserId) {
        List<DeviceAndFeatureRelationDto> dtos = deviceAndFeatureRelationService.findByHashUserId(hashUserId);
        return DeviceAndFeatureRelationFromDto.convertToDeviceAndFeatureRelationFroms(dtos);
    }
}
