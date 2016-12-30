package com.anicloud.sunny.web.controller;

import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.service.device.DeviceAndUserRelationServcie;
import com.anicloud.sunny.application.service.strategy.StrategyService;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import com.anicloud.sunny.web.dto.StrategyFormDto;
import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;
import com.anicloud.sunny.web.dto.StrategyInstanceFormDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sirhuoshan on 2015/7/1.
 */
@Controller
public class StrategyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyController.class);

    @Resource
    private StrategyService strategyService;
    @Resource
    private UserService userService;
    @Resource
    private DeviceAndUserRelationServcie deviceAndUserRelationServcie;
    @Resource
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/strategies",method = RequestMethod.GET)
    @ResponseBody
    public List<StrategyInstanceFormDto> getStrategies(@RequestParam(value = "hashUserId")Long hashUserId){
        LOGGER.info("get strategy list, user id : {}.", hashUserId);
        List<StrategyDto> strategies = strategyService.getStrategyByUser(hashUserId);
        return StrategyFormDto.convertToStrategyForms(strategies);
    }

    @RequestMapping(value="/strategies/{hashUserId}/{page}/{number}")
    @ResponseBody
    public Map<String,Object> getStrategies(@PathVariable("hashUserId") Long hashUserId,@PathVariable("page") int page, @PathVariable("number") int number ){
        Map<String,Object> result = new HashMap<>();
        int count = strategyService.getCountByHashUserId(hashUserId);
        List<StrategyDto> strategyDtos = strategyService.getStrategyByUser(hashUserId,page,number);
        result.put("count",count);
        result.put("list",StrategyFormDto.convertToStrategyForms(strategyDtos));
        return result;
    }

    @RequestMapping(value = "/strategy",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveStrategy(@RequestParam(value = "hashUserId")Long hashUserId,@RequestParam(value = "isSaveModel")Boolean isSaveModel,@RequestParam(value = "strategyInstance")String strategyInstance){
        Map<String, Object> message = new HashMap<>();
        try{
            strategyInstance = strategyInstance.replaceAll("triggerValue","value");
            StrategyInstanceFormDto strategyInstanceFormDto = objectMapper.readValue(strategyInstance,StrategyInstanceFormDto.class);
            User user = userService.getUserByHashUserId(hashUserId);
            strategyService.saveStrategyInstance(StrategyInstanceFormDto.convertToStrategyInstance(strategyInstanceFormDto, user));

//            saveDeviceParam(strategyFormDto.featureList,hashUserId);
            message.put("status", "success");
        }catch (Exception e){
            message.put("status", "error");
            message.put("message", "save strategy failed");
            e.printStackTrace();
        }
        return message;
    }

    @RequestMapping(value = "/strategy/saveModel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveStrategyModel(@RequestParam(value = "hashUserId")Long hashUserId,@RequestParam(value = "strategyModel")String strategy){
        return null;
    }

    @RequestMapping(value="/strategy",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> deleteStrategy(@RequestParam("hashUserId") Long hashUserId, @RequestParam("strategyId")String strategyId){
        Map<String, String> message = new HashMap<>();
        try{
            strategyService.removeStrategy(hashUserId, strategyId);
            message.put("status", "success");
            message.put("message", "delete strategy success");
        }catch (Exception e){
            message.put("status", "error");
            message.put("message", "delete strategy failed");
            e.printStackTrace();
        }
        return message;
    }

    @RequestMapping(value="/operateStrategy",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> operateStrategy(@RequestParam(value = "strategyId") String strategyId,
                                               @RequestParam(value = "action") StrategyAction action){
        Map<String, String> message = new HashMap<>();
        try{
            strategyService.operateStrategy(strategyId,action);
            message.put("status", "success");
            message.put("message", "operate strategy success");
        }catch (Exception e){
            message.put("status", "error");
            message.put("message", "operate strategy failed");
            e.printStackTrace();
        }
        return message;
    }
}
