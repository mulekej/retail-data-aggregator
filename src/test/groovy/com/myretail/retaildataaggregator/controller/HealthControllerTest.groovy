package com.myretail.retaildataaggregator.controller

import spock.lang.Specification

class HealthControllerTest extends Specification {

    def healthController = new HealthController(applicationName: "testApp1", serverName: "testHost")

    def "healthEndpointSuccess"() {
        when:
        def result = healthController.healthCheck()

        then:
        result.applicationName == "testApp1"
        result.serverName == "testHost"
    }

    def "healthPingEndpointSuccess"() {
        when:
        def result = healthController.healthCheckPing()

        then:
        result.applicationName == "testApp1"
        result.serverName == "testHost"
    }

}
