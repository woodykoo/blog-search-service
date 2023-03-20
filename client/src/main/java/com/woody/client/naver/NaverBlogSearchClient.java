package com.woody.client.naver;

import com.woody.client.naver.config.NaverFeignConfig;
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
    String searchBlog(@RequestParam("query") String query);
}
