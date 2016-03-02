package com.anicloud.sunny.interfaces.web;

import com.anicloud.sunny.application.service.init.DeviceFeatureInitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyu on 15-7-10.
 * the interface is used to init the device feature of sunny.
 */
@Controller
public class InitController extends BaseController {

    @Value("${sunny.config.username}")
    private String username;
    @Value("${sunny.config.password}")
    private String password;

    @Resource
    private DeviceFeatureInitService deviceFeatureInitService;

    @RequestMapping(value = "/init/{username}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> initAction(HttpSession session, @PathVariable("username") String userName,
                                          @PathVariable("password") String password) {
        Map<String, String> message = new HashMap<>();
        if (session.getAttribute("status") != null && session.getAttribute("status").equals("success")) {
            message.put("status", "error");
            message.put("message", "sunny device feature has been initialization.");
            return message;
        }
        if (this.username.equals(userName) && this.password.equals(password)) {
            deviceFeatureInitService.initDeviceFeature();
            session.setAttribute("status", "success");
            message.put("status", "success");
            message.put("message", "init sunny device feature success.");

        } else {
            message.put("status", "error");
            message.put("message", "error username or password.");
        }
        return message;
    }

}
