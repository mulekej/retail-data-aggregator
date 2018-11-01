package com.myretail.retaildataaggregator.controller

import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.services.AggregatorService
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource


@RequestMapping(value = ["/api/v1/products"])
@RestController
@Slf4j
class ProductsController {

    @Resource
    AggregatorService aggregatorService

    @GetMapping(value = "/{productId}")
    Product getProductInfoById(@PathVariable("productId") String productId) {
        aggregatorService.getProductInfoById(productId)
    }
}
