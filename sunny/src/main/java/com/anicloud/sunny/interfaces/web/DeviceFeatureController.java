package com.anicloud.sunny.interfaces.web;

import com.anicloud.sunny.application.dto.device.DeviceAndFeatureRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureArgDto;
import com.anicloud.sunny.application.service.device.DeviceAndFeatureRelationService;
import com.anicloud.sunny.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.interfaces.web.dto.DeviceFeatureFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/10.
 */
@Controller
public class DeviceFeatureController {
    @Resource
    private DeviceFeatureService deviceFeatureService;
    @Resource
    private DeviceAndFeatureRelationService deviceAndFeatureRelationService;

    @RequestMapping(value = "/features",method = RequestMethod.GET)
    @ResponseBody
    public List<DeviceFeatureFormDto> getDeviceFeature(@RequestParam("deviceId")String deviceId){
        DeviceAndFeatureRelationDto deviceAndFeatureRelationDto = deviceAndFeatureRelationService.findByDeviceIdentificationCode(deviceId);
        return  DeviceFeatureFormDto.convertToDeviceFeatureForms(deviceAndFeatureRelationDto.deviceFeatureDtoList);
    }
}
