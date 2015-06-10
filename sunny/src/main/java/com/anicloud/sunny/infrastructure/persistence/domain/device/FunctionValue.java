package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zhaoyu on 15-6-8.
 */
@Entity
@Table
public class FunctionValue extends AbstractEntity {
    private static final long serialVersionUID = -4122196456325450932L;

    public String name;
    public String value;

    public FunctionValue() {
    }

    public FunctionValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
