package com.myretail.retaildataaggregator

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard

@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
class RetailDataAggregatorApplication {

    static void main(String[] args) {
        SpringApplication.run RetailDataAggregatorApplication, args
    }
}
