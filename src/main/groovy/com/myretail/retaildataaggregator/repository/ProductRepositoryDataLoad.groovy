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
                new Product(id: "13860428", price: new Price(value: 13.99, currencyCode: "USD")),
                new Product(id: "52602890", price: new Price(value: 487.99, currencyCode: "USD")),
                new Product(id: "13397813", price: new Price(value: 7.49, currencyCode: "USD")),
                new Product(id: "53474916", price: new Price(value: 10.39, currencyCode: "USD")),
                new Product(id: "52272903", price: new Price(value: 15.00, currencyCode: "USD")),
        ]

        def saveResult = productRepository.saveAll(products)

        log.debug("Products PreLoaded into MongoDb:")
        saveResult.each {
            log.debug("Saved: ${it}")
        }
    }
}
