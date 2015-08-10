package com.anicloud.sunny.interfaces.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by sirhuoshan on 2015/8/10.
 */
public class SessionListener implements HttpSessionListener {
    private static final Logger LOG = LoggerFactory.getLogger(SessionListener.class);


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LOG.info(httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
