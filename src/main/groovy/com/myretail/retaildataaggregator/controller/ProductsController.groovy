package com.myretail.retaildataaggregator.controller

import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.services.AggregatorService
import com.myretail.retaildataaggregator.services.ProductService
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource


@RequestMapping(value = ["/api/v1/products"])
@RestController
@Slf4j
class ProductsController {

    @Resource
    AggregatorService aggregatorService
    @Resource
    ProductService productService

    @GetMapping("/{productId}")
    Product getProductInfoById(@PathVariable("productId") String productId) {
        log.debug("requestType=GET productId=$productId")
        aggregatorService.getProductInfoById(productId)
    }

    @PostMapping("")
    void addProductPriceById(@RequestBody Product product) {
        log.debug("requestType=POST productId=${product.id}")
        productService.addProductPrice(product)
    }

    @PutMapping("/{productId}")
    void updateProductPriceById(@PathVariable("productId") String productId, @RequestBody Product product) {
        log.debug("requestType=PUT productId=$productId")
        productService.updateProductPrice(productId, product)
    }

    @DeleteMapping("/{productId}")
    void deleteProductPriceById(@PathVariable("productId") String productId) {
        log.debug("requestType=DELETE productId=$productId")
        productService.deleteProductById(productId)
    }
}
