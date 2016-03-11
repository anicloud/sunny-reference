package com.anicloud.sunny.domain.adapter;

import com.anicloud.sunny.domain.model.app.AniService;
import com.anicloud.sunny.domain.model.app.AniServiceEntrance;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceEntranceDao;
import com.anicloud.sunny.interfaces.facade.dto.AniServiceDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MRK on 2016/3/8.
 */
public class DaoAdapter {
    private DaoAdapter() {}

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
                toDaoList(aniService.entranceList)
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
                toDomainList(aniServiceDao.entranceList)
        );
    }

    public static AniServiceEntranceDao toDao(AniServiceEntrance serviceEntrance) {
        if (serviceEntrance == null) {
            return null;
        }
        return new AniServiceEntranceDao(
                serviceEntrance.entranceName,
                serviceEntrance.entranceUrl,
                serviceEntrance.logoPath,
                StringUtils.collectionToCommaDelimitedString(serviceEntrance.tagSet),
                serviceEntrance.description
        );
    }

    public static AniServiceEntrance toDomain(AniServiceEntranceDao entranceDao) {
        return new AniServiceEntrance(
                entranceDao.entranceName,
                entranceDao.entranceUrl,
                entranceDao.logoPath,
                StringUtils.commaDelimitedListToSet(entranceDao.tagSet),
                entranceDao.description
        );
    }

    public static List<AniServiceEntranceDao> toDaoList(List<AniServiceEntrance> entranceList) {
        if (entranceList == null) {
            return null;
        }
        List<AniServiceEntranceDao> serviceEntranceDaoList = new ArrayList<>();
        for (AniServiceEntrance serviceEntrance : entranceList) {
            serviceEntranceDaoList.add(toDao(serviceEntrance));
        }
        return serviceEntranceDaoList;
    }

    public static List<AniServiceEntrance> toDomainList(List<AniServiceEntranceDao> daoList) {
        if (daoList == null){
            return null;
        }
        List<AniServiceEntrance> serviceEntranceList = new ArrayList<AniServiceEntrance>();
        for (AniServiceEntranceDao serviceEntrance : daoList) {
            serviceEntranceList.add(toDomain(serviceEntrance));
        }

        return serviceEntranceList;
    }
}
