package com.anicloud.sunny.domain.adapter;

import com.anicloud.sunny.domain.model.app.stub.StubGroup;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubGroupDao;

/**
 * Created by MRK on 2016/3/9.
 */
public class StubGroupAdapter {

    public StubGroupAdapter() {
    }

    public static StubGroupDao toDao(StubGroup stubGroup){
        if(stubGroup==null){
            return null;
        }
        return new StubGroupDao(
                stubGroup.id,
                stubGroup.groupId,
                stubGroup.name
        );
    }

    public static StubGroup toDomain(StubGroupDao stubGroupDao){
        if(stubGroupDao==null){
            return null;
        }
        return new StubGroup(
                stubGroupDao.id,
                stubGroupDao.groupId,
                stubGroupDao.name
        );
    }

}
