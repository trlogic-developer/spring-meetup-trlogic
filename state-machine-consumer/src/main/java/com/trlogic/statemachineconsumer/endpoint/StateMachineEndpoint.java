package com.trlogic.statemachineconsumer.endpoint;

import com.trlogic.statemachineconsumer.domain.Product;
import com.trlogic.statemachineconsumer.gateway.StateMachineGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;

@MessageEndpoint
public class StateMachineEndpoint {

    public static int bonus = 0;

    @Autowired
    private StateMachineGateway stateMachineGateway;

    @Autowired
    private StateMachine<String, String> stateMachine;

    @StreamListener(value = "request-channel")
    public void execute(Message<Product> message) {
        String event = (String) message.getHeaders().get("event");

        System.out.println(message.getPayload());
        System.out.println(event);

        stateMachine.getExtendedState().getVariables().put("product", message.getPayload());
        stateMachine.getExtendedState().getVariables().put("name", message.getPayload().getName());

        stateMachine.start();
        stateMachine.sendEvent(event);
        stateMachine.stop();

        stateMachineGateway.sendReply(MessageBuilder.withPayload("Tebrikler. Bonusunuz:" + bonus)
                .copyHeaders(message.getHeaders())
                .build());

        bonus = 0;
    }
}
