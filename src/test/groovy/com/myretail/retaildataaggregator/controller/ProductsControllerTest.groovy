package com.myretail.retaildataaggregator.controller


import com.myretail.retaildataaggregator.domain.api.Price
import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.exception.ProductNotFoundException
import com.myretail.retaildataaggregator.services.AggregatorService
import com.myretail.retaildataaggregator.services.ProductService
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(webEnvironment = NONE)
class ProductsControllerTest extends Specification {

    def aggregatorService = Mock(AggregatorService)
    def productService = Mock(ProductService)
    def productId = "12345"
    def product = new Product(id: productId, price: new Price(value: 14.99, currencyCode: "UZD"))

    ProductsController productController

    def setup() {
        productController = new ProductsController(aggregatorService: aggregatorService, productService: productService)
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
        0 * productService.updateProductPrice(_, _)
    }

    def "updateProductInfoById Success"() {

        when:
        productController.updateProductInfoById(productId, product)

        then:
        0 * aggregatorService.getProductInfoById(_)
        1 * productService.updateProductPrice(productId, product)
        notThrown(ProductNotFoundException)
    }
}
