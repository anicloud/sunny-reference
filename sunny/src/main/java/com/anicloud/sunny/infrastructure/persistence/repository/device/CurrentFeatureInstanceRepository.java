package com.anicloud.sunny.infrastructure.persistence.repository.device;

import com.anicloud.sunny.infrastructure.persistence.domain.device.CurrentFeatureInstanceDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by lihui on 16-7-19.
 */
public interface CurrentFeatureInstanceRepository extends JpaRepository<CurrentFeatureInstanceDao,Long> {
    @Modifying(clearAutomatically = true)
    @Query(value="update CurrentFeatureInstanceDao c set c.deviceNum = ?1 where c.deviceId = ?2")
    public void updateCurrentFeature(Integer deviceNum,String deviceId);

    @Query(value="select c from CurrentFeatureInstanceDao c where c.deviceId = ?1")
    public List<CurrentFeatureInstanceDao> selectDeviceById(String deviceId);

}
