package com.myretail.retaildataaggregator

import com.myretail.retaildataaggregator.domain.api.Price
import com.myretail.retaildataaggregator.domain.api.Product
import groovy.util.logging.Slf4j
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.web.client.RestTemplate

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@Slf4j
@SpringBootTest(webEnvironment = RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner)
class RegressionTest {

    @LocalServerPort
    protected int port
    protected String host = 'http://localhost'
    private String baseUrl

    private RestTemplate restTemplate
    private String productId
    private HttpHeaders headers

    @Before
    void before() {
        baseUrl = "$host:$port/api/v1/products"
        restTemplate = new RestTemplate()
        headers = new HttpHeaders()
        headers.add('Authorization', "GenericToken")
        productId = ""
    }

    @Test
    void getExistingProduct() {
        productId = "52272903" //Lego Batman BluRay
        def response = restTemplate.exchange("$baseUrl/$productId", HttpMethod.GET, null, Product)
        assert response
    }

    @Test
    void addAndRemoveNewPriceForProduct() {

        productId = "13016243"  //Gold Medal All Purpose Flour - 2lb
        def newPrice = new Product(id: productId, price: new Price(value: 1.89, currencyCode: "USD"))
        def requestEntity = new HttpEntity<Product>(newPrice, headers)
        def addResponse = restTemplate.exchange("$baseUrl/$productId", HttpMethod.POST, requestEntity, Product)
        assert addResponse
        assert addResponse.statusCode == HttpStatus.OK
        assert !addResponse.body

        def deleteResponse = restTemplate.exchange("$baseUrl/$productId", HttpMethod.DELETE, new HttpEntity(headers), Product)
        assert deleteResponse.statusCode == HttpStatus.OK
    }

    @Test
    void updateExistingProduct() {
        productId = "13397813" //Folgers
        def newPrice = new Product(id: productId, price: new Price(value: 3.99, currencyCode: "USD"))
        def requestEntity = new HttpEntity<Product>(newPrice, headers)
        def updateResponse = restTemplate.exchange("$baseUrl/$productId", HttpMethod.PUT, requestEntity, Product)
        assert updateResponse.statusCode == HttpStatus.OK

        //check value was update
        def getResponse = restTemplate.exchange("$baseUrl/$productId", HttpMethod.GET, null, Product)
        assert getResponse.statusCode == HttpStatus.OK
        def body = getResponse.body as Product
        body.with {
            assert id == productId
            price.with {
                assert value == 3.99
                assert currencyCode == "USD"
            }
        }

    }
}
