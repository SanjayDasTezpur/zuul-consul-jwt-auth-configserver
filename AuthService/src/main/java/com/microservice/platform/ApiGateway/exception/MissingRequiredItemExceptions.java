package com.microservice.platform.ApiGateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingRequiredItemExceptions extends RuntimeException {

    public MissingRequiredItemExceptions(String message) {

        super("[Missing Item] " + message);
    }
}
