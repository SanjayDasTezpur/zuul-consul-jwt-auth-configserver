package com.microservice.platform.ApiGateway.jwt.common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Getter
@ToString
public class JwtAuthenticationConfig {

    @Value("${micro.security.jwt.url:/login}")
    private String url;

    @Value("${micro.security.jwt.header:Authorization}")
    private String header;

    @Value("${micro.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${micro.security.jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${micro.security.jwt.secret}")
    private String secret;
}