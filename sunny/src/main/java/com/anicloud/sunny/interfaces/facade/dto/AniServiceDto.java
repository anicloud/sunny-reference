package com.anicloud.sunny.interfaces.facade.dto;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * @autor zhaoyu
 * @date 16-4-6
 * @since JDK 1.7
 */
public class AniServiceDto {
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
    public boolean trusted;
    public Long accountId;

    // TODO .......
}
