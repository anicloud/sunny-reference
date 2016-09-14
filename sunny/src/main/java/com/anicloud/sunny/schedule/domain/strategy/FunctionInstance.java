package com.anicloud.sunny.schedule.domain.strategy;

import com.ani.agent.service.service.AgentTemplate;
import com.ani.agent.service.service.websocket.AniInvokable;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.service.agent.AniStubRunProxy;
import com.anicloud.sunny.application.service.holder.SpringContextHolder;
import com.anicloud.sunny.domain.model.device.Device;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 7/18/15.
 */

public class FunctionInstance implements Serializable {
    private final static Logger LOGGER = LoggerFactory.getLogger(FunctionInstance.class);

    public String functionId;
    public Integer stubId;
    public Long groupId;
    public String name;
    public List<Argument> inputList;
    public List<Argument> outputList;

    public synchronized boolean execute(Long hashUserId, String deviceId) {

        //AniStubRunProxy aniStubRunProxy = (AniStubRunProxy)SpringContextHolder
         //       .getBean("aniStubRunProxy");
        try {
            AniStub aniStub = new AniStub(
                    fetchDeviceMasterId(deviceId),
                    fetchDeviceSlaveId(deviceId),
                    hashUserId,
                    groupId,
                    stubId,
                    convert(inputList)
            );
            AgentTemplate agentTemplate = (AgentTemplate)SpringContextHolder.getBean("agentTemplate");
            AniInvokable aniInvokable = agentTemplate.getAniInvokable(Constants.aniServiceSession);
            List<com.ani.bus.service.commons.dto.anistub.Argument> argumentList = aniInvokable.invokeAniObjectSync(aniStub);
            LOGGER.info("function execute success, stubId is {}, groupId is {},result {}.",
                    stubId, groupId, argumentList);
        } catch (IOException e) {
            LOGGER.error("function execute success, stubId is {}, groupId is {},result {}.",
                stubId, groupId, e.getMessage());
            e.printStackTrace();
        } catch (EncodeException e) {
            LOGGER.error("function execute success, stubId is {}, groupId is {},result {}.",
                    stubId, groupId, e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    public FunctionInstance() {
    }

    public FunctionInstance(String functionId, Integer stubId,
                            Long groupId, String name,
                            List<Argument> inputList, List<Argument> outputList) {
        this.functionId = functionId;
        this.stubId = stubId;
        this.groupId = groupId;
        this.name = name;
        this.inputList = inputList;
        this.outputList = outputList;
    }

    public static List<com.ani.bus.service.commons.dto.anistub.Argument> convert(List<Argument> inputList) {
        if (inputList != null && inputList.size() > 0) {
            List<com.ani.bus.service.commons.dto.anistub.Argument> argumentList = new ArrayList<>();
            for (Argument argument : inputList) {
                com.ani.bus.service.commons.dto.anistub.Argument aniArgument =
                        new com.ani.bus.service.commons.dto.anistub.Argument(
                            argument.name,
                            argument.argumentType,
                            argument.value
                );
                argumentList.add(aniArgument);
            }
            return argumentList;
        } else {
            return null;
        }
    }

    public static Integer fetchDeviceSlaveId(String deviceId) {
        if (StringUtils.isNotEmpty(deviceId)) {
            String[] arr = deviceId.split(Device.DEVICE_CODE_SEPARATOR);
            return Integer.parseInt(arr[1]) == -1?null: Integer.parseInt(arr[1]);
        } else {
            throw new IllegalArgumentException("device id is null.");
        }
    }

    public static Long fetchDeviceMasterId(String deviceId) {
        if (StringUtils.isNotEmpty(deviceId)) {
            String[] arr = deviceId.split(Device.DEVICE_CODE_SEPARATOR);
            return Long.parseLong(arr[0]);
        } else {
            throw new IllegalArgumentException("device id is null.");
        }
    }
}
