package com.anicloud.sunny.interfaces.facade;

import com.anicloud.sunny.interfaces.facade.dto.app.StubDto;
import com.anicloud.sunny.interfaces.facade.dto.app.StubGroupDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
@Component(value = "stubMetaServiceFacade")
public class StubMetaServiceFacadeImpl implements StubMetaServiceFacade {

    @Override
    public List<StubDto> getAllStub() {
        // TODO by CaMili
        return null;
    }

    @Override
    public StubDto getStubById(Long stubId) {
        // TODO by CaMili
        return null;
    }

    @Override
    public StubDto save(StubDto stub) {
        // TODO by CaMili
        return null;
    }

    @Override
    public void deleteStub(Long stubId) {
        // TODO by CaMili
    }

    @Override
    public StubDto updateStub(StubDto stub) {
        // TODO by CaMili
        return null;
    }

    @Override
    public List<StubGroupDto> getAllGroup() {
        // TODO by CaMili
        return null;
    }

    @Override
    public List<StubGroupDto> getByGroupIdIn(Collection<Integer> groupIds) {
        // TODO by CaMili
        return null;
    }

    @Override
    public StubGroupDto getStubGroupById(Integer groupId) {
        // TODO by CaMili
        return null;
    }

    @Override
    public StubGroupDto getStubGroupByName(String name) {
        // TODO by CaMili
        return null;
    }

    @Override
    public StubGroupDto save(StubGroupDto stubGroup) {
        // TODO by CaMili
        return null;
    }

    @Override
    public StubGroupDto update(StubGroupDto stubGroup) {
        // TODO by CaMili
        return null;
    }

    @Override
    public void deleteGroup(Integer groupId) {
        // TODO by CaMili
    }
}
