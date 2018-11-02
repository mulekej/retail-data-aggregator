package com.myretail.retaildataaggregator.domain.api

import java.time.LocalDateTime

class Error {

    int statusCode
    String message
    LocalDateTime timeStamp = LocalDateTime.now()
}
