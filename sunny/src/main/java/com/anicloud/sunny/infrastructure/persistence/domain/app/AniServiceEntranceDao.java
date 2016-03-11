package com.anicloud.sunny.infrastructure.persistence.domain.app;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Entity
@Table(name="t_aniService_entrance")
public class AniServiceEntranceDao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "entranceName", nullable = false, length = 150)
    public String entranceName;
    @Column(name = "entranceUrl", nullable = false, length = 200)
    public String entranceUrl;
    @Column(name = "logoPath", length = 200)
    public String logoPath;
    @Column(name = "tagSet", length = 200)
    public String tagSet;
    @Column(name = "description", length = 255)
    public String description;

    public AniServiceEntranceDao() {
    }

    public AniServiceEntranceDao(String entranceName, String entranceUrl,
                                 String logoPath, String tagSet,
                                 String description) {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return entranceUrl != null ? entranceUrl.equals(that.entranceUrl) : that.entranceUrl == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (entranceUrl != null ? entranceUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AniServiceEntranceDao{" +
                "entranceName='" + entranceName + '\'' +
                ", id=" + id +
                '}';
    }
}
