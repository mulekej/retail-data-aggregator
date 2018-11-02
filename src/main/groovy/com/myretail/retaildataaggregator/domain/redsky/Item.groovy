package com.myretail.retaildataaggregator.domain.redsky

import com.fasterxml.jackson.annotation.JsonProperty


class Item {

    String tcin
    def bundleComponents
    String dpci
    String upc
    @JsonProperty("product_description")
    ProductDescription productDescription
    String parentItems
    String buyUrl
    def variation
    def enrichment
    String returnMethod
}
