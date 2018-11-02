package com.myretail.retaildataaggregator.exception

import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll


class RedSkyResponseErrorHandlerTest extends Specification {

    RedSkyResponseErrorHandler responseErrorHandler

    def setup(){
        responseErrorHandler = new RedSkyResponseErrorHandler()
    }

    @Unroll
    def "hasError #statusCode"(){
        when:
        def result = responseErrorHandler.hasError(statusCode)

        then:
        result == expectedValue

        where:
        statusCode                          | expectedValue
        HttpStatus.OK                       | false
        HttpStatus.INTERNAL_SERVER_ERROR    | true
        HttpStatus.NOT_FOUND                | false
    }

    def "handleError Success"() {
        when:
        responseErrorHandler.handleError(null, HttpStatus.I_AM_A_TEAPOT)

        then:
        def ex = thrown(RedSkyTransactionException)
        ex.message == "RedSKy Service responded with non-success status=418"
    }
}
