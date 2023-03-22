package com.woody.client.naver;

import com.woody.client.naver.config.NaverApiProperties;
import com.woody.client.naver.dto.response.NaverBlogSearchResponse;
import com.woody.client.naver.exception.NaverBadRequestException;
import com.woody.client.naver.exception.NaverErrorResponse;
import com.woody.client.naver.exception.NaverServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NaverApiClient {

    private final WebClient webClient;

    private static final String BLOG_SEARCH_URI = "/v1/search/blog.json";

    public NaverApiClient(NaverApiProperties naverApiProperties) {
        this.webClient = WebClient.builder()
                .baseUrl(naverApiProperties.getUrl())
                .defaultHeader("X-Naver-Client-Id", naverApiProperties.getClientId())
                .defaultHeader("X-Naver-Client-Secret", naverApiProperties.getClientSecret())
                .build();
    }

    public Mono<NaverBlogSearchResponse> searchBlogs(String query, String sort, int start, int display) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(BLOG_SEARCH_URI)
                        .queryParam("query", query)
                        .queryParam("sort", sort)
                        .queryParam("start", start)
                        .queryParam("display", display)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.bodyToMono(NaverErrorResponse.class)
                            .flatMap(errorResponse -> Mono.error(new NaverBadRequestException(errorResponse)));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.bodyToMono(NaverErrorResponse.class)
                            .flatMap(errorResponse -> Mono.error(new NaverServerErrorException(errorResponse)));
                })
                .bodyToMono(NaverBlogSearchResponse.class);
    }
}
