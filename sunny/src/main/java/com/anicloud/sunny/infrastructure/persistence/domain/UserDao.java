package com.anicloud.sunny.infrastructure.persistence.domain;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zhaoyu on 15-6-8.
 */
@Entity
@Table(name = "t_user")
public class UserDao extends AbstractEntity {
    private static final long serialVersionUID = -1856228190263844653L;

    @Column(name = "hash_user_id", unique = true)
    public String hashUserId;
    public String email;
    public String screenName;

    public String accessToken;
    public String tokenType;
    public String refreshToken;
    public String expiresIn;
    public String scope;

    public UserDao() {
    }

    public UserDao(String accessToken, String email, String expiresIn, String hashUserId,
                   String refreshToken, String scope, String screenName, String tokenType) {
        this.accessToken = accessToken;
        this.email = email;
        this.expiresIn = expiresIn;
        this.hashUserId = hashUserId;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.screenName = screenName;
        this.tokenType = tokenType;
    }

    @Override
    public String toString() {
       return ToStringBuilder.reflectionToString(this);
    }
}
