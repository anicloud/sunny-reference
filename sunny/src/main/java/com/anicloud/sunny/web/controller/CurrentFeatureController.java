package com.anicloud.sunny.web.controller;

import com.anicloud.sunny.application.service.strategy.CurrentFeatureService;
import com.anicloud.sunny.infrastructure.persistence.domain.device.CurrentFeatureInstanceDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by lihui on 16-7-20.
 */
@Controller
public class CurrentFeatureController {
    @Resource
    private CurrentFeatureService service;
    @RequestMapping(value = "/updateCurrentFeature/{featureid}/{deviceid}/{hashuserid}", method = RequestMethod.GET)
    public void updateCurrentFeature(@PathVariable("featureid") String featureId, @PathVariable("deviceid") String deviceId, @PathVariable("hashuserid") Long hashUserId) {
        CurrentFeatureInstanceDao dao = service.findCurrentFeatureByDeviceId(deviceId);
        if(dao != null) {
            service.updateCurrentFeatureInstance(dao.deviceNum+1,dao.deviceId);
        } else {
            CurrentFeatureInstanceDao temp = new CurrentFeatureInstanceDao(featureId,deviceId,1,hashUserId);
            service.saveCurrentFeature(temp);
        }

    }
}
