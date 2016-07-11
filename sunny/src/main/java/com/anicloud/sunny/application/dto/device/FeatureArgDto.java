package com.anicloud.sunny.application.dto.device;


import com.ani.agent.service.commons.object.enumeration.DataType;

/**
 * Created by zhaoyu on 15-7-10.
 */
public class FeatureArgDto extends ArgumentDto {
    public String screenName;

    public FeatureArgDto(){
        super();
    }

    public FeatureArgDto(String screenName) {
        this.screenName = screenName;
    }

    public FeatureArgDto(DataType dataType, String name, String screenName) {
        super(dataType, name);
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return "FeatureArgDto{" +
                "screenName='" + screenName + '\'' +
                '}';
    }
}
