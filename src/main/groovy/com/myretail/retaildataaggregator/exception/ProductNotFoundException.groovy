package com.myretail.retaildataaggregator.exception


class ProductNotFoundException extends RuntimeException {

    ProductNotFoundException() {
    }

    ProductNotFoundException(String message) {
        super(message)
    }
}
