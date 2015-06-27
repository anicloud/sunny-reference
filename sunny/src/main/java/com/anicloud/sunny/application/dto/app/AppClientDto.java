package com.anicloud.sunny.application.dto.app;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaoyu on 15-6-27.
 */
public class AppClientDto implements Serializable {
    private static final long serialVersionUID = -1180852028403254766L;

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

    public AppClientDto() {
    }

    public AppClientDto(String authorities, String authorizedGrantType, String autoApprove,
                        String clientId, String clientName, String clientSecret,
                        String clientServerUrl, Date createTime, String description,
                        String developerAddress, String developerEmail, String developerName,
                        String developerPhoneNum, String language, String logoPath, Long onShelf,
                        Double price, String resourceIds, String scope, String tags,
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
}
