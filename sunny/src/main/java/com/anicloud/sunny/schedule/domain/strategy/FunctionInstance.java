package com.anicloud.sunny.schedule.domain.strategy;

import com.ani.agent.service.service.AgentTemplate;
import com.ani.agent.service.service.websocket.AniInvokable;
import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.ani.octopus.commons.message.http.Message;
import com.ani.octopus.commons.stub.dto.StubArgumentDto;
import com.ani.octopus.commons.stub.type.DataCollectionType;
import com.ani.octopus.commons.stub.type.DataPrimitiveType;
import com.ani.octopus.commons.stub.type.DataType;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.service.holder.SpringContextHolder;
import com.anicloud.sunny.domain.model.device.Device;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            AgentTemplate agentTemplate = (AgentTemplate) SpringContextHolder.getBean("agentTemplate");
            AniInvokable aniInvokable = agentTemplate.getAniInvokable(Constants.aniServiceSession);
            AniStub result = aniInvokable.invokeAniObjectSync(aniStub);
            if (result.resultMsg.getResultCode() == Message.ResultCode.OBJECT_CALL_ERROR) {
                LOGGER.error("function execute failed, stubId is {}, groupId is {},result {}.",
                        stubId, groupId, result.resultMsg.getMsg());
            } else {
                LOGGER.info("function execute success, stubId is {}, groupId is {},result {}.",
                        stubId, groupId, result);
            }
        } catch (IOException e) {
            LOGGER.error("function execute failed, stubId is {}, groupId is {},result {}.",
                stubId, groupId, e.getMessage());
            e.printStackTrace();
        } catch (EncodeException e) {
            LOGGER.error("function execute failed, stubId is {}, groupId is {},result {}.",
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

    public static List<StubArgumentDto> convert(List<Argument> inputList) {
        if (inputList != null && inputList.size() > 0) {
            List<StubArgumentDto> argumentList = new ArrayList<>();
            for (Argument argument : inputList) {
                StubArgumentDto aniArgument = new StubArgumentDto(convetValueToCorrectObject(argument.value,argument.argumentType));
                argumentList.add(aniArgument);
            }
            return argumentList;
        } else {
            return null;
        }
    }

    public static Object convetValueToCorrectObject(Object value, DataType dataType) {
        if (dataType instanceof DataPrimitiveType) {
            switch (((DataPrimitiveType) dataType).getType()){
                case INTEGER:
                    return new Integer(value.toString());
                case LONG:
                    return new Long(value.toString());
                case FLOAT:
                    return new Float(value.toString()+'f');
                case BOOLEAN:
                    return Boolean.parseBoolean(value.toString());
                case SHORT:
                    return new Short(value.toString());
                case BINARY:
                    return value.toString().getBytes();
                case CHAR:
                    return value.toString().charAt(0);
                case PERCENTAGE:
                    return new Short(value.toString());
                case STRING:
                    return value.toString();
                case OBJECT:
                    return value;
            }
        } else if(dataType instanceof DataCollectionType){
            //todo
        }
        return value;
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
