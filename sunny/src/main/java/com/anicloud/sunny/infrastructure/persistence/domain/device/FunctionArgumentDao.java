package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DataType;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ObjectState;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by zhaoyu on 15-6-8.
 */
public class FunctionArgumentDao extends AbstractEntity{
    private static final long serialVersionUID = 5254836963082579939L;

    public String name;
    public DataType dataType;

    public ObjectState objectState;

    public FunctionArgumentDao() {
    }

    public FunctionArgumentDao(DataType dataType, String name, ObjectState objectState) {
        this.dataType = dataType;
        this.name = name;
        this.objectState = objectState;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
