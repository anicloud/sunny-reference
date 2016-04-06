package com.anicloud.sunny.interfaces.facade.dto;

import java.util.Set;

/**
 * @autor zhaoyu
 * @date 16-4-6
 * @since JDK 1.7
 */
public class AniServiceEntranceDto {
    public Long entranceId;
    public String entranceName;
    public String entranceUrl;
    public String logoPath;
    public Set<String> tagSet;
    public String description;

    public AniServiceEntranceDto() {
    }

    public AniServiceEntranceDto(String entranceName, String entranceUrl,
                                 String logoPath, Set<String> tagSet,
                                 String description) {
        this.entranceName = entranceName;
        this.entranceUrl = entranceUrl;
        this.logoPath = logoPath;
        this.tagSet = tagSet;
        this.description = description;
    }

    public AniServiceEntranceDto(Long entranceId, String entranceName,
                                 String entranceUrl, String logoPath,
                                 Set<String> tagSet, String description) {
        this.entranceId = entranceId;
        this.entranceName = entranceName;
        this.entranceUrl = entranceUrl;
        this.logoPath = logoPath;
        this.tagSet = tagSet;
        this.description = description;
    }

    @Override
    public String toString() {
        return "AniServiceEntranceDto{" +
                "entranceId=" + entranceId +
                ", entranceName='" + entranceName + '\'' +
                ", entranceUrl='" + entranceUrl + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", tagSet=" + tagSet +
                ", description='" + description + '\'' +
                '}';
    }
}
