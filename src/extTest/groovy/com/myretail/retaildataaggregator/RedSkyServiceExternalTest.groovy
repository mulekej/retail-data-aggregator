package com.myretail.retaildataaggregator

import com.myretail.retaildataaggregator.config.ServiceConfig
import com.myretail.retaildataaggregator.services.RedSkyService
import groovy.util.logging.Slf4j
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.web.client.RestTemplate

import javax.annotation.Resource

@Slf4j
@ContextConfiguration(classes = [ServiceConfig])
@TestPropertySource("classpath:exttest.properties")
@RunWith(SpringJUnit4ClassRunner)
class RedSkyServiceExternalTest {

    @Resource
    RestTemplate restTemplate
    @Value('${myretail.redsky.host}')
    String host

    RedSkyService redSkyService

    @Before
    void before() {
        redSkyService = new RedSkyService(
                restTemplate: restTemplate,
                host: host
        )
    }

    @Test
    void requestVariousTcins() {
        def products = [
                "13860428":"The Big Lebowski (Blu-ray)",
                "52602890":"10ft Pre-Lit Artificial Christmas Tree Full Salem Spruce - Clear Lights",
                "13397813":"Folgers Classic Medium Roast Ground Coffee - 30.5oz",
                "53474916":"Harry Potter and the Chamber of Secrets -  (Harry Potter) by J. K. Rowling (Paperback)",
                "52272903":"The LEGO Batman Movie (Blu-ray)"]

        products.each { product ->
            def item = redSkyService.getProductInfoByTcin(product.key)
            assert item?.tcin == product.key
            assert item.productDescription.title == product.value
        }
    }
}
