package com.anicloud.sunny.domain.model.app;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.anicloud.sunny.infrastructure.persistence.service.app.AniServicePersistService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Configurable
public class AniService implements Serializable {
    private static final long serialVersionUID = 237465243070540574L;

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

    public AniService() {
    }

    public AniService(Long id,String aniServiceId, String serviceName, String version,
                      String clientSecret, Set<String> resourceIds, Set<String> scope,
                      Set<String> authorizedGrantTypes, Collection<String> authorities,
                      String webServerRedirectUri, Integer accessTokenValidity,
                      Integer refreshTokenValidity, String autoApprove, Date registerDate,
                      boolean archived, boolean trusted, String serviceServerUrl,
                      String logoPath, Set<String> languageSet, Set<String> tagSet,
                      Double price, Date onShelf, String description, Long accountId,
                      List<AniServiceEntrance> entranceList) {
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

    public void addServiceEntrance(AniServiceEntrance entrance) {
        if (entranceList == null) {
            entranceList = new ArrayList<>();
        }
        entranceList.add(entrance);
    }

    public void addServiceEntrance(List<AniServiceEntrance> entranceList) {
        if (entranceList == null) {
            entranceList = new ArrayList<>();
        }
        entranceList.addAll(entranceList);
    }

    public static void update(AniServicePersistService aniServicePersistService, AniService aniService) throws IOException {
        AniServiceDao aniServiceDao = toDao(aniService);
        aniServicePersistService.update(aniServiceDao);
    }

    public static AniServiceDao toDao(AniService aniService) {
        if (aniService == null) {
            return null;
        }

        return new AniServiceDao(
                aniService.id,
                aniService.aniServiceId,
                aniService.serviceName,
                aniService.version,
                aniService.clientSecret,
                StringUtils.collectionToCommaDelimitedString(aniService.resourceIds),
                StringUtils.collectionToCommaDelimitedString(aniService.scope),
                StringUtils.collectionToCommaDelimitedString(aniService.authorizedGrantTypes),
                StringUtils.collectionToCommaDelimitedString(aniService.authorities),
                aniService.webServerRedirectUri,
                aniService.accessTokenValidity,
                aniService.refreshTokenValidity,
                aniService.autoApprove,
                aniService.registerDate,
                aniService.archived,
                aniService.trusted,
                aniService.serviceServerUrl,
                aniService.logoPath,
                StringUtils.collectionToCommaDelimitedString(aniService.languageSet),
                StringUtils.collectionToCommaDelimitedString(aniService.tagSet),
                aniService.price,
                aniService.onShelf,
                aniService.description,
                aniService.accountId,
                AniServiceEntrance.toDaoList(aniService.entranceList)
        );
    }

    public static AniService toDomain(AniServiceDao aniServiceDao) {
        if (aniServiceDao == null) {
            return null;
        }

        return new AniService(
                aniServiceDao.id,
                aniServiceDao.aniServiceId,
                aniServiceDao.serviceName,
                aniServiceDao.version,
                aniServiceDao.clientSecret,
                StringUtils.commaDelimitedListToSet(aniServiceDao.resourceIds),
                StringUtils.commaDelimitedListToSet(aniServiceDao.scope),
                StringUtils.commaDelimitedListToSet(aniServiceDao.authorizedGrantTypes),
                StringUtils.commaDelimitedListToSet(aniServiceDao.authorities),
                aniServiceDao.webServerRedirectUri,
                aniServiceDao.accessTokenValidity,
                aniServiceDao.refreshTokenValidity,
                aniServiceDao.autoApprove,
                aniServiceDao.registerDate,
                aniServiceDao.archived,
                aniServiceDao.trusted,
                aniServiceDao.serviceServerUrl,
                aniServiceDao.logoPath,
                StringUtils.commaDelimitedListToSet(aniServiceDao.languageSet),
                StringUtils.commaDelimitedListToSet(aniServiceDao.tagSet),
                aniServiceDao.price,
                aniServiceDao.onShelf,
                aniServiceDao.description,
                aniServiceDao.accountId,
                AniServiceEntrance.toDomainList(aniServiceDao.entranceList)
        );
    }

    @Override
    public String toString() {
        return "AniService{" +
                ", id=" + id +
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
