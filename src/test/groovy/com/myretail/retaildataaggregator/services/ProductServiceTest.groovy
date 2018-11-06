package com.myretail.retaildataaggregator.services

import com.myretail.retaildataaggregator.domain.api.Price
import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.exception.ProductAlreadyExistsException
import com.myretail.retaildataaggregator.exception.ProductNotFoundException
import com.myretail.retaildataaggregator.repository.ProductRepository
import spock.lang.Specification


class ProductServiceTest extends Specification {

    def productRepository = Mock(ProductRepository)
    ProductService productService

    String productId
    Product product

    def setup() {
        productId = "12345"
        product = new Product(id: productId, price: new Price(value: 12.39, currencyCode: "CAD"))
        productService = new ProductService(productRepository: productRepository)
    }

    def "updateProductPrice IdMismatch"() {
        setup:
        product.id = "23456"

        when:
        productService.updateProductPrice(productId, product)

        then:
        def ex = thrown(ProductNotFoundException)
        ex.message == "ProductId in path (12345) does not match id in body (23456)"
        0 * _
    }

    def "updateProductPrice NonExistentProduct"() {
        setup:
        1 * productRepository.existsById(productId) >> false

        when:
        productService.updateProductPrice(productId, product)

        then:
        def ex = thrown(ProductNotFoundException)
        ex.message == "Product Not Found, Unable to update product for id=12345"
        0 * productRepository.save(product)
    }

    def "updateProductPrice Success"() {
        setup:
        1 * productRepository.existsById(productId) >> true

        when:
        productService.updateProductPrice(productId, product)

        then:
        1 * productRepository.save(product)
    }

    def "addProductPrice Success"() {
        setup:
        1 * productRepository.existsById(product.id) >> false

        when:
        productService.addProductPrice(product)

        then:
        notThrown(ProductAlreadyExistsException)
        1 * productRepository.save(product)
    }

    def "addProductPrice PreExistingProduct"() {
        setup:
        1 * productRepository.existsById(product.id) >> true

        when:
        productService.addProductPrice(product)

        then:
        def ex = thrown(ProductAlreadyExistsException)
        ex.message == "Cannot add price for product, price already exists. Change request type to PUT to update price."
        0 * productRepository.save(product)
    }

}
