package com.aicode.ai_code_reviewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}

//🧠 What’s happening?
//Code	Meaning
//@Configuration	This is config class
//@Bean	Spring will manage this object
//WebClient.builder()	Creates HTTP client