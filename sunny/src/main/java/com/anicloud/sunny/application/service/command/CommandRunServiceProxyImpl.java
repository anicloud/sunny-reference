package com.anicloud.sunny.application.service.command;

import com.ani.cel.service.manager.agent.app.builder.AniCommandDtoBuilder;
import com.ani.cel.service.manager.agent.app.model.AniCommandCallResultDto;
import com.ani.cel.service.manager.agent.app.model.AniCommandDto;
import com.ani.cel.service.manager.agent.app.service.AppCommandService;
import com.ani.cel.service.manager.agent.app.service.AppCommandServiceImpl;
import com.ani.cel.service.manager.agent.core.AnicelServiceConfig;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.domain.model.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 15-8-1.
 */
@Service
@Transactional
public class CommandRunServiceProxyImpl implements CommandRunServiceProxy {
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
        // refresh token
        UserDto userDto = userService.refreshUserToken(hashUserId);
        return appCommandService.runCommand(aniCommandDto, userDto.accessToken);
    }
}
