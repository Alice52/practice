package io.github.alice52.test;

import common.swagger.annotation.EnableSwagger;
import io.github.alice52.common.inject.annotation.SimpleBootApplication;
import org.springframework.boot.SpringApplication;

@EnableSwagger
@SimpleBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
