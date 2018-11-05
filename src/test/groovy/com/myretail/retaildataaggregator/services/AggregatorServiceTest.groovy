package com.myretail.retaildataaggregator.services

import com.myretail.retaildataaggregator.domain.api.Price
import com.myretail.retaildataaggregator.domain.api.Product
import com.myretail.retaildataaggregator.domain.redsky.Item
import com.myretail.retaildataaggregator.domain.redsky.ProductDescription
import com.myretail.retaildataaggregator.exception.ProductNotFoundException
import com.myretail.retaildataaggregator.repository.ProductRepository
import groovy.util.logging.Slf4j
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.when

@Slf4j
@RunWith(MockitoJUnitRunner)
class AggregatorServiceTest {

    @Mock
    RedSkyService redSkyService
    @Mock
    ProductRepository repository
    @InjectMocks
    AggregatorService service

    String productId = "12345"
    Item expectedItem
    Product repositoryProduct

    @Before
    void before() {
        expectedItem = new Item(
                tcin: productId,
                productDescription: new ProductDescription(title: "Test Product Name")
        )

        repositoryProduct = new Product(
                id: productId,
                price: new Price(
                        value: 13.99,
                        currencyCode: "NZD"
                )
        )

        when(redSkyService.getProductInfoByTcin(productId)).thenReturn(expectedItem)
        when(repository.getProductById(productId)).thenReturn(repositoryProduct)
    }

    @Test
    void getProductInfoByIdSuccess() {

        def result = service.getProductInfoById("12345")
        assert result
        result.with {
            assert it.id == "12345"
            assert it.name == "Test Product Name"
            assert it.price == new Price(value: 13.99, currencyCode: "NZD")
        }
    }

    @Test
    void getProductInfoByIdDbResultNotFound() {

        when(repository.getProductById(productId)).thenReturn(null)

        verifyExceptionCaught()
    }

    @Test
    void getProductInfoByIdRedSkyFallbackOccurred() {

        when(redSkyService.getProductInfoByTcin(productId)).thenReturn(null)

        verifyExceptionCaught()
    }

    @Test
    void getProductInfoByIdRedSkyProductNotFound() {

        when(redSkyService.getProductInfoByTcin(productId)).thenReturn(new Item())

        verifyExceptionCaught()
    }

    private verifyExceptionCaught() {
        try {
            service.getProductInfoById("12345")
            Assert.fail("ProductNotFoundException expected")
        } catch (ProductNotFoundException ex) {
            assert ex.message == "Product Not Found for ID 12345"
        } catch (Exception ex) {
            Assert.fail("Unexpected exception caught of type ${ex.class}")
        }
    }
}
