package com.myretail.retaildataaggregator.domain.api

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Price {

    double value

    @JsonProperty("currency_code")
    String currencyCode
}
