package com.woody.client.kakao;


import com.woody.client.kakao.config.KakaoFeignConfig;
import com.woody.client.kakao.dto.response.KakaoBlogSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by woody 2023.03.18
 * Kakao API Feign 인터페이스
 * */
@FeignClient(name = "KakaoBlogSearchClient", url = "${kakao.api.url}", configuration = KakaoFeignConfig.class)
public interface KakaoBlogSearchClient {

    /**
     * 카카오 블로그 검색 API 호출
     * @param query 검색어
     * @param sort 정렬순서(정확도순, 최신순)
     * @param page 요청 페이지 번호
     * @param size 한 페이지당 요견 건수
     *
     * @return KakaoBlogSearchResponse
     * */
    @GetMapping("/v2/search/blog")
    KakaoBlogSearchResponse searchBlog(@RequestParam("query") String query,
                                       @RequestParam(value = "sort", defaultValue = "accuracy") String sort,
                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "10") int size
    );
}
