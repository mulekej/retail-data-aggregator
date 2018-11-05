package com.myretail.retaildataaggregator.services

import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.exception.ProductNotFoundException
import com.myretail.retaildataaggregator.repository.ProductRepository
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

import javax.annotation.Resource

@Service
@Slf4j
class AggregatorService {

    @Resource
    RedSkyService redSkyService
    @Resource
    ProductRepository productRepository

    Product getProductInfoById(String productId) {
        def product
        def redSkyResult = redSkyService.getProductInfoByTcin(productId)
        product = productRepository.getProductById(productId)

        if (redSkyResult?.tcin && product) {
            product.name = redSkyResult.productDescription.title
            product
        } else {
            log.info("ProductNotFound for productId=$productId redSkyResultFound=${redSkyResult?.tcin as boolean} databaseResultFound=${product as boolean}")
            throw new ProductNotFoundException("Product Not Found for ID ${productId}")
        }
    }
}
