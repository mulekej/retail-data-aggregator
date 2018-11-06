package com.myretail.retaildataaggregator.exception


class BadRequestException extends RuntimeException {

    BadRequestException(String message) {
        super(message)
    }
}
