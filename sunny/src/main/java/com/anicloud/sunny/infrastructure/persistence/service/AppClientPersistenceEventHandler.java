package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AppClientDao;
import com.anicloud.sunny.infrastructure.persistence.repository.app.AppClientRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 15-6-27.
 */
@Component
public class AppClientPersistenceEventHandler implements AppClientPersistenceService {
    @Resource
    private AppClientRepository appClientRepository;

    @Override
    public AppClientDao getByClientName(String clientName) {
        return this.appClientRepository.findByClientName(clientName);
    }

    @Override
    public AppClientDao save(AppClientDao appClientDao) {
        return this.appClientRepository.save(appClientDao);
    }

    @Override
    public AppClientDao modify(AppClientDao appClientDao) {
        String clientName = appClientDao.clientName;
        if (StringUtils.isNotEmpty(clientName)) {
            AppClientDao dao = this.appClientRepository.findByClientName(clientName);
            appClientDao.id = dao.id;
            this.appClientRepository.save(appClientDao);
            return appClientDao;
        }
        return null;
    }
}
