package com.wildfly.jms.service.application;

import com.wildfly.jms.service.resources.JMSMessageService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/service")
public class JMSMessageApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(JMSMessageService.class);
        return classes;
    }
}
