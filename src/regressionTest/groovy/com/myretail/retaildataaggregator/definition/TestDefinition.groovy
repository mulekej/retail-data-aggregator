package com.myretail.retaildataaggregator.definition

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.myretail.retaildataaggregator.domain.api.Product

@JsonIgnoreProperties(ignoreUnknown = true)
class TestDefinition {

    String testName
    String requestMethod
    String productId
    Product messageBody
    ExpectedResponse expectedResponse
}
