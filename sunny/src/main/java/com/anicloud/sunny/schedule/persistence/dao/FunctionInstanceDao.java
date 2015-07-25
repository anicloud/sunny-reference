package com.anicloud.sunny.schedule.persistence.dao;


import javax.persistence.*;
import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
@Entity
@Table(name = "t_schedule_function")
public class FunctionInstanceDao extends AbstractEntity {
    private static final long serialVersionUID = -1309425777012066506L;

    @Column(name = "functionId", nullable = false, length = 100)
    public String functionId;
    @Column(name = "name")
    public String name;
    @Column(name = "groupName")
    public String groupName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "function_id", referencedColumnName = "id")
    public List<ArgumentDao> outputList;

    public FunctionInstanceDao() {
    }

    public FunctionInstanceDao(String functionId, String name,
                               String groupName,
                               List<ArgumentDao> outputList) {
        this.functionId = functionId;
        this.name = name;
        this.groupName = groupName;
        this.outputList = outputList;
    }

    @Override
    public String toString() {
        return "FunctionInstanceDao{" +
                "groupName='" + groupName + '\'' +
                ", name='" + name + '\'' +
                ", functionId='" + functionId + '\'' +
                '}';
    }
}
