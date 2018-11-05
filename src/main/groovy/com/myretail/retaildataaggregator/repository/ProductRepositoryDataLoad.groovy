package com.myretail.retaildataaggregator.repository

import com.myretail.retaildataaggregator.domain.api.Price
import com.myretail.retaildataaggregator.domain.api.Product
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct
import javax.annotation.Resource

/*
    Used for POC purposes only, remove in actual deployment.
 */
@Service
@Slf4j
class ProductRepositoryDataLoad {

    @Resource
    ProductRepository productRepository

    @PostConstruct
    private void init (){
        def products = [
                new Product(id: "13860428", price: new Price(value: 13.99, currencyCode: "USD"))
        ]

        def saveResult = productRepository.saveAll(products)

        log.debug("Products PreLoaded into MongoDb:")
        saveResult.each {
            log.debug("${it}")
        }
    }
}
