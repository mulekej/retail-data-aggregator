package com.myretail.retaildataaggregator.config

import com.myretail.retaildataaggregator.exception.RedSkyResponseErrorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ServiceConfig {

    @Bean
    RestTemplate restTemplate(RedSkyResponseErrorHandler errorHandler){
        def restTemplate = new RestTemplate()
        restTemplate.errorHandler = errorHandler
        restTemplate
    }
}
