package com.myretail.retaildataaggregator.config

import com.google.common.base.Predicates
import com.myretail.retaildataaggregator.exception.RedSkyResponseErrorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class ServiceConfig {

    @Bean
    Docket swaggerApi() {
        ApiInfo info = new ApiInfo("Retail Data Aggregator", //title
                "Retrieves Product Info and Price", //description
                "Release 1.0", //version
                "", //tos url
                "ejmulek@gmail.com", //coontact
                "", //license
                "") //license url
        new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(info)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex('/error.*')))
                .build()
    }

    @Bean
    RestTemplate restTemplate(RedSkyResponseErrorHandler errorHandler){
        def restTemplate = new RestTemplate()
        restTemplate.errorHandler = errorHandler
        restTemplate
    }
}
