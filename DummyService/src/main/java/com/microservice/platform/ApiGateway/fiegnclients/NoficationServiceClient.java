package com.microservice.platform.ApiGateway.fiegnclients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="NotificationService" )
public interface NoficationServiceClient {
    @GetMapping("/admin/hello")
    String forAdmin();

    @GetMapping("/api/hello")
    String forUser();
}