package com.myretail.retaildataaggregator.domain.api

import com.fasterxml.jackson.annotation.JsonProperty


class Product {

    String id
    String name

    @JsonProperty("current_price")
    Price price
}
