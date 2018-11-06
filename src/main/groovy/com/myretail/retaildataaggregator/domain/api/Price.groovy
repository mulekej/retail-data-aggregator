package com.myretail.retaildataaggregator.domain.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includePackage = false)
@JsonIgnoreProperties(ignoreUnknown = true, value = ["MetaClass"])
class Price {

    double value

    @JsonProperty("currency_code")
    String currencyCode
}
