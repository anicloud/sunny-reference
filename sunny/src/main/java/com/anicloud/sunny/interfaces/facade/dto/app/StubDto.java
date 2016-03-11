package com.anicloud.sunny.interfaces.facade.dto.app;

import com.anicloud.sunny.domain.model.app.stub.StubArgument;
import com.anicloud.sunny.domain.model.app.stub.StubGroup;

import java.io.Serializable;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
public class StubDto implements Serializable {
    private static final long serialVersionUID = 4931896716442965695L;

    public Integer stubId;
    public String name;
    public StubGroup group;
    public List<StubArgument> inputArguments;
    public List<StubArgument> outputArguments;

    public StubDto(Integer stubId, String name, StubGroup group, List<StubArgument> inputArguments, List<StubArgument> outputArguments) {
        this.stubId = stubId;
        this.name = name;
        this.group = group;
        this.inputArguments = inputArguments;
        this.outputArguments = outputArguments;
    }

    @Override
    public String toString() {
        return "StubDto{" +
                "stubId=" + stubId +
                ", name='" + name + '\'' +
                ", group=" + group +
                '}';
    }
}
