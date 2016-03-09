package com.anicloud.sunny.domain.model.app.stub;

import org.springframework.beans.factory.annotation.Configurable;

import java.io.Serializable;
import java.util.Objects;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Configurable
public class StubGroup implements Serializable {
    private static final long serialVersionUID = 4026107055349990977L;

    public Integer groupId;
    public String name;

    public StubGroup() {
    }

    public StubGroup(Integer groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StubGroup stubGroup = (StubGroup) o;
        return Objects.equals(groupId, stubGroup.groupId) &&
                Objects.equals(name, stubGroup.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, name);
    }

    @Override
    public String toString() {
        return "StubGroup{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }
}
