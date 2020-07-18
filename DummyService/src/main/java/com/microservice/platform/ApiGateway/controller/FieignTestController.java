package com.microservice.platform.ApiGateway.controller;

import com.microservice.platform.ApiGateway.fiegnclients.NoficationServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FieignTestController {
    private final NoficationServiceClient noficationServiceClient;

    @GetMapping("/api/notify")
    public String test1() {
        return "<h1> From Notification Service: </h1> <h3>" + noficationServiceClient.forUser() +"</h3>";
    }

    @GetMapping("/admin/notify")
    public String test2() {
        return "<h1> From Notification Service: </h1> <h3>" + noficationServiceClient.forAdmin() +"</h3>";
    }
}
