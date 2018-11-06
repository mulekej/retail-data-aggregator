package com.myretail.retaildataaggregator

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.myretail.retaildataaggregator.definition.TestDefinition
import com.myretail.retaildataaggregator.domain.api.Product
import groovy.util.logging.Slf4j
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.HttpMethod.POST
import static org.springframework.http.HttpMethod.PUT

@Slf4j
@SpringBootTest(webEnvironment = RANDOM_PORT)
@RunWith(Parameterized)
class RegressionTest {


    @LocalServerPort
    protected int port
    protected String host = 'http://localhost'
    private String baseUrl

    private String testName
    private TestDefinition testDefinition

    private RestTemplate restTemplate

    RegressionTest(String testName, TestDefinition testDefinition) {
        this.testName = testName
        this.testDefinition = testDefinition
    }

    @Parameterized.Parameters(name = "{0}")
    static Iterable<Object[]> data() {
        // cannot be injected because this method must be static
        def objectMapper = new ObjectMapper()
        objectMapper.registerModule(new JavaTimeModule())

        def dir = new ClassPathResource("tests")
        dir.file.listFiles().collect { testFile ->
            def testDefinition = objectMapper.readValue(testFile, TestDefinition) as TestDefinition

            [
                    testDefinition.testName,
                    testDefinition
            ] as Object[]
        }
    }

    @Before
    void before() {
        baseUrl = "$host:$port/api/v1/products"
        restTemplate = new RestTemplate()
    }

    @Test
    void runTests() {
        def method = HttpMethod.resolve(testDefinition.requestMethod)
        def messageBody = buildMessageBody(method)

        def response = restTemplate.exchange(baseUrl, method, messageBody, Product)
        assert response
    }

    def buildUrlPath(String requestMethod) {
        def method = HttpMethod.resolve(requestMethod)

    }

    HttpEntity<Product> buildMessageBody(HttpMethod method) {
        def requestEntity
        if ([POST, PUT].contains(method)) {
            requestEntity = new HttpEntity<Product>(testDefinition.messageBody)
        } else {
            requestEntity = null
        }
        requestEntity
    }
}
