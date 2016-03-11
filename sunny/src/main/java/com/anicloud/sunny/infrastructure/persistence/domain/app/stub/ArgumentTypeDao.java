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
@Table(name="t_argument_type")
public class ArgumentTypeDao implements Serializable {
    private static final long serialVersionUID = 3224138632667076994L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "data_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public DataType type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "component_id", referencedColumnName = "id", nullable = true)
    public ArgumentTypeDao componentType;


    public ArgumentTypeDao(DataType type, ArgumentTypeDao componentType) {
        this.type = type;
        this.componentType = componentType;
    }

    public ArgumentTypeDao(DataType type) {
        this.type = type;
        this.componentType=null;
    }

    @Override
    public String toString() {
        return "ArgumentTypeDao{" +
                "id=" + id +
                ", type=" + type +
                ", componentType=" + componentType +
                '}';
    }
}
