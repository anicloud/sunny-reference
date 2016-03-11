package com.anicloud.sunny.interfaces.facade;

import com.anicloud.sunny.interfaces.facade.dto.app.StubDto;
import com.anicloud.sunny.interfaces.facade.dto.app.StubGroupDto;

import java.util.Collection;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
public interface StubMetaServiceFacade {
    // for Stub Meta
    List<StubDto> getAllStub();
    StubDto getStubById(Long stubId);

    /**
     *
     * @param stub
     * @return
     */
    StubDto save(StubDto stub);
    void deleteStub(Long stubId);
    StubDto updateStub(StubDto stub);

    // for group
    List<StubGroupDto> getAllGroup();
    List<StubGroupDto> getByGroupIdIn(Collection<Integer> groupIds);
    StubGroupDto getStubGroupById(Integer groupId);
    StubGroupDto getStubGroupByName(String name);

    StubGroupDto save(StubGroupDto stubGroup);
    StubGroupDto update(StubGroupDto stubGroup);
    void deleteGroup(Integer groupId);
}
