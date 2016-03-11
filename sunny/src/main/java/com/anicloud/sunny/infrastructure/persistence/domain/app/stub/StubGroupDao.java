package com.anicloud.sunny.infrastructure.persistence.domain.app.stub;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Entity
@Table(name="t_stub_group")
public class StubGroupDao implements Serializable {
    private static final long serialVersionUID = -1114114400456203008L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column()
    public Integer groupId;
    @Column
    public String name;

    public StubGroupDao() {
    }

    public StubGroupDao(Integer id, Integer groupId, String name) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StubGroupDao{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }
}
