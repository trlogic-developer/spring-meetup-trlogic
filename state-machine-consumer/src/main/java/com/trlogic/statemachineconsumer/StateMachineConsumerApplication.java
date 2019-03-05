package com.trlogic.statemachineconsumer;

import com.trlogic.statemachineconsumer.channel.StateMachineChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value = StateMachineChannel.class)
@SpringBootApplication
public class StateMachineConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StateMachineConsumerApplication.class, args);
    }

}
