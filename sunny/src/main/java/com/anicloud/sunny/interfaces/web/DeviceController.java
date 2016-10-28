package com.anicloud.sunny.interfaces.web;

import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.dto.user.UserInfoDto;
import com.anicloud.sunny.application.service.device.DeviceAndUserRelationServcie;
import com.anicloud.sunny.application.service.device.DeviceService;
import com.anicloud.sunny.interfaces.web.dto.DeviceFormDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;


/**
 * Created by sirhuoshan on 2015/6/28.
 */
@Controller
public class DeviceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);

    @Resource
    private DeviceService deviceService;
    @Resource
    private DeviceAndUserRelationServcie deviceAndUserRelationServcie;
    @Resource
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    @ResponseBody
    public List<DeviceFormDto> getDevice(@RequestParam(value = "hashUserId") Long hashUserId) {

        UserDto userDto = new UserDto();
        userDto.hashUserId = hashUserId;
//        List<DeviceDto> deivces = deviceService.getDeviceByUser(userDto);
        List<DeviceAndUserRelationDto> relations = deviceAndUserRelationServcie.getRelationsByUser(userDto);
        return DeviceFormDto.convertToDeviceFormsByRelations(relations);
    }

    @RequestMapping(value = "/device/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void removeDevice(@PathVariable("id") String identificationCode) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.identificationCode = identificationCode;
        deviceService.removeDevice(deviceDto);
    }

    //    @RequestMapping(value = "/device/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public void modifyDeviceGroup(@PathVariable("id") String identificationCode, @RequestParam("deviceGroup") String deviceGroup) {
//        System.out.print(identificationCode + "  " + deviceGroup);
//        DeviceDto deviceDto = deviceService.getDeviceByIdentificationCode(identificationCode);
//        deviceDto.deviceGroup = deviceGroup;
//        deviceService.modifyDevice(deviceDto);
//    }
    @RequestMapping(value = "/device/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void modifyDevice(@PathVariable("id") String identificationCode, @Payload("device") HttpServletRequest req,
                             @CookieValue(value = Constants.SUNNY_COOKIE_USER_NAME, required = false) String currentUser) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject deviceJson = JSONObject.fromObject(sb.toString());
        UserInfoDto userInfoDto = objectMapper.readValue(currentUser, UserInfoDto.class);
        DeviceAndUserRelationDto deviceDto = deviceAndUserRelationServcie.getDeviceAndUserRelation(identificationCode, Long.parseLong(userInfoDto.hashUserId));
        Iterator it = deviceJson.keys();
        while (it.hasNext()) {
            String key = it.next().toString().trim();
            if (key.equals("deviceGroup"))
                deviceDto.deviceGroup = deviceJson.getString(key);
            if (key.equals("deviceName"))
                deviceDto.screenName = deviceJson.getString(key);
        }
        deviceAndUserRelationServcie.modifyRelation(deviceDto);
    }
}
