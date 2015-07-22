package com.anicloud.sunny.interfaces.web;

import com.ani.cel.service.manager.agent.core.share.DeviceState;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.strategy.StrategyService;
import com.anicloud.sunny.interfaces.web.dto.DeviceFeatureInstanceFormDto;
import com.anicloud.sunny.interfaces.web.dto.DeviceFormDto;
import com.anicloud.sunny.interfaces.web.dto.StrategyFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/7/1.
 */
@Controller
public class StrategyController {
    @Resource
    private StrategyService strategyService;

    @RequestMapping(value = "/strategies",method = RequestMethod.GET)
    @ResponseBody
    public List<StrategyFormDto> getStrategies(@RequestParam(value = "hashUserId")String hashUserId){
        List<StrategyDto> strategies = strategyService.getStrategyByUser(hashUserId);

        StrategyFormDto strategyFormDto = new StrategyFormDto();
        strategyFormDto.strategyId  = "1";
        strategyFormDto.strategyName = "strategy1";
        strategyFormDto.strategyDescription = "";
        strategyFormDto.strategyStage = "2";

        List<DeviceFeatureInstanceFormDto> featureList = new ArrayList<>();

        DeviceFeatureInstanceFormDto deviceFeatureInstanceFormDto1 = new DeviceFeatureInstanceFormDto();

        deviceFeatureInstanceFormDto1.featureId = "1";
        deviceFeatureInstanceFormDto1.featureName="调节空调温度1";

        DeviceFormDto deviceFormDto = new DeviceFormDto();
        deviceFormDto.id = "12345";
        deviceFormDto.name="空调";
        deviceFormDto.deviceState = DeviceState.CONNECTED;
        deviceFormDto.deviceType = "";
        deviceFormDto.deviceGroup ="客厅";

        deviceFeatureInstanceFormDto1.device = deviceFormDto;

        featureList.add(deviceFeatureInstanceFormDto1);

        strategyFormDto.featureList = featureList;
        List<StrategyFormDto> list = new ArrayList<>();
        list.add(strategyFormDto);
        return list;
        //return StrategyFormDto.convertToStrategyForms(strategies);
    }

    @RequestMapping(value = "/strategy",method = RequestMethod.POST)
    @ResponseBody
    public void saveStrategy(StrategyFormDto strategyFormDto,@RequestParam(value = "hashUserId")String hashUserId){
        UserDto userDto = new UserDto();
        userDto.hashUserId = hashUserId;
        strategyService.saveStrategy(StrategyFormDto.convertToStrategyDto(strategyFormDto,userDto));
    }
}
