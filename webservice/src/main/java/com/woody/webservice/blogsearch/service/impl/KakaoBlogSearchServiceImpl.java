package com.woody.webservice.blogsearch.service.impl;

import com.woody.client.kakao.KakaoApiClient;
import com.woody.client.kakao.dto.response.KakaoBlogSearchResponse;
import com.woody.webservice.blogsearch.service.BlogSearchService;
import com.woody.webservice.blogsearch.service.data.BlogSearchConditionData;
import com.woody.webservice.blogsearch.service.data.BlogSearchResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;


/**
 * Created by woody 2023.03.20
 * 카카오 API 연동 블로그 검색 서비스 구현체
 * */
@Primary
@Service
@RequiredArgsConstructor
public class KakaoBlogSearchServiceImpl implements BlogSearchService {

    private final KakaoApiClient kakaoApiClient;

    @Override
    public Mono<BlogSearchResultData> searchBlogs(BlogSearchConditionData searchCondition) {
        return kakaoApiClient.searchBlogs(searchCondition.getKeyword(), searchCondition.getSort().getValue(), searchCondition.getPage(), searchCondition.getSize())
                .map(kakaoBlogSearchResponse -> mapBlogSearchResult(kakaoBlogSearchResponse, searchCondition));
    }

    private BlogSearchResultData mapBlogSearchResult(KakaoBlogSearchResponse searchResponse, BlogSearchConditionData searchCondition) {
        return BlogSearchResultData.builder()
                .currentPage(searchCondition.getPage())
                .totalCount(searchResponse.getMeta().getTotalCount())
                .pageableCount(searchResponse.getMeta().getPageableCount())
                .size(searchCondition.getSize())
                .first(searchCondition.getPage() == 1)
                .last(searchResponse.getMeta().isEnd())
                .documents(searchResponse.getDocuments().stream().map(document -> new BlogSearchResultData.Document(document.getTitle(), document.getContents(), document.getUrl(), document.getBlogname(), document.getThumbnail(), document.getDatetime())).collect(Collectors.toList()))
                .build();
    }
}
