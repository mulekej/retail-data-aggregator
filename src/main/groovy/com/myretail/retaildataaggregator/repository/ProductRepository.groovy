package com.myretail.retaildataaggregator.repository

import com.myretail.retaildataaggregator.domain.api.Price
import com.myretail.retaildataaggregator.domain.api.Product
import org.springframework.stereotype.Repository

@Repository
class ProductRepository {

    Product getProductById(String productId) {
        new Product(id: productId, price: new Price(value: 19.96, currencyCode: "USD"))
    }
}
