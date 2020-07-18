package com.microservice.platform.ApiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String test() {
        return "<h1> Notification Service </h1>";
    }
    @GetMapping("/api/hello")
    public String test1() {
        return "<h1> Hello world! God Is Great, I am a valid user and resided behind APIGateway </h1>";
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
        return "<h1> Hello world! God Is Great, I am a random guy and not resided behind APIGateway </h1>";
    }
}
