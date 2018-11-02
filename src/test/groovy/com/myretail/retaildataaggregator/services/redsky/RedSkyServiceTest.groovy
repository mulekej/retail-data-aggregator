package com.myretail.retaildataaggregator.services.redsky

import com.myretail.retaildataaggregator.domain.redsky.Item
import com.myretail.retaildataaggregator.domain.redsky.Product
import com.myretail.retaildataaggregator.domain.redsky.ProductDescription
import com.myretail.retaildataaggregator.domain.redsky.ProductWrapper
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class RedSkyServiceTest extends Specification {

    def productId = "12345"
    RedSkyService redSkyService
    RestTemplate mockedTemplate

    def setup() {
        mockedTemplate = Mock(RestTemplate)
        redSkyService =new RedSkyService(restTemplate: mockedTemplate)
    }

    def "getProductInfoByTcin Success"() {
        setup:
        def productWrapper = new ProductWrapper(product: new Product(item: new Item(tcin: productId, productDescription: new ProductDescription(title: "TestTitle"))))
        def responseEntity = new ResponseEntity<ProductWrapper>(productWrapper, HttpStatus.OK)

        1 * mockedTemplate.exchange(_, HttpMethod.GET, null, ProductWrapper) >> { responseEntity }
        when:
        def result = redSkyService.getProductInfoByTcin(productId)

        then:
        result.tcin == "12345"
        result.productDescription.title == "TestTitle"
    }

    def "getProductInfoByTcin NotFound"() {
        setup:
        def productWrapper = new ProductWrapper(product: new Product(item: new Item()))
        def responseEntity = new ResponseEntity<ProductWrapper>(productWrapper, HttpStatus.NOT_FOUND)

        1 * mockedTemplate.exchange(_, HttpMethod.GET, null, ProductWrapper) >> { responseEntity }
        when:
        def result = redSkyService.getProductInfoByTcin(productId)

        then:
        !result.tcin
    }
}
