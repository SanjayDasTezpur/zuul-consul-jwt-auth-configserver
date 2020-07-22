package com.microservice.platform.ApiGateway.controller;

import com.microservice.platform.ApiGateway.fiegnclients.NoficationServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FieignTestController {
    private final NoficationServiceClient noficationServiceClient;

    @GetMapping("/api/notify")
    public String test1() {
        log.info("/api/notify called");
        return "<h1> From Notification Service: </h1> <h3>" + noficationServiceClient.forUser() +"</h3>";
    }

    @GetMapping("/admin/notify")
    public String test2() {
        log.info("/admin/notify called");
        return "<h1> From Notification Service: </h1> <h3>" + noficationServiceClient.forAdmin() +"</h3>";
    }
}
