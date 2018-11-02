package com.myretail.retaildataaggregator.domain.api

import com.fasterxml.jackson.annotation.JsonProperty

class Price {

    double value

    @JsonProperty("currency_code")
    String currencyCode
}
