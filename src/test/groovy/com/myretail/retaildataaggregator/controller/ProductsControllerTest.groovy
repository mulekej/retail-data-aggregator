package com.myretail.retaildataaggregator.controller


import com.myretail.retaildataaggregator.domain.api.Price
import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.services.AggregatorService
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(webEnvironment = NONE)
class ProductsControllerTest extends Specification {

    def aggregatorService = Mock(AggregatorService)
    def productId = "12345"

    def productController

    def setup() {
        productController = new ProductsController(aggregatorService: aggregatorService)
    }

    def "getProductInfoById Success"() {
        setup:
        def productResult = new Product(id: productId, name: "test product 1", price: new Price(value: 13.99, currencyCode: "CAD"))
        1 * aggregatorService.getProductInfoById(productId) >> {productResult}

        when:
        def response = productController.getProductInfoById(productId) as Product

        then:
        response.id == "12345"
        response.name == "test product 1"
        response.price == new Price(value: 13.99, currencyCode: "CAD")
    }

    def "updateProductInfoById"() {

        //updateProductInfoById is currently a no-op
        when:
        productController.updateProductInfoById(productId)

        then:
        0 * aggregatorService.getProductInfoById(_)
    }
}
