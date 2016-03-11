package com.anicloud.sunny.domain.adapter;

import com.anicloud.sunny.domain.model.app.stub.ArgumentType;
import com.anicloud.sunny.domain.model.app.stub.Stub;
import com.anicloud.sunny.domain.model.app.stub.StubArgument;
import com.anicloud.sunny.domain.model.app.stub.StubGroup;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MRK on 2016/3/9.
 */
public class StubDaoAdapter {

    public StubDaoAdapter() {
    }

    public static StubDao toDao(Stub stub) {
        if (stub == null) {
            return null;
        }
        List<StubArgumentDao> inArg = null;
        List<StubArgumentDao> outArg = null;
        if (stub.inputArguments != null && stub.inputArguments.size() > 0) {
            inArg = new ArrayList<>();
            System.err.println("*******************"+stub.inputArguments.size());
            for (StubArgument argument : stub.inputArguments) {
                inArg.add(toDao(argument));
            }
        }
        if (stub.outputArguments != null && stub.outputArguments.size() > 0) {
            outArg = new ArrayList<>();
            System.err.println("*******************"+stub.outputArguments.size());
            for (StubArgument argument : stub.outputArguments) {
                outArg.add(toDao(argument));
            }
        }
        return new StubDao(
                stub.stubId,
                stub.name,
                toDao(stub.group),
                inArg,
                outArg
        );
    }

    public static Stub toDomain(StubDao stubDao) {
        if (stubDao == null) {
            return null;
        }
        List<StubArgument> inArg = null;
        List<StubArgument> outArg = null;
        if(stubDao.inputArguments != null && stubDao.inputArguments.size()>0) {
            inArg = new ArrayList<>();
            for (StubArgumentDao argument : stubDao.inputArguments) {
                inArg.add(toDomain(argument));
            }
        }
        if(stubDao.outputArguments != null && stubDao.outputArguments.size()>0) {
            outArg = new ArrayList<>();
            for (StubArgumentDao argument : stubDao.outputArguments) {
                outArg.add(toDomain(argument));
            }
        }
        return new Stub(
                stubDao.stubId,
                stubDao.name,
                toDomian(stubDao.group),
                inArg,
                outArg
        );
    }

    public static StubGroup toDomian(StubGroupDao stubGroupDao) {
        if (stubGroupDao == null) {
            return null;
        }
        return new StubGroup(
                stubGroupDao.id,
                stubGroupDao.groupId,
                stubGroupDao.name
        );
    }

    public static StubGroupDao toDao(StubGroup stubGroup) {
        if (stubGroup == null) {
            return null;
        }
        return new StubGroupDao(
                stubGroup.id,
                stubGroup.groupId,
                stubGroup.name
        );
    }


    public static ArgumentType toDomain(ArgumentTypeDao argumentTypeDao) {
        if (argumentTypeDao == null) {
            return null;
        }
        return new ArgumentType(
                argumentTypeDao.type,
                toDomain(argumentTypeDao.componentType)
        );
    }

    public static ArgumentTypeDao toDao(ArgumentType argumentType) {
        if (argumentType == null) {
            return null;
        }
        return new ArgumentTypeDao(
                argumentType.type,
                toDao(argumentType.componentType)
        );
    }

    public static StubArgument toDomain(StubArgumentDao stubArgumentDao) {
        if (stubArgumentDao == null) {
            return null;
        }
        return new StubArgument(
                stubArgumentDao.name,
                toDomain(stubArgumentDao.argumentType)
        );
    }

    public static StubArgumentDao toDao(StubArgument stubArgument) {
        if (stubArgument == null) {
            return null;
        }
        return new StubArgumentDao(
                stubArgument.name,
                toDao(stubArgument.argumentType)
        );
    }
}