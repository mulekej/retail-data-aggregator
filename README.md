# retail-data-aggregator
myRetail app for combining product information
for the RedSky api with pricing information stored in the database.

## Technology Stack
* Spring Boot 2.1.0.RELEASE
* SpringFox (Swagger)
* Hystrix (Circuit Breaker)
* MongoDb
* groovy
* Mockito and Spock for testing
* Built using Intellij

## Running the application
### Environment Setup
MongoDb will need to be installed and running on port 27017 to run the app
without config changes. An embedded instance was skipped due to the length of boot times it incurred.
 
### Gradle
`gradlew clean run`

### Intellij
When the project is opened with IntelliJ, there a config saved
in the repo call `Run Application`. Run this to run the app.


## Testing the Endpoints
### Using the swagger page
http://localhost:9022/swagger-ui.html

### Using Curl
#### Get Product By Id
`curl -X GET "http://localhost:9022/api/v1/products/52272903"`

```
{
  "id": "52272903",
  "name": "The LEGO Batman Movie (Blu-ray)",
  "current_price": {
    "value": 15,
    "currency_code": "USD"
  }
}
```

#### Add Product
`curl -X POST "http://localhost:9022/api/v1/products/13016243" -H "accept: */*" -H "Authorization: Any Old Auth Token" -H "Content-Type: application/json" -d "{ \"current_price\": { \"currency_code\": \"USD\", \"value\": 13.99 }, \"id\": \"13016243\"}"`

Returns empty 200 response

#### update Product Price
`curl -X PUT "http://localhost:9022/api/v1/products/13016243" -H "accept: */*" -H "Authorization: Any Old Auth Token" -H "Content-Type: application/json" -d "{ \"current_price\": { \"currency_code\": \"NZD\", \"value\": 0 }, \"id\": \"13016243\"}"`

Returns empty 200 response

#### Delete Product By Id
`curl -X DELETE "http://localhost:9022/api/v1/products/13016243" -H "accept: */*" -H "Authorization: Any Old Auth Token"`

Returns empty 200 response

### Running project tests
Tests Run in gradle can be found in:
`~/build/test-results/test/` 
#### Unit Tests
`gradlew test` or the `Run All Unit Tests` run config in IntelliJ

#### External Tests
Tests that run run against external APIs
`gradlew extTest` or the `Run All External Tests` run config in IntelliJ

#### Regression Tests
End to End tests of the application
`gradlew regressionTest` or the `Run Regression Tests` run config in IntelliJ
