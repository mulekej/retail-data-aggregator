package com.myretail.retaildataaggregator.exception


class ProductNotFoundException extends RuntimeException {

    ProductNotFoundException(String message) {
        super(message)
    }
}
