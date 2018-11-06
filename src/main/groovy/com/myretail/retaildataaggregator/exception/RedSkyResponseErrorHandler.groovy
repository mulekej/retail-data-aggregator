package com.myretail.retaildataaggregator.exception

import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.DefaultResponseErrorHandler

class RedSkyResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    protected boolean hasError(HttpStatus statusCode) {
        //404 is a valid status from RedSky indicating the productId is not found
        if (statusCode == HttpStatus.NOT_FOUND) {
            false
        } else {
            super.hasError(statusCode)
        }
    }

    @Override
    protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {

        throw new RedSkyTransactionException("RedSKy Service responded with non-success status=${statusCode.value()}")
    }
}
