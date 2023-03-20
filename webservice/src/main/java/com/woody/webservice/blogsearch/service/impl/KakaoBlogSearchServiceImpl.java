package com.woody.webservice.blogsearch.service.impl;

import com.woody.webservice.blogsearch.event.BlogSearchStatisticsEvent;
import com.woody.webservice.blogsearch.service.BlogSearchService;
import com.woody.webservice.blogsearch.service.data.BlogSearchConditionData;
import com.woody.webservice.blogsearch.service.data.BlogSearchResultData;
import com.woody.client.kakao.KakaoBlogSearchClient;
import com.woody.client.kakao.dto.response.KakaoBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


/**
 * Created by woody 2023.03.20
 * 카카오 API 연동 블로그 검색 서비스 구현체
 * */
@Slf4j
@Primary
@Service("KakaoBlogSearch")
@RequiredArgsConstructor
public class KakaoBlogSearchServiceImpl implements BlogSearchService {

    private final KakaoBlogSearchClient kakaoBlogSearchClient;

    private final ApplicationEventPublisher publisher;

    @Override
    public BlogSearchResultData searchBlog(BlogSearchConditionData searchCondition) {

        if (true) {
            throw new RuntimeException();
        }

        KakaoBlogSearchResponse searchResponse = kakaoBlogSearchClient.searchBlog(searchCondition.getKeyword(), searchCondition.getSort().getValue(), searchCondition.getPage(), searchCondition.getSize());

        publisher.publishEvent(new BlogSearchStatisticsEvent(searchCondition.getKeyword()));

        return mapBlogSearchResult(searchResponse, searchCondition);
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
