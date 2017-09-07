package com.wildfly.jms.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(name = "WildFlyMDBExample", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jmsExample/MyWildflyQueue"),
        @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:/jmsExample/MyInVMConnectionFactory"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class WildFlyMessageBean implements MessageListener {

    private final static Logger logger = Logger.getLogger(WildFlyMessageBean.class.getCanonicalName());

    @Override
    public void onMessage(Message message) {

        try {
            String datMessageDoe = message.getBody(String.class);
            logger.log(Level.INFO, "Successfully received message: " + datMessageDoe);
        } catch(JMSException e) {
            logger.log(Level.SEVERE, "What the heck did you send me? I wasn't able to read that.", e);
        }
    }
}
