package com.myretail.retaildataaggregator.repository


import com.myretail.retaildataaggregator.domain.api.Product
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository extends MongoRepository<Product, String> {

    Product getProductById(String productId)
}
