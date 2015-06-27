package com.anicloud.sunny.domain.model.app;

import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AppClientDao;
import com.anicloud.sunny.infrastructure.persistence.service.AppClientPersistenceService;

import java.util.Date;

/**
 * Created by zhaoyu on 15-6-27.
 */
public class AppClient extends AbstractDomain {
    private static final long serialVersionUID = -8592580607178299910L;

    public String clientName;
    public String clientServerUrl;
    public Double price;
    public String language;
    public String tags;
    public String logoPath;
    public Long onShelf;
    public String version;
    public String description;

    public String developerName;
    public String developerEmail;
    public String developerPhoneNum;
    public String developerAddress;

    public String clientId;
    public String resourceIds;
    public String clientSecret;
    public String scope;
    public String authorizedGrantType;
    public String webServerRedirectUri;
    public String authorities;
    public String autoApprove;
    public Date createTime;

    public AppClient() {
    }

    public AppClient(String authorities, String authorizedGrantType,
                     String autoApprove, String clientId,
                     String clientName, String clientSecret,
                     String clientServerUrl, Date createTime,
                     String description, String developerAddress,
                     String developerEmail, String developerName,
                     String developerPhoneNum, String language,
                     String logoPath, Long onShelf, Double price,
                     String resourceIds, String scope, String tags,
                     String version, String webServerRedirectUri) {
        this.authorities = authorities;
        this.authorizedGrantType = authorizedGrantType;
        this.autoApprove = autoApprove;
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSecret = clientSecret;
        this.clientServerUrl = clientServerUrl;
        this.createTime = createTime;
        this.description = description;
        this.developerAddress = developerAddress;
        this.developerEmail = developerEmail;
        this.developerName = developerName;
        this.developerPhoneNum = developerPhoneNum;
        this.language = language;
        this.logoPath = logoPath;
        this.onShelf = onShelf;
        this.price = price;
        this.resourceIds = resourceIds;
        this.scope = scope;
        this.tags = tags;
        this.version = version;
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public static AppClient save(AppClientPersistenceService persistenceService, AppClient appClient) {
        AppClientDao dao = persistenceService.save(toDao(appClient));
        return toAppClient(dao);
    }

    public static AppClient modify(AppClientPersistenceService persistenceService, AppClient appClient) {
        AppClientDao dao = persistenceService.modify(toDao(appClient));
        return toAppClient(dao);
    }

    public static AppClient getAppClientByClientName(AppClientPersistenceService persistenceService, String clientName) {
        AppClientDao dao = persistenceService.getByClientName(clientName);
        return toAppClient(dao);
    }

    public static AppClient toAppClient(AppClientDao dao) {
        AppClient appClient = new AppClient(
                dao.authorities,
                dao.authorizedGrantType,
                dao.autoApprove,
                dao.clientId,
                dao.clientName,
                dao.clientSecret,
                dao.clientServerUrl,
                dao.createTime,
                dao.description,
                dao.developerAddress,
                dao.developerEmail,
                dao.developerName,
                dao.developerPhoneNum,
                dao.language,
                dao.logoPath,
                dao.onShelf,
                dao.price,
                dao.resourceIds,
                dao.scope,
                dao.tags,
                dao.version,
                dao.webServerRedirectUri
        );
        return appClient;
    }

    public static AppClientDao toDao(AppClient appClient) {
        AppClientDao clientDao = new AppClientDao(
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
        return clientDao;
    }
}
