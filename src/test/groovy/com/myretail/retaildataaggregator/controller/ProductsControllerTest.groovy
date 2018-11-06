package com.myretail.retaildataaggregator.controller


import com.myretail.retaildataaggregator.domain.api.Price
import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.exception.BadRequestException
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
        0 * productService.updateProductPrice(_)
    }

    def "updateProductPriceById Success"() {

        when:
        productController.updateProductPriceById(productId, product)

        then:
        1 * productService.updateProductPrice(product)
        0 * _
        notThrown(ProductNotFoundException)
    }

    def "updateProductPriceById IdMisMatch"() {
        setup:
        productId = "23456"

        when:
        productController.updateProductPriceById(productId, product)

        then:
        def ex = thrown(BadRequestException)
        ex.message == "ProductId in path ($productId) does not match id in body (${product.id})"
        0 * _
    }

    def "addProductPriceById Success"() {

        when:
        productController.addProductPriceById(productId, product)

        then:
        1 * productService.addProductPrice(product)
        0 * _
        notThrown(BadRequestException)
    }

    def "addProductPriceById IdMisMatch"() {
        setup:
        productId = "23456"

        when:
        productController.addProductPriceById(productId, product)

        then:
        def ex = thrown(BadRequestException)
        ex.message == "ProductId in path ($productId) does not match id in body (${product.id})"
        0 * _
    }

    def "deleteProductPriceById Success"() {

        when:
        productController.deleteProductPriceById(productId)

        then:
        1 * productService.deleteProductById(productId)
        0 * _
        notThrown(BadRequestException)
    }
}
