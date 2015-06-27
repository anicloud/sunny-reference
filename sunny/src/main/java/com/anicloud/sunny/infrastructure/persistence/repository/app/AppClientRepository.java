package com.anicloud.sunny.infrastructure.persistence.repository.app;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AppClientDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaoyu on 15-6-27.
 */
public interface AppClientRepository extends CrudRepository<AppClientDao, Long> {
    @Query(value = "select a from AppClientDao a where a.clientName = ?1")
    public AppClientDao findByClientName(String clientName);
}
