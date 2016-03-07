package com.anicloud.sunny.domain.model.app;

import com.anicloud.sunny.infrastructure.persistence.service.app.AniServicePersistService;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.Resource;
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
@Configurable
public class AniService implements Serializable {
    private static final long serialVersionUID = 237465243070540574L;

    @Resource
    private AniServicePersistService aniServicePersistService;

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

    public AniService() {
    }
}
