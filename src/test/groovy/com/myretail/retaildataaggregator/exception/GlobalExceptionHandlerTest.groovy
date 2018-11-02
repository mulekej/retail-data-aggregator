package com.myretail.retaildataaggregator.exception

import groovy.util.logging.Slf4j
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.http.HttpStatus

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Slf4j
@RunWith(MockitoJUnitRunner)
class GlobalExceptionHandlerTest {

    private def dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    private def currentTimeStamp = LocalDateTime.now()

    GlobalExceptionHandler exceptionHandler

    @Before
    void before() {
        exceptionHandler = new GlobalExceptionHandler()
    }

    @Test
    void catchProductNotFoundException() {
        def pnfEx = new ProductNotFoundException("Warning, Warning")
        def response = exceptionHandler.handleException(pnfEx)

        response.with {
            assert it.statusCode == HttpStatus.NOT_FOUND
            assert it.hasBody()
            def errorBody = it.body
            assert errorBody.statusCode == HttpStatus.NOT_FOUND.value()
            assert errorBody.message == "Warning, Warning"
            assert dateTimeFormatter.format(errorBody.timeStamp) == dateTimeFormatter.format(currentTimeStamp)
        }
    }

    @Test
    void catchGeneralException() {
        def rtEx = new RuntimeException("Warning, Warning")
        def response = exceptionHandler.handleException(rtEx)

        response.with {
            assert it.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
            assert it.hasBody()
            def errorBody = it.body
            assert errorBody.statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()
            assert errorBody.message == "Warning, Warning"
            assert dateTimeFormatter.format(errorBody.timeStamp) == dateTimeFormatter.format(currentTimeStamp)
        }
    }
}
