package com.myretail.retaildataaggregator.auth

import org.springframework.mock.web.MockFilterChain
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import spock.lang.Specification
import spock.lang.Unroll

class WriteTokenFilterTest extends Specification {

    MockHttpServletRequest request
    MockHttpServletResponse response
    WriteTokenFilter filter = new WriteTokenFilter()

    def setup() {
        request = Spy(new MockHttpServletRequest())
        response = Spy(new MockHttpServletResponse())
    }

    @Unroll
    def "doFilter #method #authToken"() {
        setup:
        request.method = method
        if (authToken) {
            request.addHeader('Authorization', authToken)
        }

        when:
        filter.doFilter(request, response, new MockFilterChain())

        then:
        1 * request.getHeader('Authorization')
        1 * request.method
        if (result) {
            1 * response.sendError(result, 'Missing Auth Header')
        }

        where:
        method  | authToken         | result
        "GET"   | "An Auth Token"   | null
        "GET"   | null              | null
        "POST"  | "An Auth Token"   | null
        "POST"  | null              | 401
        "PUT"   | "An Auth Token"   | null
        "PUT"   | null              | 401
        "PATCH" | "An Auth Token"   | null
        "PATCH" | null              | 401
        "DELETE" | "An Auth Token"  | null
        "DELETE" | null             | 401
    }
}
