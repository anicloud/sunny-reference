package com.anicloud.sunny.application.dto.app;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @autor zhaoyu
 * @date 16-4-6
 * @since JDK 1.7
 */
public class AniServiceDto {

    public Long id;
    public String aniServiceId;
    public String serviceName;
    public String version;

    public String clientSecret;
    public Set<String> resourceIds;
    public Set<String> scope;
    public Set<String> authorizedGrantTypes;
    public Collection<String> authorities;

    public String webServerRedirectUri;
    public Integer accessTokenValidity;
    public Integer refreshTokenValidity;
    public String autoApprove;
    public Date registerDate;
    public boolean archived;
    public boolean trusted;

    public String serviceServerUrl;
    public String logoPath;
    public Set<String> languageSet;
    public Set<String> tagSet;
    public Double price;
    public Date onShelf;
    public String description;

    public Long accountId;
    public List<AniServiceEntranceDto> entranceList;

    public AniServiceDto() {
    }

    public AniServiceDto(String aniServiceId, String serviceName,
                         String version, String clientSecret,
                         Set<String> resourceIds, Set<String> scope,
                         Set<String> authorizedGrantTypes,
                         Collection<String> authorities,
                         String webServerRedirectUri, Integer accessTokenValidity,
                         Integer refreshTokenValidity, String autoApprove,
                         Date registerDate, boolean archived, boolean trusted,
                         String serviceServerUrl, String logoPath,
                         Set<String> languageSet, Set<String> tagSet,
                         Double price, Date onShelf, String description,
                         Long accountId, List<AniServiceEntranceDto> entranceList) {
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

    public AniServiceDto(Long id, String aniServiceId,
                         String serviceName, String version,
                         String clientSecret, Set<String> resourceIds,
                         Set<String> scope, Set<String> authorizedGrantTypes,
                         Collection<String> authorities, String webServerRedirectUri,
                         Integer accessTokenValidity, Integer refreshTokenValidity,
                         String autoApprove, Date registerDate, boolean archived,
                         boolean trusted, String serviceServerUrl, String logoPath,
                         Set<String> languageSet, Set<String> tagSet, Double price,
                         Date onShelf, String description, Long accountId,
                         List<AniServiceEntranceDto> entranceList) {
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
        return "AniServiceDto{" +
                "id=" + id +
                ", aniServiceId='" + aniServiceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", version='" + version + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", resourceIds=" + resourceIds +
                ", scope=" + scope +
                ", authorizedGrantTypes=" + authorizedGrantTypes +
                ", authorities=" + authorities +
                ", webServerRedirectUri='" + webServerRedirectUri + '\'' +
                ", accessTokenValidity=" + accessTokenValidity +
                ", refreshTokenValidity=" + refreshTokenValidity +
                ", autoApprove='" + autoApprove + '\'' +
                ", registerDate=" + registerDate +
                ", archived=" + archived +
                ", trusted=" + trusted +
                ", serviceServerUrl='" + serviceServerUrl + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", languageSet=" + languageSet +
                ", tagSet=" + tagSet +
                ", price=" + price +
                ", onShelf=" + onShelf +
                ", description='" + description + '\'' +
                ", accountId=" + accountId +
                ", entranceList=" + entranceList +
                '}';
    }

}
