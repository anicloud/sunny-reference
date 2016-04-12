package com.anicloud.sunny.application.service.init;

import com.ani.bus.service.commons.observer.MessageObserver;
import com.ani.octopus.service.agent.core.config.AnicelMeta;
import com.ani.octopus.service.agent.core.websocket.WebSocketClient;
import com.ani.octopus.service.agent.core.websocket.WebSocketSessionFactory;
import com.ani.octopus.service.agent.service.websocket.ClientInvokable;
import com.ani.octopus.service.agent.service.websocket.observer.AniObjectCallMessageObserver;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.service.agent.ClientInvokerImpl;
import com.anicloud.sunny.interfaces.facade.AppServiceFacade;
import org.aspectj.apache.bcel.classfile.Constant;
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

    @Override
    @PostConstruct
    public void constantInitService() {

        try {
            Constants.aniServiceDto = appServiceFacade.getAniServiceInfo();
            LOGGER.debug("init AniService information.");
        } catch (IOException e) {
            LOGGER.error("read sunny basic info error. msg {}.", e.getMessage());
            e.printStackTrace();
        }

        // you need to implement the Invokable interface and register on
        // WebSocketClient for anicloud platform to callback
        ClientInvokable invokable = new ClientInvokerImpl();
        WebSocketClient socketClient = new WebSocketClient(invokable);

        // you need to implement your own observer and register on socketClient
        // to receive the message from anicloud platform
        Vector<MessageObserver> messageObservers = new Vector<>();
        messageObservers.add(new AniObjectCallMessageObserver());
        socketClient.setObs(messageObservers);

        // inject your WebSocketClient instance and anicloud socket destination url to factory
        // and use factory to get the session, than you can use the session to communicate
        // with anicloud platform
        WebSocketSessionFactory sessionFactory = new WebSocketSessionFactory(
                socketClient,
                anicelMeta,
                Constants.aniServiceDto.aniServiceId,
                Constants.aniServiceDto.clientSecret
        );
        Constants.aniServiceSession = sessionFactory.getAniServiceSession();
        LOGGER.info("build ani service session success.");
    }
}
