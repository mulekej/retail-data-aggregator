package com.myretail.retaildataaggregator.domain.api

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includePackage = false)
class Price {

    double value

    @JsonProperty("currency_code")
    String currencyCode
}
