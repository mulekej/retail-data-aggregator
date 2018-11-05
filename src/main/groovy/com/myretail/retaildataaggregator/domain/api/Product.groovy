package com.myretail.retaildataaggregator.domain.api

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.ToString

@ToString(includePackage = false)
class Product {

    String id
    String name
    @JsonProperty("current_price")
    Price price
}
