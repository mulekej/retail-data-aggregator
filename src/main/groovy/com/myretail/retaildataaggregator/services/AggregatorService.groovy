package com.myretail.retaildataaggregator.services

import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.services.redsky.RedskyService
import org.springframework.stereotype.Service

import javax.annotation.Resource

@Service
class AggregatorService {

    @Resource
    RedskyService redskyService

    Product getProductInfoById(String productId) {

        new Product(id: productId)
    }


}
