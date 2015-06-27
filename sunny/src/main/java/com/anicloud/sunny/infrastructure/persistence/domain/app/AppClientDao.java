package com.anicloud.sunny.infrastructure.persistence.domain.app;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhaoyu on 15-6-27.
 */
@Entity
@Table(name = "t_app")
public class AppClientDao extends AbstractEntity {
    private static final long serialVersionUID = 4529763911726710605L;

    @Column(name = "client_name", nullable = false, unique = true, length = 100)
    public String clientName;
    @Column(name = "client_server_url", length = 100)
    public String clientServerUrl;
    @Column(name = "price", precision = 10, scale = 2)
    public Double price;
    @Column(name = "language", length = 50)
    public String language;
    @Column(name = "tags", length = 100)
    public String tags;
    @Column(name = "logo_path", length = 100)
    public String logoPath;
    @Column(name = "on_shelf")
    public Long onShelf;

    @Column(name = "version", length = 50)
    public String version;
    @Column(name = "description", length = 200)
    public String description;

    @Column(name = "developer_name", nullable = false, length = 100)
    public String developerName;
    @Column(name = "developer_email", nullable = false, unique = true)
    public String developerEmail;
    @Column(name = "phone_num", length = 20)
    public String developerPhoneNum;
    @Column(name = "address", length = 200)
    public String developerAddress;

    @Column(name = "client_id", nullable = false, length = 100, unique = true)
    public String clientId;
    @Column(name = "resource_ids", length = 100)
    public String resourceIds;
    @Column(name = "client_secret")
    public String clientSecret;
    @Column(name = "scope", length = 100)
    public String scope;
    @Column(name = "authorized_grant_types", length = 100)
    public String authorizedGrantType;
    @Column(name = "web_server_redirect_uri", length = 200)
    public String webServerRedirectUri;
    @Column(name = "authorities", length = 100)
    public String authorities;
    @Column(name = "auto_approve", length = 100)
    public String autoApprove;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date createTime;

    public AppClientDao() {
    }

    public AppClientDao(String authorities, String authorizedGrantType, String autoApprove,
                        String clientId, String clientName, String clientSecret,
                        String clientServerUrl, Date createTime, String description,
                        String developerAddress, String developerEmail, String developerName,
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

    @Override
    public String toString() {
        return "AppClientDao{" +
                "authorities='" + authorities + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientServerUrl='" + clientServerUrl + '\'' +
                ", price=" + price +
                ", language='" + language + '\'' +
                ", tags='" + tags + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", onShelf=" + onShelf +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", developerName='" + developerName + '\'' +
                ", developerEmail='" + developerEmail + '\'' +
                ", developerPhoneNum='" + developerPhoneNum + '\'' +
                ", developerAddress='" + developerAddress + '\'' +
                ", clientId='" + clientId + '\'' +
                ", resourceIds='" + resourceIds + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", scope='" + scope + '\'' +
                ", authorizedGrantType='" + authorizedGrantType + '\'' +
                ", webServerRedirectUri='" + webServerRedirectUri + '\'' +
                ", autoApprove='" + autoApprove + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
