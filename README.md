# retail-data-aggregator
myRetail app for combining product information

## Technology Stack
* Spring Boot 2.1.0.RELEASE
* SpringFox (Swagger)
* Hystrix (Circuit Breaker)
* MongoDb
* groovy
* Mockito and Spock for testing

## Running the application


## Testing the Endpoints
### Using the swagger page
http://localhost:9022/swagger-ui.html

### Using Curl
#### Get Product By Id
`curl -X GET "http://localhost:9022/api/v1/products/52272903"`

#### Add Product
`curl -X GET "http://localhost:9022/api/v1/products/52272903"`

#### update Product Price
`curl -X PUT "http://localhost:9022/api/v1/products/52272903" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"current_price\": { \"currency_code\": \"UZD\", \"value\": 99.33 }, \"id\": \"52272903\"}"`

#### Delete Product By Id
`curl -X GET "http://localhost:9022/api/v1/products/52272903"`
