package com.anicloud.sunny.infrastructure.persistence.domain.app;

import com.anicloud.sunny.domain.model.app.AniServiceEntrance;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Entity
@Table(name = "t_ani_service")
public class AniServiceDao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "aniServiceId", nullable = false, length = 100)
    public String aniServiceId;

    @Column(name="serviceName", length=50)
    public String serviceName;

    @Column(name="version", length=50)
    public String version;

    @Column(name="clientSecret", length=100)
    public String clientSecret;


    @Column(name = "resourceIds", length = 100)
    public String resourceIds;

    @Column(name = "scope", length = 50)
    public String scope;

    @Column(name = "authorizedGrantTypes", length = 100)
    public String authorizedGrantTypes;

    @Column(name = "authorities")
    public String authorities;

    @Column(name="webServerRedirectUri", length=50)
    public String webServerRedirectUri;

    @Column(length = 100)
    public Integer accessTokenValidity;

    @Column(length = 100)
    public Integer refreshTokenValidity;

    @Column(name="autoAPProve",length = 100)
    public String autoApprove;

    @Column(name = "registerDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date registerDate;

    @Column()
    public boolean archived;

    @Column()
    public boolean trusted; //to set weather the client is trusted, the field is just for grant_type = authorization_code, if false, go

    @Column(name="serviceServerUrl",length = 100)
    public String serviceServerUrl;
    @Column(name="logoPath",length = 100)
    public String logoPath;

    @Column(length = 100)
    public String languageSet;

    @Column()
    public String tagSet;

    @Column(name = "price", precision = 10, scale = 2)
    public Double price;

    @Column(name = "onShelf")
    @Temporal(TemporalType.TIMESTAMP)
    public Date onShelf;

    @Column(name = "description",length = 100)
    public String description;

    @Column(length = 100)
    public Long accountId;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "aniServiceId", referencedColumnName = "id")
    public List<AniServiceEntranceDao> entranceList;

    public AniServiceDao() {
    }

    public AniServiceDao(Long id,String aniServiceId, String serviceName, String version,
                         String clientSecret, String resourceIds, String scope,
                         String authorizedGrantTypes, String authorities,
                         String webServerRedirectUri, Integer accessTokenValidity,
                         Integer refreshTokenValidity, String autoApprove,
                         Date registerDate, boolean archived, boolean trusted,
                         String serviceServerUrl, String logoPath, String languageSet,
                         String tagSet, Double price, Date onShelf, String description,
                         Long accountId, List<AniServiceEntranceDao> entranceList) {
        this.id=id;
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
                ", version='" + version + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", resourceIds='" + resourceIds + '\'' +
                ", scope='" + scope + '\'' +
                ", authorizedGrantTypes='" + authorizedGrantTypes + '\'' +
                ", authorities='" + authorities + '\'' +
                ", webServerRedirectUri='" + webServerRedirectUri + '\'' +
                ", accessTokenValidity=" + accessTokenValidity +
                ", refreshTokenValidity=" + refreshTokenValidity +
                ", autoApprove='" + autoApprove + '\'' +
                ", registerDate=" + registerDate +
                ", archived=" + archived +
                ", trusted=" + trusted +
                ", serviceServerUrl='" + serviceServerUrl + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", languageSet='" + languageSet + '\'' +
                ", tagSet='" + tagSet + '\'' +
                ", price=" + price +
                ", onShelf=" + onShelf +
                ", description='" + description + '\'' +
                ", accountId=" + accountId +
                ", entranceList=" + entranceList +
                '}';
    }
}
