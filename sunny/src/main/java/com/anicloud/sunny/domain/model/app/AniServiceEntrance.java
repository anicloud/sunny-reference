package com.anicloud.sunny.domain.model.app;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
