package com.woody.webservice.blogsearch.api;

import com.woody.client.kakao.exception.KakaoServerErrorException;
import com.woody.webservice.blogsearch.api.request.BlogSearchRequest;
import com.woody.webservice.blogsearch.api.response.BlogSearchResponse;
import com.woody.webservice.blogsearch.enums.BlogSearchSource;
import com.woody.webservice.blogsearch.event.BlogSearchStatisticsEvent;
import com.woody.webservice.blogsearch.service.router.BlogSearchServiceRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
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

    private final BlogSearchServiceRouter blogSearchServiceRouter;

    private final ApplicationEventPublisher publisher;

    /**
     * 블로그 검색 API
     * Kakao 블로그 검색 API 서버 장애 발생시
     * Naver 블로그 검색 API 응답 반환
     *
     * @param request (keyword, page, size, sort)
     * @return BlogSearchResponse
     * */
    @GetMapping
    public Mono<BlogSearchResponse> searchBlog(@Valid BlogSearchRequest request) {

        // 검색 조회수 증가 이벤트 호출
        publisher.publishEvent(new BlogSearchStatisticsEvent(request.getKeyword()));

        // 검색 API 호출
        return blogSearchServiceRouter.getServiceBySource(BlogSearchSource.KAKAO).searchBlogs(request.toSearchCondition())
                .onErrorResume(
                        KakaoServerErrorException.class,
                        e -> blogSearchServiceRouter.getServiceBySource(BlogSearchSource.NAVER).searchBlogs(request.toSearchCondition())
                )
                .map(BlogSearchResponse::from);
    }
}
