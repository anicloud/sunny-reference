package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.application.dto.strategy.DeviceFeatureInstanceDto;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.domain.model.strategy.Strategy;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.StrategyDao;
import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import com.anicloud.sunny.schedule.dto.StrategyInstanceDto;


import java.util.List;

/**
 * Created by zhaoyu on 15-6-14.
 */
public interface StrategyService {
    /**
     * save the strategyDto and send the strategyInstanceDto into Schedule
     * @param strategyInstance
     * @return
     */
    public void initStrategyInstance(StrategyInstance strategyInstance);

    /**
     * for schedule to save the running info of the strategy
     * @param strategyDto
     */
    public void saveStrategy(StrategyDto strategyDto);
    public void saveStrategyInstance(StrategyInstance strategyInstance);
    public void operateStrategy(String strategyInstanceId, StrategyAction action);
    /**
     * just can modify the strategyName, description
     * @param strategyDto
     * @return
     */
    public StrategyDto modifyStrategy(StrategyDto strategyDto);
    public void removeStrategy(Long hashUserId, String strategyId);

    public StrategyDto getStrategyDtoById(String strategyId);
    public Strategy getStrategyById(String strategyId);
    public List<StrategyDto> getStrategyByUser(Long hashUserId);
    List<StrategyDto> getStrategyByUser(Long hashUserId,int page,int number);
    int getCountByHashUserId(Long hashUserId);

    // method for device feature
    /**
     * deviceFeatureInstanceDto do not need trigger
     * @param userDto
     * @param deviceFeatureInstanceDto
     */
    void runDeviceFeature(UserDto userDto, DeviceFeatureInstanceDto deviceFeatureInstanceDto);
    List<StrategyInstance> getRunningStrategy();
    StrategyDao findByStrategyId(String strategyId);
}
