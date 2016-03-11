package com.anicloud.sunny.infrastructure.persistence.domain.app.stub;

import com.anicloud.sunny.domain.model.app.stub.StubArgument;
import com.anicloud.sunny.domain.model.app.stub.StubGroup;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */

@Entity
@Table(name="t_stub")
public class StubDao implements Serializable {
    private static final long serialVersionUID = 228792224516960232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column()
    public Integer stubId;
    @Column(length = 50)
    public String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = true)
    public StubGroupDao group;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "stub_inarg_id", referencedColumnName = "id")
    public List<StubArgumentDao> inputArguments;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "stub_outarg_id", referencedColumnName = "id")
    public List<StubArgumentDao> outputArguments;

    public StubDao(Integer stubId, String name, StubGroupDao group, List<StubArgumentDao> inputArguments, List<StubArgumentDao> outputArguments) {
        this.stubId = stubId;
        this.name = name;
        this.group = group;
        this.inputArguments = inputArguments;
        this.outputArguments = outputArguments;
    }

    @Override
    public String toString() {
        return "StubDao{" +
                "id=" + id +
                ", stubId=" + stubId +
                ", name='" + name + '\'' +
                '}';
    }
}
