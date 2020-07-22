package com.microservice.platform.ApiGateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = "dev")
public class AppDevConf {
}
