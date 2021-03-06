package com.wildfly.jms.message.handlers;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueueMessageHandler  {

    private static Logger logger = Logger.getLogger(QueueMessageHandler.class.getCanonicalName());

    @Inject
    @JMSConnectionFactory("java:/jmsExample/MyInVMConnectionFactory")
    private JMSContext jmsContext;

    @Resource(lookup = "java:/jmsExample/MyWildflyQueue")
    private Queue messageDestination;

    public void postMessage(String message) throws Exception {

        try {
            jmsContext.createProducer().send(messageDestination, message);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "I'm no expert, but I'm pretty sure your message didn't make it...");
            throw e;
        }
    }
}
