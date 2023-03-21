package com.woody.webservice.blogsearch.service.router;

import com.woody.webservice.blogsearch.enums.BlogSearchSource;
import com.woody.webservice.blogsearch.service.BlogSearchService;
import com.woody.webservice.exception.NotFoundServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Created by woody 2023.03.21
 * 블로그 검색 서비스 라우터
 * */
@Component
@RequiredArgsConstructor
public class BlogSearchServiceRouter {

    private final Map<String, BlogSearchService> blogSearchServiceMap;

    /**
     * 검색 Source로 해당 서비스 반환
     * @param blogSearchSource 검색 source
     * @return BlogSearchService
     * */
    public BlogSearchService getServiceBySource(BlogSearchSource blogSearchSource) {
        return Optional.ofNullable(blogSearchServiceMap.get(blogSearchSource.getServiceName()))
                .orElseThrow(() -> new NotFoundServiceException(String.format("%s 서비스를 찾을 수 없습니다.", blogSearchSource.getServiceName())));
    }
}
