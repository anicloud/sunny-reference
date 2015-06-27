package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AppClientDao;
import org.springframework.stereotype.Service;

/**
 * Created by zhaoyu on 15-6-27.
 */
@Service
public interface AppClientPersistenceService {
    public AppClientDao getByClientName(String clientName);
    public AppClientDao save(AppClientDao appClientDao);
    public AppClientDao modify(AppClientDao appClientDao);
}
