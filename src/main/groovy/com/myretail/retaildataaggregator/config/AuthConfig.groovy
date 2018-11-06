package com.myretail.retaildataaggregator.config

import com.myretail.retaildataaggregator.auth.WriteTokenFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthConfig {

    @Bean
    FilterRegistrationBean authFilterRegistrationBean(WriteTokenFilter writeTokenFilter) {
        def registrationBean = new FilterRegistrationBean(filter: writeTokenFilter)
        registrationBean.addUrlPatterns("/api/v1/products/*")
        registrationBean
    }
}
