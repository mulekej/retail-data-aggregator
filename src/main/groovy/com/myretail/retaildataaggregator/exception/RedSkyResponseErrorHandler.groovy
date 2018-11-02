package com.myretail.retaildataaggregator.exception

import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.DefaultResponseErrorHandler

@Component
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

        if (!statusCode.is2xxSuccessful()) {
            throw new RedSkyTransactionException("RedSKy Service responded with non-success status=${statusCode.value()}")
        }
    }
}
