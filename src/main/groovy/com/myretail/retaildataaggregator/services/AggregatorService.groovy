package com.myretail.retaildataaggregator.services

import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.services.redsky.RedSkyService
import org.springframework.stereotype.Service

import javax.annotation.Resource

@Service
class AggregatorService {

    @Resource
    RedSkyService redSkyService

    Product getProductInfoById(String productId) {
        def redSkyResult = redSkyService.getProductInfoByTcin(productId)

        def name = redSkyResult.item.productDescription.title
        new Product(id: productId, name: name)
    }


}
