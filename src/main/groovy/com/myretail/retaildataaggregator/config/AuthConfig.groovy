package com.myretail.retaildataaggregator.config

import com.myretail.retaildataaggregator.auth.WriteTokenFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean

//@Configuration
class AuthConfig {

//    @Bean
    FilterRegistrationBean authFilterRegistrationBean(WriteTokenFilter writeTokenFilter) {
        def registrationBean = new FilterRegistrationBean(filter: writeTokenFilter)
        registrationBean.addUrlPatterns("/api/*/products/*")
        registrationBean
    }
}
