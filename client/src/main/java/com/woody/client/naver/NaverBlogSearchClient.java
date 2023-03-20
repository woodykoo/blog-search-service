package com.woody.client.naver;

import com.woody.client.naver.config.NaverFeignConfig;
import com.woody.client.naver.dto.response.NaverBlogSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by woody 2023.03.18
 * Naver API Feign 인터페이스
 * */
@FeignClient(name = "NaverBlogSearchClient", url = "${naver.api.url}", configuration = NaverFeignConfig.class)
public interface NaverBlogSearchClient {
    @GetMapping("/v1/search/blog.json")
    NaverBlogSearchResponse searchBlog(@RequestParam("query") String query,
                                       @RequestParam(value = "display", defaultValue = "10") int display,
                                       @RequestParam(value = "start", defaultValue = "1") int start,
                                       @RequestParam(value = "sort", defaultValue = "sim") String sort);
}
