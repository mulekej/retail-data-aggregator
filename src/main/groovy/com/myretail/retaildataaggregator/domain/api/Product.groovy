package com.myretail.retaildataaggregator.domain.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includePackage = false)
@JsonIgnoreProperties(ignoreUnknown = true)
class Product {

    String id
    String name
    @JsonProperty("current_price")
    Price price
}
