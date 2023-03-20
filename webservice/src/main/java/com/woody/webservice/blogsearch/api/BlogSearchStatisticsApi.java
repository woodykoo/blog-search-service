package com.woody.webservice.blogsearch.api;


import com.woody.webservice.blogsearch.api.response.BlogPopularSearchesResponse;
import com.woody.webservice.blogsearch.service.BlogSearchStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by woody 2023.03.20
 * 블로그 검색 통계 조회 API
 * */
@RestController
@RequiredArgsConstructor
@RequestMapping("/blog/statistics")
public class BlogSearchStatisticsApi {

    private final BlogSearchStatisticsService blogSearchStatisticsService;

    /**
     * 인기 검색어 목록 조회 API
     * 최대 10개 까지 조회
     * @return BlogPopularSearchesResponse
     * */
    @GetMapping("/popular/searches")
    public Mono<BlogPopularSearchesResponse> popularSearches() {
        return Mono.just(blogSearchStatisticsService.getPopularSearches())
                .map(BlogPopularSearchesResponse::from);
    }
}
