package com.patyapp.party.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PartyConfiguration {

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }
}
