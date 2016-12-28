package com.anicloud.sunny.web.controller;

import com.anicloud.sunny.application.service.strategy.TriggerTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/10.
 */
@Controller
public class TriggerController {

    @Resource
    private TriggerTypeService triggerTypeService;

    @RequestMapping(value = "/triggers",method = RequestMethod.GET)
    @ResponseBody
    public List<String> getTriggerType(){
        List<String>  triggers = triggerTypeService.getAllTriggerType();
        return triggers;
    }
}
