package com.myretail.retaildataaggregator.exception


class ProductAlreadyExistsException extends RuntimeException {

    ProductAlreadyExistsException(String message) {
        super(message)
    }
}
