package com.myretail.retaildataaggregator.controller

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.annotation.PostConstruct

@RestController
@RequestMapping("/health")
@Slf4j
class HealthController {

    @Value('${spring.application.name}')
    String applicationName
    String serverName

    @PostConstruct
    void init() {
        serverName = determineServerName()
    }

    @GetMapping
    Map healthCheck() {
        log.debug("Responding to health Check")
        //Todo check heartbeat on external dependencies.
        ["applicationName": applicationName,
         "serverName": serverName]
    }

    @GetMapping("/ping")
    Map healthCheckPing() {
        log.debug("Responding to health Check Ping")
        ["applicationName": applicationName,
        "serverName": serverName]
    }

    private static String determineServerName() {
        def localHost = InetAddress.localHost
        def serverName = localHost.canonicalHostName
        def serverIpAddress = localHost.hostAddress
        serverName ?: serverIpAddress
    }
}
