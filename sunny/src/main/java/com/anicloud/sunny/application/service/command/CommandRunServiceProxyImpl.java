package com.anicloud.sunny.application.service.command;

import com.ani.cel.service.manager.agent.app.builder.AniCommandDtoBuilder;
import com.ani.cel.service.manager.agent.app.model.AniCommandCallResultDto;
import com.ani.cel.service.manager.agent.app.model.AniCommandDto;
import com.ani.cel.service.manager.agent.app.service.AppCommandService;
import com.ani.cel.service.manager.agent.app.service.AppCommandServiceImpl;
import com.ani.cel.service.manager.agent.core.AnicelServiceConfig;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 15-8-1.
 */
@Service(value = "commandRunServiceProxyImpl")
@Transactional
public class CommandRunServiceProxyImpl implements CommandRunServiceProxy {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommandRunServiceProxyImpl.class);

    @Resource
    private UserService userService;
    private AppCommandService appCommandService;

    public CommandRunServiceProxyImpl() {
        this.appCommandService = new AppCommandServiceImpl(AnicelServiceConfig.getInstance());
    }

    @Override
    public AniCommandCallResultDto runCommand(AniCommandDtoBuilder commandDtoBuilder, String hashUserId) {
        return runCommand(commandDtoBuilder.builder(), hashUserId);
    }

    @Override
    public AniCommandCallResultDto runCommand(AniCommandDto aniCommandDto, String hashUserId) {
        LOGGER.info("run command, userId : {}.", hashUserId);
        // refresh token
        UserDto userDto = userService.refreshUserToken(hashUserId);
        return appCommandService.runCommand(aniCommandDto, userDto.accessToken);
    }
}
