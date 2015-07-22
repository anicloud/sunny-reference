package com.anicloud.sunny.schedule.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by huangbin on 7/19/15.
 */
@Entity
@Table(name = "t_schedule_argument")
public class ArgumentDao extends AbstractEntity{
    private static final long serialVersionUID = 3414141041024144320L;

    @Column(name = "name", length = 50)
    public String name;
    @Column(name = "value", length = 200)
    public String value;

    public ArgumentDao() {
    }

    public ArgumentDao(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ArgumentDao{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
