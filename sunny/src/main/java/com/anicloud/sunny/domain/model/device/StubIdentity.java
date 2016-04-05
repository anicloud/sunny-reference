package com.anicloud.sunny.domain.model.device;

import java.io.Serializable;

/**
 * @autor zhaoyu
 * @date 16-3-31
 * @since JDK 1.7
 */
public class StubIdentity implements Serializable {
    private static final long serialVersionUID = -1159824915806491196L;

    public Long groupId;
    public Integer stubId;

    public StubIdentity() {
    }

    public StubIdentity(Long groupId, Integer stubId) {
        this.groupId = groupId;
        this.stubId = stubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StubIdentity that = (StubIdentity) o;

        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        return stubId != null ? stubId.equals(that.stubId) : that.stubId == null;

    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (stubId != null ? stubId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StubIdentity{" +
                "groupId=" + groupId +
                ", stubId=" + stubId +
                '}';
    }
}
