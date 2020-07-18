package com.microservice.platform.ApiGateway.fiegnclients.fallbacks;

import com.microservice.platform.ApiGateway.fiegnclients.NoficationServiceClient;
import org.springframework.stereotype.Component;

@Component
public class NoficationServiceClientFallback implements NoficationServiceClient {
    @Override
    public String forAdmin() {
        return "forAdmin() - Notification Service failed , return from fallback";
    }

    @Override
    public String forUser() {
        return "forUser() - Notification Service failed , return from fallback";
    }
}
