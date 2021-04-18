package com.microservice.platform.ApiGateway.signup.service;

import com.microservice.platform.ApiGateway.exception.MissingRequiredItemExceptions;
import com.microservice.platform.ApiGateway.signup.SignUp;
import com.microservice.platform.ApiGateway.signup.enums.ERegiterAttributes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService implements SignUp {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Object doSignUp(Map<String, Object> reqBody) {
        verifyMandatoryFields(reqBody);

        if (ifUserPresentWithSameAttributeValue(reqBody, ERegiterAttributes.email)) {
            return Map.of("data", "your email is already registered", "action", "FORGOT_PASSWORD");
        }

        if (ifUserPresentWithSameAttributeValue(reqBody, ERegiterAttributes.phone)) {
            return Map.of("data", "your phone is already registered", "action", "FORGOT_PASSWORD");
        }
        String phone = reqBody.get(ERegiterAttributes.phone.name()).toString();
        if (phone.length() != 10 || !NumberUtils.isDigits(phone)) {
            return Map.of("data", "Invalid phone number", "action", "ENTER_MOBILE_NUMBER");
        }
        String mail = reqBody.get(ERegiterAttributes.email.name()).toString();
        if (!mail.contains("@") || !mail.contains(".")) {
            return Map.of("data", "Invalid email id", "action", "ENTER_EMAIL_ID");
        }

        doRegister(reqBody);
        return Map.of("data", "Register Successfully");
    }

    private void doRegister(Map<String, Object> reqBody) {
        String username = reqBody.get(ERegiterAttributes.username.name()).toString();
        String password = reqBody.get(ERegiterAttributes.password.name()).toString();
        password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
        String phone = reqBody.get(ERegiterAttributes.phone.name()).toString();
        String email = reqBody.get(ERegiterAttributes.email.name()).toString();
        jdbcTemplate.execute("INSERT INTO users(username,password,enabled,phone,email) VALUES ('" + username + "','" + password + "', true," + phone + ",'" + email + "')");
    }

    private boolean ifUserPresentWithSameAttributeValue(Map<String, Object> reqBody, ERegiterAttributes attr) {
        val allValues = jdbcTemplate.queryForList("select * from users where " + attr.name() + "='" + reqBody.get(attr.name()).toString() + "';");
        if (Objects.isNull(allValues) || allValues.isEmpty()) {
            return false;
        }
        return true;
    }

    private void verifyMandatoryFields(Map<String, Object> reqBody) {
        Set<String> keys = reqBody.keySet();
        Arrays.stream(ERegiterAttributes.values()).forEach(eRegiterAttributes -> {
            if (!keys.contains(eRegiterAttributes.name())) {
                log.warn("in doSignUp() - Missing name value in request body {}", reqBody);
                throw new MissingRequiredItemExceptions(" must specify " + eRegiterAttributes.name());
            }
        });
    }
}
