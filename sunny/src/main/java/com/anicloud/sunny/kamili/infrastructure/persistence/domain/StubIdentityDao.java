package com.anicloud.sunny.kamili.infrastructure.persistence.domain;

import java.io.Serializable;

/**
 * Created by MRK on 2016/3/23.
 */
public class StubIdentityDao implements Serializable {
    private static final long serialVersionUID = -5830017622623197619L;

    public Long groupId;
    public Integer stubId;

    public StubIdentityDao() {
    }

    public StubIdentityDao(Long groupId, Integer stubId) {
        this.groupId = groupId;
        this.stubId = stubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StubIdentityDao that = (StubIdentityDao) o;

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
        return "StubIdentityDao{" +
                "groupId=" + groupId +
                ", stubId=" + stubId +
                '}';
    }
}
