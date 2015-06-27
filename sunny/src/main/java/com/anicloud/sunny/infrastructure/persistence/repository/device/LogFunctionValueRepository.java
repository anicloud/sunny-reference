package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.LogFunctionValueDao;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaoyu on 15-6-12.
 */
public interface LogFunctionValueRepository extends CrudRepository<LogFunctionValueDao, Long> {
}
