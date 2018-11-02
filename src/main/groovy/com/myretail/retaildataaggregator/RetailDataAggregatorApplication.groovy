package com.myretail.retaildataaggregator

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableHystrix
@EnableHystrixDashboard
@EnableSwagger2
@SpringBootApplication
class RetailDataAggregatorApplication {

    static void main(String[] args) {
        SpringApplication.run RetailDataAggregatorApplication, args
    }
}
