package com.trlogic.statemachineconsumer.channel;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StateMachineChannel {

    @Input(value = "request-channel")
    SubscribableChannel inboundRequestChannel();

    @Output(value = "reply-channel")
    MessageChannel outboundReplyChannel();

}
