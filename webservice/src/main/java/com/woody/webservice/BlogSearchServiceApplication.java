package com.woody.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.woody")
@ConfigurationPropertiesScan(basePackages = "com.woody")
public class BlogSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSearchServiceApplication.class, args);
    }

}