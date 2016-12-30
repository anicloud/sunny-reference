package com.anicloud.sunny.application.service.user;


import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.agent.service.commons.oauth.dto.AuthorizationCodeParameter;
import com.ani.agent.service.service.AgentTemplate;
import com.anicloud.sunny.application.assemble.UserDtoAssembler;
import com.anicloud.sunny.application.utils.OAuth2ParameterBuilder;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.infrastructure.persistence.service.UserPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 15-6-12.
 */
@Service
@Transactional
public class UserServiceEventHandler implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceEventHandler.class);

    @Resource
    private UserPersistenceService userPersistenceService;
    @Resource
    private AgentTemplate agentTemplate;

    @Override
    public User saveUser(User userDao) {
        User user = User.save(userPersistenceService, userDao);
        return user;
    }

    @Override
    public User modifyUser(User userDao) {
        return User.modify(userPersistenceService, userDao);
    }

    @Override
    public void removeUser(User userDao) {
        User.remove(userPersistenceService, userDao);
    }

    @Override
    public User getUserByHashUserId(Long hashUserId) {
        return User.getUserByHashUserId(userPersistenceService, hashUserId);
    }

    @Override
    public User getUserByEmail(String email) {
        return User.getUserByEmail(userPersistenceService, email);
    }

    @Override
    public User refreshUserToken(Long hashUserId) {
        User user = getUserByHashUserId(hashUserId);

        Long currentTimeStamp = System.currentTimeMillis();
        if (user.expiresIn - (currentTimeStamp - user.createTime) / 1000 < Constants.TOKEN_REFRESH_TIME_INTERVAL_IN_SECONDS) {
            LOGGER.info("refresh user token.");
            AuthorizationCodeParameter authorizationCodeParameter = OAuth2ParameterBuilder.buildForRefreshToken(Constants.aniServiceDto);
            AniOAuthAccessToken accessToken = agentTemplate.getAniOAuthService()
                    .refreshAccessToken(user.refreshToken, authorizationCodeParameter);
            LOGGER.info("refresh token {}.", accessToken);

            user.accessToken = accessToken.getAccessToken();
            user.tokenType = accessToken.getTokenType();
            user.refreshToken = accessToken.getRefreshToken();
            user.expiresIn = accessToken.getExpiresIn();
            user.scope = accessToken.getScope();
            user = modifyUser(user);
        }
        return user;
    }
}
