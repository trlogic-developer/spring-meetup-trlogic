package com.trlogic.httpsource.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.HeaderEnricherSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
public class BeanConfig {

    @Bean
    public IntegrationFlow enrichHeader() {
        return IntegrationFlows.from("enrich")
                .enrichHeaders(HeaderEnricherSpec::headerChannelsToString)
                .channel("request-channel")
                .get();
    }
}
