package com.anicloud.sunny.infrastructure.persistence.domain.app.stub;

import com.anicloud.sunny.domain.model.app.stub.ArgumentType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Entity
@Table(name="t_stub_argument")
public class StubArgumentDao implements Serializable {
    private static final long serialVersionUID = 7083971865938766731L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column()
    public String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "argument_id", referencedColumnName = "id", nullable = true)
    public ArgumentTypeDao argumentType;

    public StubArgumentDao(String name, ArgumentTypeDao argumentType) {
        this.name = name;
        this.argumentType = argumentType;
    }

    @Override
    public String toString() {
        return "StubArgumentDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", argumentType=" + argumentType +
                '}';
    }
}
