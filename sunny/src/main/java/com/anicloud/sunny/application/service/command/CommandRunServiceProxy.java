package com.anicloud.sunny.application.service.command;

import com.ani.cel.service.manager.agent.app.builder.AniCommandDtoBuilder;
import com.ani.cel.service.manager.agent.app.model.AniCommandCallResultDto;
import com.ani.cel.service.manager.agent.app.model.AniCommandDto;

/**
 * Created by zhaoyu on 15-8-1.
 */
public interface CommandRunServiceProxy {
    public AniCommandCallResultDto runCommand(AniCommandDtoBuilder commandDtoBuilder, String hashUserId);
    public AniCommandCallResultDto runCommand(AniCommandDto aniCommandDto, String hashUserId);
}
