package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.share.FunctionValueDto;
import com.anicloud.sunny.domain.model.share.FunctionValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-17.
 */
public class FunctionValueDtoAssembler {
    private FunctionValueDtoAssembler() {}

    public static FunctionValue toFunctionValue(FunctionValueDto functionValueDto) {
        if (functionValueDto == null) {
            return null;
        }

        FunctionValue functionValue = new FunctionValue(
                functionValueDto.functionGroup,
                functionValueDto.functionName,
                functionValueDto.argName,
                functionValueDto.value
        );
        return functionValue;
    }

    public static FunctionValueDto toFunctionValueDto(FunctionValue functionValue) {
        if (functionValue == null) {
            return null;
        }

        FunctionValueDto valueDto = new FunctionValueDto(
                functionValue.functionGroup,
                functionValue.functionName,
                functionValue.argName,
                functionValue.value
        );
        return valueDto;
    }

    public static List<FunctionValue> toFunctionValueList(List<FunctionValueDto> functionValueDtoList) {
        List<FunctionValue> functionValueList = new ArrayList<>();
        for (FunctionValueDto valueDto : functionValueDtoList) {
            functionValueList.add(toFunctionValue(valueDto));
        }
        return functionValueList;
    }

    public static List<FunctionValueDto> toFunctionValueDtoList(List<FunctionValue> valueList) {
        List<FunctionValueDto> valueDtoList = new ArrayList<>();
        for (FunctionValue functionValue : valueList) {
            valueDtoList.add(toFunctionValueDto(functionValue));
        }
        return valueDtoList;
    }
}
