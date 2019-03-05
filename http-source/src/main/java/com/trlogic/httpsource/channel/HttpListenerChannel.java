package com.trlogic.httpsource.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface HttpListenerChannel {

    @Output(value = "request-channel")
    MessageChannel outboundRequestChannel();

    @Input(value = "reply-channel")
    SubscribableChannel inboundReplyChannel();
}
