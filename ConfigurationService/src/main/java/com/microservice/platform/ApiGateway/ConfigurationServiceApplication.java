package com.microservice.platform.ApiGateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigServer
@RequiredArgsConstructor
public class ConfigurationServiceApplication {

    private final Environment environment;

    @PostConstruct
    public void init() {
        Set<String> profiles = Arrays.stream(environment.getActiveProfiles()).collect(Collectors.toSet());
        log.info("Running Config Server with profiles {}", profiles);
    }

    public static void main(String[] args) {
       // SpringApplication.run(ConfigurationServiceApplication.class, args);

        SpringApplication app = new SpringApplication(ConfigurationServiceApplication.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        addDefaultProfile(app, source);
        app.run(args);
    }

    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active") &&
                !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {

            app.setAdditionalProfiles("dev");
        }
    }

}
