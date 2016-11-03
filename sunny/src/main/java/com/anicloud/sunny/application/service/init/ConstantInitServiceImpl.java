package com.anicloud.sunny.application.service.init;

import com.ani.agent.service.core.config.AnicelMeta;
import com.ani.agent.service.core.websocket.WebSocketClient;
import com.ani.agent.service.core.websocket.WebSocketSessionFactory;
import com.ani.agent.service.service.websocket.ClientInvokable;
import com.ani.agent.service.service.websocket.ObjectNotify;
import com.ani.agent.service.service.websocket.observer.AniObjectCallMessageObserver;
import com.ani.bus.service.commons.observer.MessageObserver;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.service.agent.ClientInvokerImpl;
import com.anicloud.sunny.application.service.agent.ObjectNotifyImpl;
import com.anicloud.sunny.infrastructure.persistence.service.app.SunnyStubPersistService;
import com.anicloud.sunny.interfaces.facade.AppServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Vector;

/**
 * @autor zhaoyu
 * @date 16-4-12
 * @since JDK 1.7
 */
@Service
public class ConstantInitServiceImpl implements ConstantInitService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConstantInitServiceImpl.class);

    @Resource
    private AnicelMeta anicelMeta;
    @Resource
    private AppServiceFacade appServiceFacade;
    @Resource(name = "objectNotify")
    private ObjectNotify objectNotify;
    @Resource
    private SunnyStubPersistService sunnyStubPersistService;

    @Override
    @PostConstruct
    public void constantInitService() {

        try {
            Constants.aniServiceDto = appServiceFacade.getAniServiceInfo();
            Constants.SUNNY_STUB_MAPPINGS = sunnyStubPersistService.fetchSunnyStubMappings();
            LOGGER.debug("init AniService information.");
        } catch (IOException e) {
            LOGGER.error("read sunny basic info error. msg {}.", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
