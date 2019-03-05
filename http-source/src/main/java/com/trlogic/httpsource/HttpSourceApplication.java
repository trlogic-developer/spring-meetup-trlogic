package com.trlogic.httpsource;

import com.trlogic.httpsource.channel.HttpListenerChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value = HttpListenerChannel.class)
@SpringBootApplication
public class HttpSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpSourceApplication.class, args);
    }

}
