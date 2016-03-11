package com.anicloud.sunny.interfaces.facade;

import com.anicloud.sunny.application.service.app.stub.StubGroupService;
import com.anicloud.sunny.application.service.app.stub.StubGroupServiceImpl;
import com.anicloud.sunny.application.service.app.stub.StubService;
import com.anicloud.sunny.application.service.app.stub.StubServiceImpl;
import com.anicloud.sunny.domain.adapter.StubDaoAdapter;
import com.anicloud.sunny.domain.adapter.StubGroupAdapter;
import com.anicloud.sunny.domain.model.app.stub.ArgumentType;
import com.anicloud.sunny.domain.model.app.stub.Stub;
import com.anicloud.sunny.domain.model.app.stub.StubArgument;
import com.anicloud.sunny.domain.model.app.stub.StubGroup;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.DataType;
import com.anicloud.sunny.interfaces.facade.adapter.StubDtoAdapter;
import com.anicloud.sunny.interfaces.facade.dto.app.StubDto;
import com.anicloud.sunny.interfaces.facade.dto.app.StubGroupDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
@Component(value = "stubMetaServiceFacade")
public class StubMetaServiceFacadeImpl implements StubMetaServiceFacade {

    @Resource
    private StubService stubService;
    @Resource
    private StubGroupService stubGroupService;

    @Override
    public List<StubDto> getAllStub() {
        List<StubDto> stubDtos = null;
        for (Stub stub : stubService.getAll()) {
            stubDtos.add(StubDtoAdapter.toDto(stub));
        }
        return stubDtos;
    }

    @Override
    public StubDto getStubById(Long stubId) {
        return StubDtoAdapter.toDto(stubService.getById(stubId));
    }

    @Override
    public StubDto save(StubDto stubDto) {
        Stub stub = StubDtoAdapter.toDomain(stubDto);
        stub = stubService.save(stub);
        return StubDtoAdapter.toDto(stub);
    }

    @Override
    public void deleteStub(Long stubId) {
        stubService.delete(stubId);
    }

    @Override
    public StubDto updateStub(StubDto stubDto) {
        stubService.update(StubDtoAdapter.toDomain(stubDto));
        return stubDto;
    }

    @Override
    public List<StubGroupDto> getAllGroup() {
        List<StubGroupDto> stubGroupDtos = null;
        for (StubGroup stubGroup : stubGroupService.getAll()) {
            stubGroupDtos.add(StubDtoAdapter.toDto(stubGroup));
        }
        return stubGroupDtos;
    }

    @Override
    public List<StubGroupDto> getByGroupIdIn(Collection<Integer> groupIds) {
        List<StubGroupDto> stubGroupDtos = null;
        for (StubGroup stubGroup : stubGroupService.getByGroupIdIn(groupIds)) {
            stubGroupDtos.add(StubDtoAdapter.toDto(stubGroup));
        }
        return stubGroupDtos;
    }

    @Override
    public StubGroupDto getStubGroupById(Integer groupId) {
        return StubDtoAdapter.toDto(stubGroupService.getById(groupId));
    }

    @Override
    public StubGroupDto getStubGroupByName(String name) {
        return StubDtoAdapter.toDto(stubGroupService.getByName(name));
    }

    @Override
    public StubGroupDto save(StubGroupDto stubGroupDto) {
        stubGroupService.save(StubDtoAdapter.toDomain(stubGroupDto));
        return stubGroupDto;
    }

    @Override
    public StubGroupDto update(StubGroupDto stubGroupDto) {
        stubGroupService.update(StubDtoAdapter.toDomain(stubGroupDto));
        return stubGroupDto;
    }

    @Override
    public void deleteGroup(Integer groupId) {
        stubGroupService.delete(groupId);
    }

}
