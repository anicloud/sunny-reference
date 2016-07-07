package com.anicloud.sunny.application.assemble;

import com.ani.agent.service.commons.object.dto.FunctionArgumentDto;
import com.anicloud.sunny.domain.model.device.FunctionArgument;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ArgumentType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-15.
 */
public class FunctionArgumentDtoAssembler {

    private FunctionArgumentDtoAssembler() {}

    public static FunctionArgument toFunctionArgument(FunctionArgumentDto functionArgumentDto, ArgumentType argumentType) {
        if (functionArgumentDto == null) {
            return null;
        }

        FunctionArgument argument = new FunctionArgument(
                argumentType,
                functionArgumentDto.dataType,
                functionArgumentDto.name
        );
        return argument;
    }

    public static FunctionArgumentDto toDto(FunctionArgument functionArgument) {
        if (functionArgument == null) {
            return null;
        }

        FunctionArgumentDto argumentDto = new FunctionArgumentDto(
                functionArgument.name,
                functionArgument.dataType
        );
        return argumentDto;
    }

    public static List<FunctionArgument> toFunctionArgumentList(List<FunctionArgumentDto> argumentDtoList,
                                                                ArgumentType argumentType) {
        if (argumentDtoList == null) {
            return null;
        }
        List<FunctionArgument> argumentList = new ArrayList<>();
        for (FunctionArgumentDto argumentDto : argumentDtoList) {
            argumentList.add(toFunctionArgument(argumentDto, argumentType));
        }
        return argumentList;
    }

    public static List<FunctionArgumentDto> toDtoList(List<FunctionArgument> argumentSetList) {
        if (argumentSetList == null) {
            return null;
        }
        List<FunctionArgumentDto> functionArgumentDtoList = new ArrayList<>();
        for (FunctionArgument functionArgument : argumentSetList) {
            functionArgumentDtoList.add(toDto(functionArgument));
        }
        return functionArgumentDtoList;
    }
}
