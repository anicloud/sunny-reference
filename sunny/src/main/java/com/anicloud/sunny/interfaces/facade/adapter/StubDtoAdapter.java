package com.anicloud.sunny.interfaces.facade.adapter;

import com.anicloud.sunny.domain.model.app.stub.Stub;
import com.anicloud.sunny.domain.model.app.stub.StubGroup;
import com.anicloud.sunny.interfaces.facade.dto.app.StubDto;
import com.anicloud.sunny.interfaces.facade.dto.app.StubGroupDto;

/**
 * Created by MRK on 2016/3/9.
 */
public class StubDtoAdapter {

    public StubDtoAdapter() {
    }

    public static StubDto toDto(Stub stub){
        if(stub==null){
            return null;
        }
        return new StubDto(stub.stubId,stub.name,stub.group,stub.inputArguments,stub.outputArguments);
    }

    public static Stub toDomain(StubDto stubDto){
        if(stubDto==null){
            return null;
        }
        return new Stub(stubDto.stubId,stubDto.name,stubDto.group,stubDto.inputArguments,stubDto.outputArguments);
    }

    public static StubGroupDto toDto(StubGroup stubGroup){
        if(stubGroup == null){
            return null;
        }
        return new StubGroupDto(stubGroup.groupId,stubGroup.name);
    }

    public static StubGroup toDomain(StubGroupDto stubGroupDto){
        if(stubGroupDto == null){
            return null;
        }
        return new StubGroup(null, stubGroupDto.groupId,stubGroupDto.name);
    }



}
