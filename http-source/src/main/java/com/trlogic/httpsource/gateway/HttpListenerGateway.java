package com.trlogic.httpsource.gateway;

import com.trlogic.httpsource.domain.Product;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;

@MessagingGateway
public interface HttpListenerGateway {

    @Gateway(requestChannel = "enrich", replyChannel = "reply-channel", requestTimeout = 5000, replyTimeout = 15000)
    byte[] sendSyncRequest(Message<Product> message);

    @Async
    @Gateway(requestChannel = "request-channel")
    void sendAsyncRequest(Message<Product> message);
}
