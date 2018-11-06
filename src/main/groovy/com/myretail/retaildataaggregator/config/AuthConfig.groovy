package com.myretail.retaildataaggregator.config

import com.myretail.retaildataaggregator.auth.WriteTokenFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean

//@Configuration
class AuthConfig {

//    @Bean
    FilterRegistrationBean authFilterRegistrationBean() {
        def registrationBean = new FilterRegistrationBean(filter: new WriteTokenFilter())
        registrationBean.addUrlPatterns("/api/*/products/*")
        registrationBean
    }
}
