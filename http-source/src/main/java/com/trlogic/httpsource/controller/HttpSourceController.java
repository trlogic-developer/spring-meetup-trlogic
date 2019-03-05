package com.trlogic.httpsource.controller;

import com.trlogic.httpsource.domain.Product;
import com.trlogic.httpsource.gateway.HttpListenerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.messaging.MessageHeaders.CONTENT_TYPE;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping(value = "/http-source")
public class HttpSourceController {

    @Autowired
    private HttpListenerGateway httpListenerGateway;

    @PostMapping(value = "/send-synch-event")
    public String sendSyncEvent(@RequestBody Product product) {
        byte[] responseByte = httpListenerGateway.sendSyncRequest(MessageBuilder.withPayload(product)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader("event", "trigger")
                .build());

        String response = responseByte == null ? "null" : new String(responseByte);
        return response;
    }

    @PostMapping(value = "/send-asynch-event")
    public void sendAsyncEvent(Product product) {
        httpListenerGateway.sendAsyncRequest(MessageBuilder.withPayload(product).build());
    }
}
