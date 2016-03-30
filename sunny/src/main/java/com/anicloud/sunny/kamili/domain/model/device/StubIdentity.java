package com.anicloud.sunny.kamili.domain.model.device;

import java.io.Serializable;

/**
 * Created by MRK on 2016/3/15.
 */
public class StubIdentity implements Serializable {
    private static final long serialVersionUID = -7794877560388343803L;
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
