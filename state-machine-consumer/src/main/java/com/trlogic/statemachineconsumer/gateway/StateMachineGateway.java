package com.trlogic.statemachineconsumer.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface StateMachineGateway {

    @Gateway(requestChannel = "reply-channel")
    void sendReply(Message<String> message);
}
