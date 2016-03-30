package com.anicloud.sunny.kamili.interfaces.facade.dto;

import com.ani.bus.service.commons.dto.anidevice.stub.DataType;

/**
 * Created by MRK on 2016/3/18.
 */
public class FeatureArgDto {
    public Integer id;
    public String name;
    public DataType dataType;
    public String screenName;

    public FeatureArgDto() {
    }

    public FeatureArgDto(Integer id, String name, DataType dataType, String screenName) {
        this.id = id;
        this.name = name;
        this.dataType = dataType;
        this.screenName = screenName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureArgDto that = (FeatureArgDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FeatureArgDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dataType=" + dataType +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
