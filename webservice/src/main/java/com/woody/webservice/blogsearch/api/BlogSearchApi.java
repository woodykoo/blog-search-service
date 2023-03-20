package com.woody.webservice.blogsearch.api;

import com.woody.webservice.blogsearch.api.request.BlogSearchRequest;
import com.woody.webservice.blogsearch.api.response.BlogSearchResponse;
import com.woody.webservice.blogsearch.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Created by woody 2023.03.19
 * Blog 검색 API
 * */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/blog/search")
public class BlogSearchApi {
    private final BlogSearchService blogSearchService;

    /**
     * 블로그 검색
     * @param request (keyword, page, size, sort)
     * @return BlogSearchResponse
     * */
    @GetMapping
    public Mono<BlogSearchResponse> searchBlog(@Valid BlogSearchRequest request) {
        return Mono.fromCallable(() -> blogSearchService.searchBlog(request.toSearchCondition()))
                .map(BlogSearchResponse::from);
    }
}
