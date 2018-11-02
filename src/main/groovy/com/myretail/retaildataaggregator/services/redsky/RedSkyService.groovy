package com.myretail.retaildataaggregator.services.redsky

import com.myretail.retaildataaggregator.domain.redsky.Item
import com.myretail.retaildataaggregator.domain.redsky.ProductWrapper
import com.myretail.retaildataaggregator.repository.ProductRepository
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

import javax.annotation.Resource

@Service
class RedSkyService {

    private static final String BASE_URL = "https://redsky.target.com/v2/pdp"
    private static final String SEARCH_EXCLUSIONS = "excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics"

    @Resource
    RestTemplate restTemplate
    @Resource
    ProductRepository priceRepository

    @HystrixCommand(fallbackMethod = "searchFallback")
    Item getProductInfoByTcin(String tcin) {

        def response = restTemplate.exchange(
                "$BASE_URL/tcin/$tcin?$SEARCH_EXCLUSIONS",
                HttpMethod.GET,
                null,
                ProductWrapper)

        response.body.product.item as Item
    }

    Item searchFallback(String tcin) {
        null
    }
}
