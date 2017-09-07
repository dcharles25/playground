package com.wildfly.jms.service.resources;

import com.wildfly.jms.message.handlers.QueueMessageHandler;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/jmsMessage")
public class JMSMessageService {

    private static Logger logger = Logger.getLogger(JMSMessageService.class.getCanonicalName());

    @Inject
    private QueueMessageHandler messageHandler;

    @GET
    public Response getJMSMessage() {
        return Response.accepted("JMS Example ready to rock! Trying posting a message.").build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postJMSMessage(String message) {

        try {
            messageHandler.postMessage(message);
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Something for sure blew up...", e);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("We were unable to process your message").build();
        }

        return Response.ok("You message: " + message + ", was processed.").build();
    }
}
