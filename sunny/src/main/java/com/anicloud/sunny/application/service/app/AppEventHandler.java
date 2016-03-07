package com.anicloud.sunny.application.service.app;

import com.anicloud.sunny.application.assemble.AppClientDtoAssembler;
import com.anicloud.sunny.application.dto.app.AppClientDto;
import com.anicloud.sunny.domain.model.app.AppClient;
import com.anicloud.sunny.infrastructure.persistence.service.app.AppClientPersistenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 15-6-27.
 */
@Service
@Transactional
public class AppEventHandler implements AppService {
    @Resource
    private AppClientPersistenceService clientPersistenceService;

    @Override
    public AppClientDto save(AppClientDto dto) {
        AppClient client = AppClient.save(clientPersistenceService, AppClientDtoAssembler.toAppClient(dto));
        return AppClientDtoAssembler.toDto(client);
    }

    @Override
    public AppClientDto modify(AppClientDto dto) {
        AppClient client = AppClient.modify(clientPersistenceService, AppClientDtoAssembler.toAppClient(dto));
        return AppClientDtoAssembler.toDto(client);
    }

    @Override
    @Transactional(readOnly = true)
    public AppClientDto findByClientName(String clientName) {
        AppClient client = AppClient.getAppClientByClientName(clientPersistenceService, clientName);
        return AppClientDtoAssembler.toDto(client);
    }
}
