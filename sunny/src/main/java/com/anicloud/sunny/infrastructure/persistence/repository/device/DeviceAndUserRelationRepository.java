package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndUserRelationDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wyf on 16-10-13.
 */
public interface DeviceAndUserRelationRepository extends JpaRepository<DeviceAndUserRelationDao, Long> {
    @Query(value = "select d from DeviceAndUserRelationDao d where d.user.hashUserId = ?1")
    List<DeviceAndUserRelationDao> findByUserId(Long hashUserId);
}
