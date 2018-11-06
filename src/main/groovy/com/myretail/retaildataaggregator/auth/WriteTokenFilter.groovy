package com.myretail.retaildataaggregator.auth

import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean

import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import static org.springframework.http.HttpMethod.DELETE
import static org.springframework.http.HttpMethod.PATCH
import static org.springframework.http.HttpMethod.POST
import static org.springframework.http.HttpMethod.PUT

@Component
class WriteTokenFilter extends GenericFilterBean {

    @Override
    void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        def request = (HttpServletRequest) req
        def response = (HttpServletResponse) res

        def authHeader = request.getHeader('Authorization')
        def httpMethod = HttpMethod.resolve(request.method)

        if ([POST, PUT, PATCH, DELETE].contains(httpMethod)) {
            //if attempting to do write functionality a token must be present
            if (!authHeader) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Missing Auth Header")
            } else {
                /*
                Validate token here and return error if invalid
                Leaving un-impled for POC
                 */

                chain.doFilter(req, res)
            }

        } else {
            chain.doFilter(req, res)
        }

    }
}
