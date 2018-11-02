package com.myretail.retaildataaggregator.services

import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.repository.ProductRepository
import com.myretail.retaildataaggregator.services.redsky.RedSkyService
import org.springframework.stereotype.Service

import javax.annotation.Resource

@Service
class AggregatorService {

    @Resource
    RedSkyService redSkyService
    @Resource
    ProductRepository productRepository

    Product getProductInfoById(String productId) {
        def product
        def redSkyResult = redSkyService.getProductInfoByTcin(productId)
        product = productRepository.getProductById(productId)

        if (redSkyResult && product) {
            product.name = redSkyResult.item.productDescription.title
            product
        } else {
            //throw ProductNotFound Exception
            null
        }
    }
}
