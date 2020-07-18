package com.microservice.platform.ApiGateway.services;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    public static final String TEST_SERVICE = "Test Service";

    @HystrixCommand(fallbackMethod = "executeIfFail", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String getValue(int val) {
        __sleep(val);
        return TEST_SERVICE;
    }

    private String executeIfFail(int val) {
        return TEST_SERVICE + " !!! Sorry our Systems are busy! try again later.";
    }

    private void __sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            log.error("Interuppted");
            Thread.currentThread().interrupt();
        }
    }
}
