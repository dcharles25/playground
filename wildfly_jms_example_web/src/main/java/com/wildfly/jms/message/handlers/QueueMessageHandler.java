package com.wildfly.jms.message.handlers;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

public class QueueMessageHandler  {

    @Inject
    @JMSConnectionFactory("java:/jmsExample/MyInVMConnectionFactory")
    private JMSContext jmsContext;

    @Resource(lookup = "java:/jmsExample/MyWildflyQueue")
    private Queue messageDestination;

    public void postMessage(String message) throws Exception {

        try {
            jmsContext.createProducer().send(messageDestination, message);
        } catch (Exception e) {
            System.out.println("Something went awry in " + QueueMessageHandler.class.getSimpleName());
            throw e;
        }
    }
}
