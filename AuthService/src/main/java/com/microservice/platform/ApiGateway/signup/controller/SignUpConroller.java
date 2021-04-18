package com.microservice.platform.ApiGateway.signup.controller;

import com.microservice.platform.ApiGateway.signup.SignUp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SignUpConroller {

    private final SignUp signUp;

    @PostMapping("/register")
    public Object signUp(@RequestBody @NotNull Map<String, Object> reqBody) {
        return signUp.doSignUp(reqBody);
    }

}
