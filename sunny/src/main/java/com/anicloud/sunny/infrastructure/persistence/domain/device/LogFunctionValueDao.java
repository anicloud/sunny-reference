package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractFunctionValue;

import javax.persistence.*;

/**
 * Created by zhaoyu on 15-6-10.
 */
@Entity
@Table(name = "t_log_function_value")
public class LogFunctionValueDao extends AbstractFunctionValue {
    private static final long serialVersionUID = -423371624823650717L;

    public LogFunctionValueDao() {
        super();
    }

    public LogFunctionValueDao(String functionGroup, String functionName,
                               String argName, String value) {
        super(functionGroup, functionName, argName, value);
    }
}
