package com.anicloud.sunny.infrastructure.persistence.domain.app;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
public class AniServiceDao implements Serializable {
    private static final long serialVersionUID = -3727816916211856412L;

    public Long id;
    public String aniServiceId;
    public String serviceName;
    public String version;
    public String clientSecret;
    public String resourceIds;
    public String scope;

    public String authorizedGrantTypes;
    public String authorities;

    public String webServerRedirectUri;
    public Integer accessTokenValidity;
    public Integer refreshTokenValidity;
    public String autoApprove;
    public Date registerDate;
    public boolean archived;
    public boolean trusted;             //to set weather the client is trusted, the field is just for grant_type = authorization_code, if false, go
    public String serviceServerUrl;
    public String logoPath;
    public String languageSet;

    public String tagSet;
    public Double price;

    public Date onShelf;
    public String description;
    public Long accountId;
    public List<AniServiceEntranceDao> entranceList;

    public AniServiceDao() {
    }

    public AniServiceDao(Long id, String aniServiceId, String serviceName,
                         String version, String clientSecret,
                         String resourceIds, String scope,
                         String authorizedGrantTypes, String authorities,
                         String webServerRedirectUri, Integer accessTokenValidity,
                         Integer refreshTokenValidity, String autoApprove,
                         Date registerDate, boolean archived, boolean trusted,
                         String serviceServerUrl, String logoPath,
                         String languageSet, String tagSet, Double price, Date onShelf,
                         String description, Long accountId, List<AniServiceEntranceDao> entranceList) {
        this.id = id;
        this.aniServiceId = aniServiceId;
        this.serviceName = serviceName;
        this.version = version;
        this.clientSecret = clientSecret;
        this.resourceIds = resourceIds;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.authorities = authorities;
        this.webServerRedirectUri = webServerRedirectUri;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
        this.autoApprove = autoApprove;
        this.registerDate = registerDate;
        this.archived = archived;
        this.trusted = trusted;
        this.serviceServerUrl = serviceServerUrl;
        this.logoPath = logoPath;
        this.languageSet = languageSet;
        this.tagSet = tagSet;
        this.price = price;
        this.onShelf = onShelf;
        this.description = description;
        this.accountId = accountId;
        this.entranceList = entranceList;
    }

    @Override
    public String toString() {
        return "AniServiceDao{" +
                "id=" + id +
                ", aniServiceId='" + aniServiceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
