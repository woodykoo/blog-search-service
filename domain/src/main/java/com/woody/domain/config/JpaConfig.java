package com.woody.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by woody 2023.03.18
 * JPA 설정 파일
 * */
@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = "com.woody.domain")
@EnableJpaRepositories(basePackages = "com.woody.domain")
public class JpaConfig {
}
