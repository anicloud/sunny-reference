package com.anicloud.sunny.interfaces.facade.dto;

import java.io.Serializable;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
public class AniEntranceDto implements Serializable {
    private static final long serialVersionUID = 7942521011856789100L;
    public Long id;
    public String entranceName;
    public String entranceUrl;
    public String logoPath;
    public String tagSet;
    public String description;

    public AniEntranceDto(Long id, String entranceName, String entranceUrl,
                          String logoPath, String tagSet, String description) {
        this.id = id;
        this.entranceName = entranceName;
        this.entranceUrl = entranceUrl;
        this.logoPath = logoPath;
        this.tagSet = tagSet;
        this.description = description;
    }

}
