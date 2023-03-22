package com.woody.client.kakao;

import com.woody.client.kakao.config.KakaoApiProperties;
import com.woody.client.kakao.dto.response.KakaoBlogSearchResponse;
import com.woody.client.kakao.exception.KakaoBadRequestException;
import com.woody.client.kakao.exception.KakaoErrorResponse;
import com.woody.client.kakao.exception.KakaoServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KakaoApiClient {

    private final WebClient webClient;

    private static final String BLOG_SEARCH_URI = "/v2/search/blog";

    public KakaoApiClient(KakaoApiProperties kakaoApiProperties) {
        this.webClient = WebClient.builder()
                .baseUrl(kakaoApiProperties.getUrl())
                .defaultHeader("Authorization", "KakaoAK " + kakaoApiProperties.getKey())
                .build();
    }

    public Mono<KakaoBlogSearchResponse> searchBlogs(String query, String sort, int page, int size) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(BLOG_SEARCH_URI)
                        .queryParam("query", query)
                        .queryParam("sort", sort)
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.bodyToMono(KakaoErrorResponse.class)
                            .flatMap(errorResponse -> Mono.error(new KakaoBadRequestException(errorResponse)));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.bodyToMono(KakaoErrorResponse.class)
                            .flatMap(errorResponse -> Mono.error(new KakaoServerErrorException(errorResponse)));
                })
                .bodyToMono(KakaoBlogSearchResponse.class);
    }
}
