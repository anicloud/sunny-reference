package com.anicloud.sunny.infrastructure.persistence.domain.app;

import java.io.Serializable;
import java.util.Objects;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
public class AniServiceEntranceDao implements Serializable {
    private static final long serialVersionUID = -9051496626833084249L;

    public Long id;
    public String entranceName;
    public String entranceUrl;
    public String logoPath;
    public String tagSet;
    public String description;

    public AniServiceEntranceDao() {
    }

    public AniServiceEntranceDao(Long id, String entranceName, String entranceUrl,
                                 String logoPath, String tagSet, String description) {
        this.id = id;
        this.entranceName = entranceName;
        this.entranceUrl = entranceUrl;
        this.logoPath = logoPath;
        this.tagSet = tagSet;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AniServiceEntranceDao that = (AniServiceEntranceDao) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(entranceName, that.entranceName) &&
                Objects.equals(entranceUrl, that.entranceUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entranceName, entranceUrl);
    }

    @Override
    public String toString() {
        return "AniServiceEntranceDao{" +
                "id=" + id +
                ", entranceName='" + entranceName + '\'' +
                ", entranceUrl='" + entranceUrl + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", tagSet='" + tagSet + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
