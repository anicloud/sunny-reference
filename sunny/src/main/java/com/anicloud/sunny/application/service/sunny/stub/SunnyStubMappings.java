package com.anicloud.sunny.application.service.sunny.stub;

import java.io.Serializable;

/**
 * Created by lihui on 16-10-28.
 */
public class SunnyStubMappings implements Serializable {
    private static final long serialVersionUID = 1545230457764138103L;
    public int hashId;
    public SunnyStub sunnyStub;

    public SunnyStubMappings(int hashId, SunnyStub sunnyStub) {
        this.hashId = hashId;
        this.sunnyStub = sunnyStub;
    }
    public SunnyStubMappings() {

    }
    public SunnyStub findByHashId(int hashId) {
        if(hashId == this.hashId)
            return this.sunnyStub;
        else
            return null;
    }
    @Override
    public String toString() {
        return "SunnyStubMappings{" +
                "hashId=" + hashId +
                ", sunnyStub=" + sunnyStub +
                '}';
    }
}
