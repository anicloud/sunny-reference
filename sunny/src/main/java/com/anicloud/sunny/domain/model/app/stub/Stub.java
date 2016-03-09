package com.anicloud.sunny.domain.model.app.stub;

import org.springframework.beans.factory.annotation.Configurable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Configurable
public class Stub implements Serializable {
    private static final long serialVersionUID = 1557362571907132980L;

    public Integer stubId;
    public String name;
    public StubGroup group;

    public List<StubArgument> inputArguments;
    public List<StubArgument> outputArguments;

    public Stub() {
    }

    public Stub(Integer stubId, String name, StubGroup group,
                List<StubArgument> inputArguments,
                List<StubArgument> outputArguments) {
        this.stubId = stubId;
        this.name = name;
        this.group = group;
        this.inputArguments = inputArguments;
        this.outputArguments = outputArguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stub stub = (Stub) o;
        return Objects.equals(stubId, stub.stubId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stubId);
    }

    @Override
    public String toString() {
        return "Stub{" +
                "stubId=" + stubId +
                ", name='" + name + '\'' +
                ", group=" + group +
                '}';
    }
}
