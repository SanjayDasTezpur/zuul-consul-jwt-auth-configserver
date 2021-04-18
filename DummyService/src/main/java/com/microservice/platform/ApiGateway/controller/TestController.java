package com.microservice.platform.ApiGateway.controller;

import com.microservice.platform.ApiGateway.services.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/")
    public String test() {
        return "<h1> Dummy Service </h1>";
    }

    @GetMapping("/api/hello")
    public String test1() {

        log.info("/api/hello called ");
        return "<h1> Hello world! God Is Great, I am a valid user and resided behind APIGateway </h1> ";
    }

    @GetMapping("/api/bijoy")
    public String bijoyExample() {
        log.info("bijoy");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(testService::hugeTask);
        executorService.shutdown();
        return "Request Submitted";
    }

    @GetMapping("/api/exception")
    public String test1Rxception() {
        try {
            throw new RuntimeException("You create this exception");
        } catch (Exception e) {
            log.error("/api/exception called {}", e);
        }
        return "<h1> Hello world! God Is Great, I am a valid user and resided behind APIGateway </h1> ";
    }

    @GetMapping("/admin/hello")
    public String test2() {
        return "<h1> Hello world! God Is Great, I am a valid admin and resided behind APIGateway </h1>";
    }

    @GetMapping("/extra/admin")
    public String test2_1() {
        return "<h1> Hello world! God Is Great, I am a valid admin and resided behind APIGateway </h1>";
    }

    @GetMapping("/open/hello")
    public String test3() {
        log.info("logging...");
        return "<h1> Hello world! God Is Great, I am a random guy and not resided behind APIGateway </h1>";
    }

    @GetMapping("/api/hcb")
    public String getValue(@RequestParam(name = "t", defaultValue = "1") int t) {
        return testService.getValue(t);
    }
}
