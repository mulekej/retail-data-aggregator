package com.myretail.retaildataaggregator.services

import com.myretail.retaildataaggregator.domain.redsky.Item
import com.myretail.retaildataaggregator.domain.redsky.ProductWrapper
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

import javax.annotation.Resource

@Service
@Slf4j
class RedSkyService {

    private static final String BASE_URL = "/v2/pdp"
    private static final String SEARCH_EXCLUSIONS = "excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics"

    @Resource
    RestTemplate restTemplate

    @Value('${myretail.redsky.host}')
    String host

    @HystrixCommand(fallbackMethod = "searchFallback")
    Item getProductInfoByTcin(String tcin) {

        def response = restTemplate.exchange(
                "$host$BASE_URL/tcin/$tcin?$SEARCH_EXCLUSIONS",
                HttpMethod.GET,
                null,
                ProductWrapper)

        def item = response.body.product.item as Item
        log.debug("Result found from RedSky=${item.tcin as boolean} tcin=$tcin")
        item
    }

    Item searchFallback(String tcin) {
        null
    }
}
