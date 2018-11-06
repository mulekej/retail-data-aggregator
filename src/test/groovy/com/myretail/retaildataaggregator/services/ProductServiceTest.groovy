package com.myretail.retaildataaggregator.services

import com.myretail.retaildataaggregator.domain.api.Price
import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.exception.BadRequestException
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

    def "updateProductPrice NonExistentProduct"() {
        setup:
        1 * productRepository.existsById(productId) >> false

        when:
        productService.updateProductPrice(product)

        then:
        def ex = thrown(ProductNotFoundException)
        ex.message == "Product Not Found, Unable to update product for id=12345"
        0 * productRepository.save(product)
    }

    def "updateProductPrice Success"() {
        setup:
        1 * productRepository.existsById(productId) >> true

        when:
        productService.updateProductPrice(product)

        then:
        1 * productRepository.save(product)
    }

    def "addProductPrice Success"() {
        setup:
        1 * productRepository.existsById(product.id) >> false

        when:
        productService.addProductPrice(product)

        then:
        notThrown(BadRequestException)
        1 * productRepository.save(product)
    }

    def "addProductPrice PreExistingProduct"() {
        setup:
        1 * productRepository.existsById(product.id) >> true

        when:
        productService.addProductPrice(product)

        then:
        def ex = thrown(BadRequestException)
        ex.message == "Cannot add price for product, price already exists. Change request type to PUT to update price."
        0 * productRepository.save(product)
    }

    def "deleteProductPrice Success"() {
        setup:
        1 * productRepository.existsById(productId) >> true

        when:
        productService.deleteProductById(productId)

        then:
        1 * productRepository.deleteById(productId)
    }

    def "deleteProductPrice ProductNotFound"() {
        setup:
        1 * productRepository.existsById(productId) >> false

        when:
        productService.deleteProductById(productId)

        then:
        def ex = thrown(ProductNotFoundException)
        ex.message == "Product Not Found, Unable to delete product for id=$productId"
        0 * productRepository.deleteById(productId)
    }

}
