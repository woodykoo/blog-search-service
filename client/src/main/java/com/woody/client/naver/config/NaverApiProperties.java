package com.woody.client.naver.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * Created by woody 2023.03.22
 * 네이버 API 호출 정보
 * */
@Getter
@ConstructorBinding
@ConfigurationProperties("naver.api")
public class NaverApiProperties {
    private final String url;
    private final String clientId;

    private final String clientSecret;

    public NaverApiProperties(String url, String clientId, String clientSecret) {
        this.url = url;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
