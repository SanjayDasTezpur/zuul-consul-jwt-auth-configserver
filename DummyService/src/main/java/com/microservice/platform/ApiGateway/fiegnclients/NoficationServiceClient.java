package com.microservice.platform.ApiGateway.fiegnclients;


import com.microservice.platform.ApiGateway.fiegnclients.fallbacks.NoficationServiceClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="NotificationService" , fallback = NoficationServiceClientFallback.class)
public interface NoficationServiceClient {
    @GetMapping("/admin/hello")
    String forAdmin();

    @GetMapping("/api/hello")
    String forUser();
}