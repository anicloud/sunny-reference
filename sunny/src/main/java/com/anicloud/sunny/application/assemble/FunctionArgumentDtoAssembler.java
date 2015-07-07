package com.anicloud.sunny.application.assemble;

import com.ani.cel.service.manager.agent.device.model.FunctionArgumentDto;
import com.anicloud.sunny.domain.model.device.FunctionArgument;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-15.
 */
public class FunctionArgumentDtoAssembler {

    private FunctionArgumentDtoAssembler() {}

    public static FunctionArgument toFunctionArgument(FunctionArgumentDto functionArgumentDto) {
        if (functionArgumentDto == null) {
            return null;
        }

        FunctionArgument argument = new FunctionArgument(
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

    public static Set<FunctionArgument> toFunctionArgumentSet(Set<FunctionArgumentDto> argumentDtoSet) {
        if (argumentDtoSet == null) {
            return null;
        }
        Set<FunctionArgument> argumentSet = new HashSet<FunctionArgument>(argumentDtoSet.size());
        for (FunctionArgumentDto argumentDto : argumentDtoSet) {
            argumentSet.add(toFunctionArgument(argumentDto));
        }
        return argumentSet;
    }

    public static Set<FunctionArgumentDto> toDtoSet(Set<FunctionArgument> argumentSet) {
        if (argumentSet == null) {
            return null;
        }
        Set<FunctionArgumentDto> functionArgumentDtoSet = new HashSet<FunctionArgumentDto>(argumentSet.size());
        for (FunctionArgument functionArgument : argumentSet) {
            functionArgumentDtoSet.add(toDto(functionArgument));
        }
        return functionArgumentDtoSet;
    }
}
