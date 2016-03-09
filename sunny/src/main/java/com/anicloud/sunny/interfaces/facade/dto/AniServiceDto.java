package com.anicloud.sunny.interfaces.facade.dto;

import com.anicloud.sunny.domain.model.app.AniServiceEntrance;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public class AniServiceDto implements Serializable {
    private static final long serialVersionUID = 2111255330908533568L;
    // generate all of the needed fields

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
    public boolean trusted; //to set weather the client is trusted, the field is just for grant_type = authorization_code, if false, go
    public String serviceServerUrl;
    public String logoPath;
    public Set<String> languageSet;
    public Set<String> tagSet;
    public Double price;
    public Date onShelf;
    public String description;
    public Long accountId;
    public List<AniServiceEntrance> entranceList;


    public AniServiceDto(Long id,String aniServiceId, String serviceName,
                         String version, String clientSecret, Set<String> resourceIds,
                         Set<String> scope, Set<String> authorizedGrantTypes, Collection<String> authorities,
                         String webServerRedirectUri, Integer accessTokenValidity, Integer refreshTokenValidity,
                         String autoApprove, Date registerDate, boolean archived, boolean trusted, String serviceServerUrl,
                         String logoPath, Set<String> languageSet, Set<String> tagSet, Double price, Date onShelf,
                         String description, Long accountId, List<AniServiceEntrance> entranceList) {
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




}
