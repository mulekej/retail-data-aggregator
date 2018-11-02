package com.myretail.retaildataaggregator.exception

import com.myretail.retaildataaggregator.domain.api.Error
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler
    final ResponseEntity<Error> handleException(Exception ex) {
        if (ex instanceof ProductNotFoundException) {
            handleProductNotFoundException(ex)
        } else {
            buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    ResponseEntity<Error> handleProductNotFoundException(ProductNotFoundException pnfex) {
        buildResponse(pnfex, HttpStatus.NOT_FOUND)
    }

    ResponseEntity<Error> buildResponse(Exception ex, HttpStatus status) {
        def error = new Error(statusCode: status.value, message: ex.message)
        new ResponseEntity<Error>(error, status)
    }

}
