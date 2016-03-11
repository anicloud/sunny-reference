package com.anicloud.sunny.domain.model.app.stub;

import com.anicloud.sunny.domain.adapter.StubDaoAdapter;
import com.anicloud.sunny.domain.adapter.StubGroupAdapter;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubGroupDao;
import com.anicloud.sunny.infrastructure.persistence.service.app.stub.StubGroupPersistService;
import com.anicloud.sunny.infrastructure.persistence.service.app.stub.StubPersistService;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Objects;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Configurable
public class StubGroup implements Serializable {
    private static final long serialVersionUID = 4026107055349990977L;

    @Resource
    private StubGroupPersistService stubGroupPersistService;
    public Integer id;
    public Integer groupId;
    public String name;

    public StubGroup() {
    }

    public StubGroup(Integer id, Integer groupId, String name) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
    }

    public StubGroup save() {
        StubGroupDao stubGroupDao = StubGroupAdapter.toDao(this);
        stubGroupPersistService.save(stubGroupDao);
        return StubGroupAdapter.toDomain(stubGroupDao);
    }

    public StubGroup update() {
        StubGroupDao stubGroupDao = StubGroupAdapter.toDao(this);
        stubGroupPersistService.update(stubGroupDao);
        return StubGroupAdapter.toDomain(stubGroupDao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StubGroup stubGroup = (StubGroup) o;
        return Objects.equals(groupId, stubGroup.groupId) &&
                Objects.equals(name, stubGroup.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, name);
    }

    @Override
    public String toString() {
        return "StubGroup{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }
}
