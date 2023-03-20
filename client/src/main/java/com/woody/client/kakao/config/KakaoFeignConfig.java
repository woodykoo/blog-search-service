package com.woody.client.kakao.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by woody 2023.03.18
 * Kakao API 전용 Feign 설정 파일
 * */
@Configuration
public class KakaoFeignConfig {

    @Value("${kakao.api.key}")
    private String apiKey;

    @Bean
    public RequestInterceptor kakaoRequestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "KakaoAK " + apiKey);
    }
}
