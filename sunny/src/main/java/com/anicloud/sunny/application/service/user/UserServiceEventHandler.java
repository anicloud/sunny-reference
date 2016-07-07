package com.anicloud.sunny.application.service.user;


import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.agent.service.commons.oauth.dto.AuthorizationCodeParameter;
import com.anicloud.sunny.application.assemble.UserDtoAssembler;
import com.anicloud.sunny.application.builder.OAuth2ParameterBuilder;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.agent.AgentTemplate;
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
    public UserDto saveUser(UserDto userDao) {
        User user = UserDtoAssembler.toUser(userDao);
        user = User.save(userPersistenceService, user);
        return UserDtoAssembler.fromUser(user);
    }

    @Override
    public UserDto modifyUser(UserDto userDao) {
        User user = UserDtoAssembler.toUser(userDao);
        user = User.modify(userPersistenceService, user);
        return UserDtoAssembler.fromUser(user);
    }

    @Override
    public void removeUser(UserDto userDao) {
        User user = UserDtoAssembler.toUser(userDao);
        User.remove(userPersistenceService, user);
    }

    @Override
    public UserDto getUserByHashUserId(Long hashUserId) {
        User user = User.getUserByHashUserId(userPersistenceService, hashUserId);
        return UserDtoAssembler.fromUser(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = User.getUserByEmail(userPersistenceService, email);
        return UserDtoAssembler.fromUser(user);
    }

    @Override
    public UserDto refreshUserToken(Long hashUserId) {
        UserDto userDto = getUserByHashUserId(hashUserId);

        Long currentTimeStamp = System.currentTimeMillis();
        if (userDto.expiresIn - (currentTimeStamp - userDto.createTime) / 1000 < Constants.TOKEN_REFRESH_TIME_INTERVAL_IN_SECONDS) {
            LOGGER.info("refresh user token.");
            AuthorizationCodeParameter authorizationCodeParameter = OAuth2ParameterBuilder.buildForRefreshToken(Constants.aniServiceDto);
            AniOAuthAccessToken accessToken = agentTemplate.getAniOAuthService()
                    .refreshAccessToken(userDto.refreshToken, authorizationCodeParameter);
            LOGGER.info("refresh token {}.", accessToken);

            userDto.accessToken = accessToken.getAccessToken();
            userDto.tokenType = accessToken.getTokenType();
            userDto.refreshToken = accessToken.getRefreshToken();
            userDto.expiresIn = accessToken.getExpiresIn();
            userDto.scope = accessToken.getScope();

            userDto = modifyUser(userDto);
        }
        return userDto;
    }
}
