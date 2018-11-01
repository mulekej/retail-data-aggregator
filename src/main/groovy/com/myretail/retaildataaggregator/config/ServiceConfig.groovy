package com.myretail.retaildataaggregator.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ServiceConfig {

    @Bean
    RestTemplate restTemplate(){
        new RestTemplate()
    }
}
