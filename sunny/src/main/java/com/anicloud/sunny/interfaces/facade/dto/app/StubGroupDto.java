package com.anicloud.sunny.interfaces.facade.dto.app;

import java.io.Serializable;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
public class StubGroupDto implements Serializable {
    private static final long serialVersionUID = -4680642694254117679L;

    public Integer groupId;
    public String name;

    public StubGroupDto(Integer groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StubGroupDto{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }
}
