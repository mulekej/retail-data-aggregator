package com.myretail.retaildataaggregator.exception

import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.DefaultResponseErrorHandler

@Component
class RedSkyResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
        if (!statusCode.is2xxSuccessful()) {
            throw new RedSkyTransactionException(response.statusText)
        }
    }
}
