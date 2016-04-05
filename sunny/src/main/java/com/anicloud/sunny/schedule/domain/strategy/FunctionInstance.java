package com.anicloud.sunny.schedule.domain.strategy;

import com.ani.cel.service.manager.agent.app.builder.AniCommandDtoBuilder;
import com.ani.cel.service.manager.agent.app.builder.AniFunctionDtoBuilder;
import com.ani.cel.service.manager.agent.app.model.*;
import com.ani.cel.service.manager.agent.app.service.AppCommandService;
import com.ani.cel.service.manager.agent.app.service.AppCommandServiceImpl;
import com.ani.cel.service.manager.agent.core.AnicelServiceConfig;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.service.command.CommandRunServiceProxy;
import com.anicloud.sunny.application.service.holder.SpringContextHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 7/18/15.
 */

public class FunctionInstance implements Serializable {
    public String functionId;
    public String name;
    public String group;
    public List<Argument> inputList;
    public List<Argument> outputList;

    public boolean execute(String hashUserId, String deviceId) {
        List<AniFunctionArgumentDto> inputDtoList = new ArrayList<>();
        for (Argument arg : inputList) {
            inputDtoList.add(new AniFunctionArgumentDto(arg.name, arg.value));
        }

        AniFunctionDto functionDto = new AniFunctionDtoBuilder()
                .setAction("call")
                .setFunctionName(name)
                .setGroupName(group)
                .setFunctionInputArgument(inputDtoList)
                .builder();

        AniCommandDtoBuilder commandDtoBuilder = new AniCommandDtoBuilder()
                .setClientId(Constants.aniServiceDto.aniServiceId)
                .setDeviceIdentificationCode(deviceId)
                .setAniFunction(functionDto);

        CommandRunServiceProxy commandRunServiceProxy = (CommandRunServiceProxy)SpringContextHolder.getBean("commandRunServiceProxyImpl");
        AniCommandCallResultDto resultDto = commandRunServiceProxy.runCommand(commandDtoBuilder, hashUserId);
//        AniFunctionCallResultDto functionCallResultDto = resultDto.getResultDtoList().get(0);
//
        if (outputList == null) {
            outputList = new ArrayList<>();
        }
        outputList.clear();
//        for(AniFunctionArgumentDto argumentDto : functionCallResultDto.getOutput()) {
//            outputList.add(new Argument(argumentDto.getName(), argumentDto.getValue()));
//        }


        return true;
    }

    public FunctionInstance() {
    }

    public FunctionInstance(String functionId, String name, String group, List<Argument> inputList, List<Argument> outputList) {
        this.functionId = functionId;
        this.name = name;
        this.group = group;
        this.inputList = inputList;
        this.outputList = outputList;
    }
}
