package com.myretail.retaildataaggregator.services

import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.exception.ProductAlreadyExistsException
import com.myretail.retaildataaggregator.exception.ProductNotFoundException
import com.myretail.retaildataaggregator.repository.ProductRepository
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

import javax.annotation.Resource

@Slf4j
@Service
class ProductService {

    @Resource
    ProductRepository productRepository

    void addProductPrice(Product product) {
        if (!productRepository.existsById(product.id)) {
            productRepository.save(product)
        } else {
            throw new ProductAlreadyExistsException("Cannot add price for product, price already exists. Change request type to PUT to update price.")
        }
    }

    void updateProductPrice(String productId, Product product) throws ProductNotFoundException {

        if (productId != product.id) {
            throw new ProductNotFoundException("ProductId in path ($productId) does not match id in body (${product.id})")
        }

        if (productRepository.existsById(product.id)) {
            productRepository.save(product)
        } else {
            throw new ProductNotFoundException("Product Not Found, Unable to update product for id=${product.id}")
        }
    }
}
