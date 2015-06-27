package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.app.AppClientDto;
import com.anicloud.sunny.domain.model.app.AppClient;

/**
 * Created by zhaoyu on 15-6-27.
 */
public class AppClientDtoAssembler {
    private AppClientDtoAssembler() {}

    public static AppClient toAppClient(AppClientDto dto) {
        if (dto == null) return null;
        AppClient appClient = new AppClient(
                dto.authorities,
                dto.authorizedGrantType,
                dto.autoApprove,
                dto.clientId,
                dto.clientName,
                dto.clientSecret,
                dto.clientServerUrl,
                dto.createTime,
                dto.description,
                dto.developerAddress,
                dto.developerEmail,
                dto.developerName,
                dto.developerPhoneNum,
                dto.language,
                dto.logoPath,
                dto.onShelf,
                dto.price,
                dto.resourceIds,
                dto.scope,
                dto.tags,
                dto.version,
                dto.webServerRedirectUri
        );
        return appClient;
    }

    public static AppClientDto toDto(AppClient appClient) {
        if (appClient == null) return null;
        AppClientDto dto = new AppClientDto(
                appClient.authorities,
                appClient.authorizedGrantType,
                appClient.autoApprove,
                appClient.clientId,
                appClient.clientName,
                appClient.clientSecret,
                appClient.clientServerUrl,
                appClient.createTime,
                appClient.description,
                appClient.developerAddress,
                appClient.developerEmail,
                appClient.developerName,
                appClient.developerPhoneNum,
                appClient.language,
                appClient.logoPath,
                appClient.onShelf,
                appClient.price,
                appClient.resourceIds,
                appClient.scope,
                appClient.tags,
                appClient.version,
                appClient.webServerRedirectUri
        );
        return dto;
    }
}
