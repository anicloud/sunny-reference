package com.anicloud.sunny.domain.model.app;

import com.ani.bus.service.commons.dto.aniservice.AniServiceEntranceDto;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceEntranceDao;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public class AniServiceEntrance implements Serializable {
    private static final long serialVersionUID = 2594706093541394665L;

    public Long entranceId;
    public String entranceName;
    public String entranceUrl;
    public String logoPath;
    public Set<String> tagSet;
    public String description;

    public AniServiceEntrance() {}

    public AniServiceEntrance(String entranceName, String entranceUrl,
                              String logoPath, Set<String> tagSet, String description) {
        this.entranceName = entranceName;
        this.entranceUrl = entranceUrl;
        this.logoPath = logoPath;
        this.tagSet = tagSet;
        this.description = description;
    }

    public AniServiceEntrance(Long entranceId, String entranceName,
                              String entranceUrl, String logoPath,
                              Set<String> tagSet, String description) {
        this.entranceId = entranceId;
        this.entranceName = entranceName;
        this.entranceUrl = entranceUrl;
        this.logoPath = logoPath;
        this.tagSet = tagSet;
        this.description = description;
    }

    public void setTagSet(Set<String> tagSet) {
        if (this.tagSet == null) {
            this.tagSet = tagSet;
        }
        this.tagSet.addAll(tagSet);
    }

    public void addTag(String tag) {
        if (this.tagSet == null) {
            this.tagSet = new HashSet<>();
        }
        this.tagSet.add(tag);
    }

    public static AniServiceEntranceDao toDao(AniServiceEntrance serviceEntrance) {
        if (serviceEntrance == null) {
            return null;
        }
        return new AniServiceEntranceDao(
                serviceEntrance.entranceId,
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
    public static List<AniServiceEntranceDto> fromCommonsToLocal(List<com.anicloud.sunny.application.dto.app.AniServiceEntranceDto> entranceDtoList)
    {
        if (entranceDtoList == null){
            return null;
        }
        List<AniServiceEntranceDto> commonsEntranceList = new ArrayList<>();
        for (com.anicloud.sunny.application.dto.app.AniServiceEntranceDto entranceDto: entranceDtoList) {
            commonsEntranceList.add(new AniServiceEntranceDto(
                    entranceDto.entranceId,
                    entranceDto.entranceName,
                    entranceDto.entranceUrl,
                    entranceDto.logoPath,
                    entranceDto.tagSet,
                    entranceDto.description)
            );
        }
        return commonsEntranceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AniServiceEntrance that = (AniServiceEntrance) o;
        return Objects.equals(entranceId, that.entranceId) &&
                Objects.equals(entranceName, that.entranceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entranceId, entranceName);
    }

    @Override
    public String toString() {
        return "AniServiceEntrance{" +
                "entranceId=" + entranceId +
                ", entranceName='" + entranceName + '\'' +
                ", entranceUrl='" + entranceUrl + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", tagSet=" + tagSet +
                ", description='" + description + '\'' +
                '}';
    }
}
